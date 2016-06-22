package model;

public class ItemComanda {

	private int codItem;
	private int codProduto;
	private int codComanda;
	
	public ItemComanda(int codProduto, int codComanda){
		this.codProduto = codProduto;
		this.codComanda = codComanda;
	}
	
	public int getCodItem() {
		return codItem;
	}
	
	public void setCodItem(int codItem) {
		this.codItem = codItem;
	}
	
	public int getCoProduto() {
		return codProduto;
	}
	
	public void setCoProduto(int coProduto) {
		this.codProduto = coProduto;
	}
	
	public int getCodCoamnda() {
		return codComanda;
	}
	
	public void setCodCoamnda(int codComanda) {
		this.codComanda = codComanda;
	}
	
	
}
