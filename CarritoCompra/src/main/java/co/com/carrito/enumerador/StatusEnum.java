package co.com.carrito.enumerador;

public enum StatusEnum {
	
	PENDING(1, "Pendiente"), 
	COMPLETED(2, "Completado");
	
	private int id;
	private String descripcion;
	
	private StatusEnum(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

}
