package model;

public class Categoria {

	private int codCategoria;
	private String categoria;
	
	public Categoria(String categoria, int codCategoria){
		this.categoria = categoria;
		this.codCategoria = codCategoria;
	}
	
	public Categoria(){
		
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
