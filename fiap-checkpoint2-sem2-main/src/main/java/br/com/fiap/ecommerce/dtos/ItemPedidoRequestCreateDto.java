package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequestCreateDto {
	private Long idPedido;
	private Long idProduto;
	private BigDecimal quantidade;
	private BigDecimal valorTotal;

}
