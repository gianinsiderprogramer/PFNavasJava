package com.example.demo.dto;

import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComprobanteDTO {
    private Long id;
    private ClienteDTO cliente;
    private List<LineaComprobanteDTO> lineas;
    private Date fecha;
    private double total;
    private int totalProductos;
}