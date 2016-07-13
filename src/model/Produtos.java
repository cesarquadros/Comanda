package model;

public class Produtos {

	private int codProdutos;
	private int codCategoria;
	private String descricao;
	private float preco;
	private String observacoes;
	
	
	public Produtos(int codCategoria, String descricao, float preco, String observacoes){
		this.codCategoria = codCategoria;
		this.descricao = descricao;
		this.preco = preco;
		this.observacoes = observacoes;
	}
	
	public int getCodProdutos() {
		return codProdutos;
	}
	
	public void setCodProdutos(int codProdutos) {
		this.codProdutos = codProdutos;
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codProdutos) {
		this.codCategoria = codProdutos;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
}
