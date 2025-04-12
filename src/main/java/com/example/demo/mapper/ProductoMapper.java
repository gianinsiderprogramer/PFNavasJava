package com.example.demo.mapper;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.model.Producto;

public class ProductoMapper {
    public static ProductoDTO toDTO(Producto producto) {
        return ProductoDTO.builder()
            .id(producto.getId())
            .nombre(producto.getNombre())
            .precio(producto.getPrecio())
            .build();
    }
}