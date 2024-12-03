
package persistencia;

import java.util.List;
import java.util.Optional;


public interface Repository <T> {
    void add(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    
}
