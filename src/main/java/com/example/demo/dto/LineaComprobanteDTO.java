package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineaComprobanteDTO {
    private Long id;
    private ProductoDTO producto;
    private int cantidad;
    private double precioUnitario;
}