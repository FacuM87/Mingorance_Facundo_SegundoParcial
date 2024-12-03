# 🛍️ Sistema de Gestión de Inventario para Tienda en Línea

## **📜 Enunciado**

Una tienda en línea especializada en productos electrónicos y ropa ha decidido modernizar su sistema para gestionar el inventario, optimizar las ventas y brindar mejores promociones a sus clientes. Este sistema permitirá realizar tareas básicas de gestión, implementar estrategias de personalización y análisis, fundamentales para el crecimiento de la tienda.

---

## **🏗️ Capa Modelo**

### **🛒 Clase Abstracta: Producto**
- **Atributos**:
  - `static int contadorId`: contador para autoincrementar el ID 
  - `int id`: Identificador único (auto-incremental).
  - `String nombre`: Nombre del producto (mínimo 3 caracteres).
  - `double precioBase`: Precio base del producto (mayor a 0).

- **Métodos**:
  - `abstract double calcularPrecioFinal()`: Calcula el precio final según el tipo de producto.
  - `String mostrarResumen()`: Devuelve el ID, nombre y precio final.
  - `int getId`: obtiene el id del producto 
  - `void setId`: establece el id
  - `double getPrecioBase`: obtiene el precio base del producto
  - `void setPrecioBase`: establece el precio base

### **🔌💻 Clase: Electrónico (Subclase de Producto)**
- **Atributos**:
  - `static double INCREMENTO`: el incremento a aplicar en el precio final segun corresponda 
  - `int garantia`: Duración de la garantía (en meses). Debe ser mayor o igual a 0.

- **Métodos**:
  - `@Override calcularPrecioFinal()`: Incrementa un 20% del precio base si la garantía supera los 12 meses.

### **👗👔 Clase: Ropa (Subclase de Producto)**
- **Atributos**:
  - `static double INCREMENTO`: el incremento a aplicar en el precio final segun corresponda
  - `String talla`: Talla de la prenda (“S”, “M”, “L”, “XL”). No puede ser nulo ni vacío.

- **Métodos**:
  - `@Override calcularPrecioFinal()`: Incrementa un 10% si la talla es "XL".

### **🧾 Clase: Compra**
- **Atributos**:
  - `int idCompra`: ID único de la compra.
  - `void setIdCompra`: establece el Id de compra
  - `String cliente`: Nombre del cliente que realizó la compra.
  - `List<Producto> productos`: Lista de productos comprados.
  - `double total`: Precio total de la compra.

- **Métodos**:
  - `void agregarProducto(Producto producto)`: Agrega un producto a la lista de la compra y actualiza el total.
  - `void calcularTotal()`: Calcula el total sumando los precios finales de los productos.
  - `String mostrarDetalle()`: Muestra los detalles de la compra.
  - `int getIdCompra`: obtiene idCompra
  - `List<Producto> getProductos`: obtiene la lista de productos

---

## **⚙️ Capa Negocio**

### **🏬 Clase: GestorTienda**
- **Atributos**:
  - `Repository<Producto> productoRepository`: Repositorio para gestionar productos.
  - `Repository<Compra> compraRepository`: Repositorio para gestionar compras.

- **Métodos**:
  - `void agregarProducto(Producto producto)`: Agrega un producto al repositorio, validando sus datos.
  - `Optional<Producto> buscarProductoPorId(int id)`: Busca un producto por su ID.
  - `void realizarCompra(Compra compra)`: Registra una compra, valida los productos, calcula el total y la guarda.
  - `double calcularIngresos()`: Calcula los ingresos totales usando `stream()`.
  - `List<Producto> filtrarProductos(Predicate<Producto> criterio)`: Filtra productos según un criterio.
  - `void aplicarDescuento(Function<Producto, Double> descuento)`: Aplica descuentos dinámicos.
  - `List<Producto> listarProductos: devuelve la lista de productos del repositorio
  - `List<Compra> listarCompras: devuelve la lista de compras del repositorio

---

## **💾 Capa Persistencia**

### **📂 Interfaz Genérica: Repository<T>**
- **Métodos**:
  - `void add(T entity)`: Agrega una entidad.
  - `Optional<T> findById(int id)`: Busca una entidad por ID.
  - `List<T> findAll()`: Devuelve todas las entidades.

### **📦 Clase: ProductoRepository**
- Gestiona productos y su persistencia en un archivo binario.
- **Atributos**:
  - `List<Producto> productos`: Lista que almacena los productos.
  - `GestorPersistencia<Producto> gestorPersistencia`: Objeto para la persistencia de productos.
  - `int ultimoId`: referencia al ultimo id de producto persisitido
- **Métodos**:
  - `void add(Producto producto)`: sobreescribe el metodo de la interfaz generica Repository, agrega un producto a la lista y lo persiste
  - `Optional<Producto> findByid(in id)`: sobreescribe el metodo de la interfaz generica Repository, devuelve un Optional de producto o un Optional.emty()
  - `List<Producto> findAll`: sobreescribe el metodo de la interfaz generica Repository, devuelve la lista de productos cargados en el repositorio  

### **🛒 Clase: CompraRepository**
- Gestiona compras y su persistencia en un archivo binario.
- **Atributos**:
  - `List<Compra> compras`: Lista de compras realizadas.
  - `GestorPersistencia<Compra> gestorPersistencia`: Objeto para la persistencia de compras.
  - `ultimoId`: referencia al ultimo id de compra persistido
- **Métodos**:
  - `void add(Producto producto)`: sobreescribe el metodo de la interfaz generica Repository, agrega una compra a la lista y lo persiste
  - `Optional<Producto> findByid(in id)`: sobreescribe el metodo de la interfaz generica Repository, devuelve un Optional de compra o un Optional.emty()
  - `List<Producto> findAll`: sobreescribe el metodo de la interfaz generica Repository, devuelve la lista de compras cargados en el repositorio  

### **🗂️ Clase Genérica: GestorPersistencia<T extends Serializable>**
- **Atributos**:
  - `String archivo`: Nombre del archivo para almacenar datos.
- **Métodos**:
  - `void guardar(List<T> lista)`: Serializa y guarda una lista de objetos.
  - `List<T> cargar()`: Deserializa los objetos del archivo.

