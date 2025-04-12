package com.example.demo.mapper;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente) {
        return ClienteDTO.builder()
            .id(cliente.getId())
            .nombre(cliente.getNombre())
            .email(cliente.getEmail())
            .build();
    }
}