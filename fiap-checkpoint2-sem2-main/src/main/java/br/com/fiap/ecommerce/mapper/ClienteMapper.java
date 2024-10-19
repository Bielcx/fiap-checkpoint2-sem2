package br.com.fiap.ecommerce.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ecommerce.dtos.ClienteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ClienteRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ClienteResponseDto;
import br.com.fiap.ecommerce.model.Cliente;

@Component
public class ClienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteResponseDto toDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDto.class);
    }

    public Cliente toModel(ClienteRequestCreateDto dto) {
        return modelMapper.map(dto, Cliente.class);
    }

    public Cliente toModel(Long id, ClienteRequestUpdateDto dto) {
        Cliente result = modelMapper.map(dto, Cliente.class);
        result.setId(id);
        return result;
    }

}
