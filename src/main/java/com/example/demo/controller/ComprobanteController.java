package com.example.demo.controller;

import com.example.demo.model.Comprobante;
import com.example.demo.service.ComprobanteService;
import com.example.demo.repository.ComprobanteRepository;
import com.example.demo.dto.ComprobanteDTO;
import com.example.demo.dto.ComprobanteRequest;
import com.example.demo.mapper.ComprobanteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ComprobanteRequest request) {
        try {
            Comprobante comprobante = comprobanteService.crearComprobante(request);
            return ResponseEntity.ok(comprobante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ComprobanteDTO> listar() {
        return comprobanteRepository.findAll().stream()
                .map(ComprobanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteDTO> obtener(@PathVariable Long id) {
        return comprobanteRepository.findById(id)
                .map(c -> ResponseEntity.ok(ComprobanteMapper.toDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }
}
