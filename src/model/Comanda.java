package model;

import java.util.Date;

public class Comanda {

	private int codComanda;
	private String nome;
	private Date data;
	private String status;
	private float valorTotal;
	
	public Comanda(String nome, Date data, String status, float valorTotal){
		this.nome = nome;
		this.data = data;
		this.status = status;
		this.valorTotal = valorTotal;
	}
	
	public int getCodComanda() {
		return codComanda;
	}
	
	public void setCodComanda(int codComanda) {
		this.codComanda = codComanda;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public float getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
}
