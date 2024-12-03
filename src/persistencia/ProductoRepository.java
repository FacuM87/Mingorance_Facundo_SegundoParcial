
package persistencia;

import java.util.List;
import java.util.Optional;
import models.Producto;


public class ProductoRepository implements Repository<Producto>{
    
    private final List<Producto> productos;
    private final GestorPersistencia<Producto> gestorPersistencia;
    private int ultimoId;

    public ProductoRepository(String archivoProductos) {
        this.gestorPersistencia = new GestorPersistencia<>(archivoProductos);
        this.productos = this.gestorPersistencia.cargar();
        this.ultimoId = ultimoId;
    }

   
    @Override
    public void add(Producto producto) {
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
