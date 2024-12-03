package persistencia;

import java.util.List;
import java.util.Optional;
import models.Producto;

public class ProductoRepository implements Repository<Producto> {

    private final List<Producto> productos;
    private final GestorPersistencia<Producto> gestorPersistencia;
    private int ultimoId;

    public ProductoRepository(String archivoProductos) {
        this.gestorPersistencia = new GestorPersistencia<>(archivoProductos);
        this.productos = this.gestorPersistencia.cargar();
        this.ultimoId = productos.stream().mapToInt(Producto::getId).max().orElse(0);
    }

    @Override
    public void add(Producto producto) {
        ultimoId++;
        producto.setId(ultimoId);
        productos.add(producto);
        gestorPersistencia.guardar(productos);
    }

    @Override
    public Optional<Producto> findById(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Producto> findAll() {
        return this.productos;
    }

}
