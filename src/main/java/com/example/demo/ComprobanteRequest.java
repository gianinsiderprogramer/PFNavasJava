package com.example.demo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteRequest {

    private ClienteDTO cliente;
    private List<LineaDTO> lineas;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClienteDTO {
        private Long clienteid;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineaDTO {
        private int cantidad;
        private ProductoDTO producto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoDTO {
        private Long productoid;
    }
}