
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Compra implements Serializable {
    private static final long serialVersionUID = 2L;
    private int idCompra;
    private String cliente;
    private List<Producto> productos;
    private double total;

    public Compra(String cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }
    
    public void agregarProducto(Producto producto){
        productos.add(producto);
   
    }
    
    public double calcularTotal(){
        return productos.stream().mapToDouble(Producto::calcularPrecioFinal).sum();
    }

    public String mostrarDetalle(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cliente: %s%n", cliente))
                .append((String.format("ID Compra: %d%n", idCompra)))
                .append(String.format("Monto total: $%.2f%n", total))
                .append(String.format("Productos", productos));
        return sb.toString();
    }

    public int getIdCompra() {
        return idCompra;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    
    
    
}
