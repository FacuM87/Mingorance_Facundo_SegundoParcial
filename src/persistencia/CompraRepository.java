
package persistencia;

import java.util.List;
import java.util.Optional;
import models.Compra;


public class CompraRepository implements Repository<Compra> {
    private final List<Compra> compras;
    private final GestorPersistencia<Compra> gestorPersistencia;
    private int ultimoId;

    public CompraRepository(String archivoCompras) {
        this.gestorPersistencia = new GestorPersistencia<>(archivoCompras);
        this.compras = this.gestorPersistencia.cargar();
        this.ultimoId = compras.stream().mapToInt(Compra::getIdCompra).max().orElse(0);
        
    }
    
    @Override
    public void add(Compra compra) {
        ultimoId++;
        compra.setIdCompra(ultimoId);
        compras.add(compra);
        gestorPersistencia.guardar(compras);
    }

    @Override
    public Optional<Compra> findById(int id) {
        return compras.stream().filter(c -> c.getIdCompra() == id).findFirst();
    }

    @Override
    public List<Compra> findAll() {
        return this.compras;
    }
    
    
}
