
package models;


public class Ropa extends Producto {
    private static final double INCREMENTO = 0.1;
    private String talla;

    public Ropa(String nombre, double precioBase, String talla) {
        super(nombre, precioBase);
        this.talla = talla;
    }

    @Override
    public double calcularPrecioFinal() {
        if(this.talla.equals("XL")){
            return this.precioBase*(1+INCREMENTO);
        }
        return this.precioBase;
    }
    
}
