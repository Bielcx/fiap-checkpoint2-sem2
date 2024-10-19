package br.com.fiap.ecommerce.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ecommerce.dtos.ItemPedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.ItemPedidoResponseDto;
import br.com.fiap.ecommerce.model.ItemPedido;

@Component
public class ItemPedidoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ItemPedidoResponseDto toDto(ItemPedido produto) {
        return modelMapper.map(produto, ItemPedidoResponseDto.class);
    }

    public ItemPedido toModel(ItemPedidoRequestCreateDto dto) {
        return modelMapper.map(dto, ItemPedido.class);
    }

    public ItemPedido toModel(Long id, ItemPedidoRequestUpdateDto dto) {
        ItemPedido result = modelMapper.map(dto, ItemPedido.class);
        result.setId(id);
        return result;
    }

}
