package co.com.carrito.domain;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int 	prodId;
	
	@NotNull(message = "Nombre no puede ser null")
	@Size(min =3, max =50, message ="Nombre debe ser entre 3 y 50 caracteres")
	private String 	nombre;
	
	@NotNull(message = "SKU no puede ser null")
	@Size(min =3, max =10, message ="SKU debe ser entre 3 y 10 caracteres")
	private String 	sku;
	
	@NotNull(message = "Descripcion no puede ser null")
	@Size(min =3, max =50, message ="Descripcion debe ser entre 3 y 50 caracteres")
	private String 	descripcion;
	
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
