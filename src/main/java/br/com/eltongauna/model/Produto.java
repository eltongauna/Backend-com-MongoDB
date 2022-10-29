package br.com.eltongauna.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Produto {
	
	@Id
	public String id;
	
	public String nome;
	
	public BigDecimal preco;
	
}
