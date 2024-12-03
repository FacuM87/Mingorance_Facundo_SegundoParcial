package services;

import exceptions.ElementoNuloException;
import exceptions.PrecioNegativoException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import models.Compra;
import models.Producto;
import persistencia.CompraRepository;
import persistencia.ProductoRepository;
import persistencia.Repository;

public class GestorTienda {

    private final Repository<Producto> productoRepository;
    private final Repository<Compra> compraRepository;

    public GestorTienda(String archivoProductos, String archivoCompras) {
        this.productoRepository = new ProductoRepository(archivoProductos);
        this.compraRepository = new CompraRepository(archivoCompras);
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new ElementoNuloException("No se pueden incorporar productos nulos");
        }
        productoRepository.add(producto);
    }

    public Optional<Producto> buscarProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    public void realizarCompra(Compra compra) {
        List<Producto> listaProductosCompra = compra.getProductos();
        for (Producto p : listaProductosCompra) {
            Optional<Producto> productoEnRepo = this.buscarProductoPorId(p.getId());

            if (productoEnRepo.isEmpty()) {
                throw new ElementoNuloException(
                        "El producto con ID " + p.getId() + " no se encuentra en el repositorio de productos. No se puede realizar la compra."
                );
            }
        }

    }

    public double calcularIngresos() {
        return compraRepository.findAll().stream().mapToDouble(Compra::calcularTotal).sum();
    }

    public List<Producto> filtrarProductos(Predicate<Producto> criterio) {
        return productoRepository.findAll().stream().filter(criterio).toList();
    }

    public void aplicarDescuento(Function<Producto, Double> descuento) {
        List<Producto> listaProductos = productoRepository.findAll();
        listaProductos.forEach(p -> {
            double nuevoPrecioBase = descuento.apply(p);
            if (nuevoPrecioBase >= 0) {
                p.setPrecioBase(nuevoPrecioBase);
            } else {
                throw new PrecioNegativoException("No se pueden establecer precios negativos. Revise el descuento aplicado");
            }
        });
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

}
