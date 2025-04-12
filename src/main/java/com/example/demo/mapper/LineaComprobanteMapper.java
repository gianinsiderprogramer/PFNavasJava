package com.example.demo.mapper;

import com.example.demo.dto.LineaComprobanteDTO;
import com.example.demo.model.LineaComprobante;

public class LineaComprobanteMapper {
    public static LineaComprobanteDTO toDTO(LineaComprobante linea) {
        return LineaComprobanteDTO.builder()
            .id(linea.getId())
            .producto(ProductoMapper.toDTO(linea.getProducto()))
            .cantidad(linea.getCantidad())
            .precioUnitario(linea.getPrecioUnitario())
            .build();
    }
}