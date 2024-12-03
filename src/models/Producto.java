package models;

import java.io.Serializable;
import utils.Validador;

public abstract class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1;
    protected int id;
    protected String nombre;
    protected double precioBase;

    public Producto(String nombre, double precioBase) {
        if(Validador.esTextoValido(nombre, 3)){
            throw new IllegalArgumentException("El nombre del producto no puede tener menos de 3 caracteres");
        }
        
        if(!Validador.esPositivo(precioBase)){
            throw new IllegalArgumentException("El precio del producto debe ser mayor a 0");
        }
        
        this.id = contadorId++;
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public abstract double calcularPrecioFinal();

    public String mostrarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Producto: %s%n", nombre))
                .append((String.format("ID: %d%n", id)))
                .append(String.format("Precio base: $ %.2f%n ", precioBase));
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    
}
