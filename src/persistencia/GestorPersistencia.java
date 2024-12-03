
package persistencia;

import exceptions.PersistenciaException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GestorPersistencia <T extends Serializable> {
    private final String archivo;

    public GestorPersistencia(String archivo) {
        this.archivo = archivo;
    }
    
    public void guardar(List<T>lista){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new PersistenciaException("Error al guardar los datos: " + e.getMessage());
        }
    }
    
    public List<T> cargar(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.archivo))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenciaException("Error al cargar los datos: " + e.getMessage());
        }
    }
}