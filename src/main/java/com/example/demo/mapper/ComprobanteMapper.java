package com.example.demo.mapper;

import com.example.demo.dto.ComprobanteDTO;
import com.example.demo.dto.LineaComprobanteDTO;
import com.example.demo.model.Comprobante;

import java.util.List;
import java.util.stream.Collectors;

public class ComprobanteMapper {
    public static ComprobanteDTO toDTO(Comprobante comprobante) {
        List<LineaComprobanteDTO> lineas = comprobante.getLineas().stream()
            .map(LineaComprobanteMapper::toDTO)
            .collect(Collectors.toList());

        return ComprobanteDTO.builder()
            .id(comprobante.getId())
            .cliente(ClienteMapper.toDTO(comprobante.getCliente()))
            .lineas(lineas)
            .fecha(comprobante.getFecha())
            .total(comprobante.getTotal())
            .totalProductos(comprobante.getTotalProductos())
            .build();
    }
}