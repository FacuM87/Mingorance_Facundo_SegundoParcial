
package models;


public class Electronico extends Producto {
    private static final double INCREMENTO=0.2;
    private final int garantia;

    public Electronico(String nombre, double precioBase, int garantia) {
        super(nombre, precioBase);
        this.garantia = garantia;
    }

    @Override
    public double calcularPrecioFinal() {
        if(this.garantia>12){
            return this.precioBase*(1+INCREMENTO);
        }
        return this.precioBase;
    }
    
    
}
