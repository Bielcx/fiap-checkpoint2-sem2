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

import br.com.fiap.ecommerce.dtos.ItemPedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoResponseDto;
import br.com.fiap.ecommerce.mapper.ItemPedidoMapper;
import br.com.fiap.ecommerce.service.ItemPedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/itemPedidos")
@RequiredArgsConstructor
public class ItemPedidoController {
    private final ItemPedidoService itempedidoService;
    private final ItemPedidoMapper itempedidoMapper;

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponseDto>> list() {
        List<ItemPedidoResponseDto> dtos = itempedidoService.list()
                .stream()
                .map(e -> itempedidoMapper.toDto(e))
                .toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDto> create(@RequestBody ItemPedidoRequestCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itempedidoMapper.toDto(itempedidoService.save(itempedidoMapper.toModel(dto))));

    }

    @PutMapping("{id}")
    public ResponseEntity<ItemPedidoResponseDto> update(
            @PathVariable Long id,
            @RequestBody ItemPedidoRequestUpdateDto dto) {
        if (!itempedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        return ResponseEntity.ok()
                .body(itempedidoMapper.toDto(itempedidoService.save(itempedidoMapper.toModel(id, dto))));

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!itempedidoService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        itempedidoService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemPedidoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(
                        itempedidoService
                                .findById(id)
                                .map(e -> itempedidoMapper.toDto(e))
                                .orElseThrow(() -> new RuntimeException("Id inexistente")));

    }

}
