package com.algaworks.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja extends Entidade {

	private static final long serialVersionUID = 1L;
	
	
	@SKU
	@NotBlank(message = "SKU é obrigatório")
	private String sku;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatório")
	@Size(max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String descricao;
	
	@NotNull(message = "Valor é obrigatorio")
	@DecimalMin("0.01")
	@DecimalMax(value = "99999999.99", message = "O valor da cerveja deve ser menor que R$99999999.99")
	private BigDecimal valor;
	
	@NotNull(message = "O teor alcóolico é obrigatorio")
	@DecimalMax(value = "100.0", message = "O teor alcóolico deve ser menor que 100")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@NotNull(message = "A comissão é obrigatoria")
	@DecimalMax(value = "100.0", message = "A comissão deve ser igual ou menor que 100")
	private BigDecimal comissao;
	
	@NotNull(message = "O estoque é obrigatorio")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@NotNull(message = "A origem é obrigatoria")
	@Enumerated(EnumType.STRING)
	private Origem origem;
	
	@NotNull(message = "O sabor é obrigatorio")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	
	@NotNull(message = "O estilo é obrigatorio")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;
	
	@Column(name = "foto")
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;

	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getFotoOrMock() {
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}

}
