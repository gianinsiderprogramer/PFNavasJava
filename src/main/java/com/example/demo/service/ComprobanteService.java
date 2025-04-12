package com.example.demo.service;

import com.example.demo.dto.ComprobanteRequest;
import com.example.demo.dto.ComprobanteRequest.ClienteDTO;
import com.example.demo.dto.ComprobanteRequest.LineaDTO;
import com.example.demo.dto.ComprobanteRequest.ProductoDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Service
public class ComprobanteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private LineaComprobanteRepository lineaRepository;

    public Comprobante crearComprobante(ComprobanteRequest request) throws Exception {
        ClienteDTO clienteDTO = request.getCliente();
        Cliente cliente = clienteRepository.findById(clienteDTO.getClienteid())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<LineaComprobante> lineas = new ArrayList<>();
        double total = 0.0;
        int totalProductos = 0;

        for (LineaDTO lineaDTO : request.getLineas()) {
            ProductoDTO productoDTO = lineaDTO.getProducto();

            Producto producto = productoRepository.findById(productoDTO.getProductoid())
                    .orElseThrow(() -> new RuntimeException("Producto ID " + productoDTO.getProductoid() + " no encontrado"));

            int cantidad = lineaDTO.getCantidad();
            if (producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);

            LineaComprobante linea = LineaComprobante.builder()
                    .producto(producto)
                    .cantidad(cantidad)
                    .precioUnitario(producto.getPrecio())
                    .build();

            lineas.add(linea);
            total += cantidad * producto.getPrecio();
            totalProductos += cantidad;
        }

        Date fecha = obtenerFechaDesdeAPI();

        Comprobante comprobante = Comprobante.builder()
                .cliente(cliente)
                .fecha(fecha)
                .total(total)
                .totalProductos(totalProductos)
                .build();

        comprobante = comprobanteRepository.save(comprobante);

        for (LineaComprobante linea : lineas) {
            linea.setComprobante(comprobante);
            lineaRepository.save(linea);
        }

        return comprobante;
    }

    private Date obtenerFechaDesdeAPI() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject("http://worldclockapi.com/api/json/utc/now", String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            String currentDateTime = jsonNode.get("currentDateTime").asText();

            OffsetDateTime odt = OffsetDateTime.parse(currentDateTime);
            return Date.from(odt.toInstant());

        } catch (Exception e) {
            return new Date();
        }
    }
}
