package model;

public class Categoria {

	private int codCategoria;
	private String categoria;
	
	public Categoria(String categoria){
		this.categoria = categoria;
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}
	
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}
