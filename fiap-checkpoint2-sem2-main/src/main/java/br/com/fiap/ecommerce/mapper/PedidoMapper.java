package br.com.fiap.ecommerce.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ecommerce.dtos.PedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.PedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.PedidoResponseDto;
import br.com.fiap.ecommerce.model.Pedido;

@Component
public class PedidoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResponseDto toDto(Pedido produto) {
        return modelMapper.map(produto, PedidoResponseDto.class);
    }

    public Pedido toModel(PedidoRequestCreateDto dto) {
        return modelMapper.map(dto, Pedido.class);
    }

    public Pedido toModel(Long id, PedidoRequestUpdateDto dto) {
        Pedido result = modelMapper.map(dto, Pedido.class);
        result.setId(id);
        return result;
    }

}
