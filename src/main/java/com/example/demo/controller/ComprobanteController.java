package com.example.demo.controller;

import com.example.demo.Comprobante;
import com.example.demo.ComprobanteRequest;
import com.example.demo.ComprobanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ComprobanteRequest request) {
        try {
            Comprobante comprobante = comprobanteService.crearComprobante(request);
            return ResponseEntity.ok(comprobante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
