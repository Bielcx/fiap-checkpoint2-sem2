package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PedidoResponseDto {
	private Long id;
	private Long idCliente;
	private LocalDate dataPedido;
	private String status;
	private String formaPagamento;
	private BigDecimal valorTotal;

}
