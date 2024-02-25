package jt.inventarios.controlador;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jt.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import jt.inventarios.modelo.Producto;
import jt.inventarios.servicio.ProductoServicio;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {

	private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@GetMapping("/productos")
	public List<Producto> obtenerProductos(){
		List<Producto> productos = this.productoServicio.listarProductos();
		logger.info("Productos obtenidos");
		productos.forEach((producto -> logger.info(producto.toString())));
		return productos;
	}
	
	@PostMapping("/productos")
	public Producto agregarProducto(@RequestBody Producto producto){
		logger.info("Producto a agregar: " + producto);		
		return this.productoServicio.guardarProducto(producto);	
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id){
		Producto producto = this.productoServicio.buscarProductoPorId(id);
		if (producto != null) {
			return ResponseEntity.ok(producto);			
		}
		else {
			throw new RecursoNoEncontradoExcepcion("No se encontró el producto");
		}
	}
	
	@PutMapping("productos/{id}")
	public ResponseEntity<Producto> editarProducto(@PathVariable Integer id, @RequestBody Producto producto){
		 Producto productoEditado = this.productoServicio.buscarProductoPorId(id);
		 productoEditado.setDescripcion(producto.getDescripcion());
		 productoEditado.setPrecio(producto.getPrecio());
		 productoEditado.setStock(producto.getStock());
		 
		 Producto productoActualizado = this.productoServicio.guardarProducto(productoEditado);
		 return ResponseEntity.ok(productoActualizado);
	}
	
	@DeleteMapping("productos/{id}")
	public ResponseEntity<HashMap<String, Boolean>> elimiarProducto(@PathVariable Integer id){
		Producto producto = this.productoServicio.buscarProductoPorId(id);
		if (producto != null) {
			this.productoServicio.eliminarProductoPorId(id);
			HashMap<String, Boolean> productoEliminado = new HashMap<>();
			productoEliminado.put("Eliminado", Boolean.TRUE);
			return ResponseEntity.ok(productoEliminado);
		}
		else {
			throw new RecursoNoEncontradoExcepcion("No se encontró registro para el Id: " + id);
		}
	}
}
