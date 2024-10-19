package br.com.fiap.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ecommerce.dtos.PedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.PedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.PedidoResponseDto;
import br.com.fiap.ecommerce.mapper.PedidoMapper;
import br.com.fiap.ecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")

@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> list() {
        List<PedidoResponseDto> dtos = pedidoService.list()
                .stream()
                .map(e -> pedidoMapper.toDto(e))
                .toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@RequestBody PedidoRequestCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        pedidoMapper.toDto(pedidoService.save(pedidoMapper.toModel(dto))));
    }

    @PutMapping("{id}")
    public ResponseEntity<PedidoResponseDto> update(
            @PathVariable Long id,
            @RequestBody PedidoRequestUpdateDto dto) {
        if (!pedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        return ResponseEntity.ok()
                .body(
                        pedidoMapper.toDto(
                                pedidoService.save(
                                        pedidoMapper.toModel(id, dto))));

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!pedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        pedidoService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(
                        pedidoService
                                .findById(id)
                                .map(e -> pedidoMapper.toDto(e))
                                .orElseThrow(() -> new RuntimeException("Id inexistente")));

    }
}
