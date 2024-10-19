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

import br.com.fiap.ecommerce.dtos.ClienteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ClienteRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ClienteResponseDto;
import br.com.fiap.ecommerce.mapper.ClienteMapper;
import br.com.fiap.ecommerce.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> list() {
        List<ClienteResponseDto> dtos = clienteService.list()
                .stream()
                .map(e -> clienteMapper.toDto(e))
                .toList();

        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@RequestBody ClienteRequestCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        clienteMapper.toDto(
                                clienteService.save(clienteMapper.toModel(dto))));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDto> update(
            @PathVariable Long id,
            @RequestBody ClienteRequestUpdateDto dto) {
        if (!clienteService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }
        return ResponseEntity.ok()
                .body(
                        clienteMapper.toDto(
                                clienteService.save(clienteMapper.toModel(id, dto))));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!clienteService.existsById(id)) {
            throw new RuntimeException("Id inexistente");
        }

        clienteService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(
                        clienteService
                                .findById(id)
                                .map(e -> new ClienteResponseDto().toDto(e))
                                .orElseThrow(() -> new RuntimeException("Id inexistente")));

    }

}
