package jt.inventarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jt.inventarios.modelo.Producto;
import jt.inventarios.repositorio.ProductoRepositorio;

@Service 
public class ProductoServicio implements IProductoServicio{

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Override
	public List<Producto> listarProductos() {
		return this.productoRepositorio.findAll();
	}
	
	@Override
	public Producto guardarProducto(Producto producto) {
		return this.productoRepositorio.save(producto);	
	}
	
	@Override
	public Producto buscarProductoPorId(Integer idProducto) {
		Producto producto = productoRepositorio.findById(idProducto).orElse(null);
		return producto;
	}
	
	@Override
	public void eliminarProductoPorId(Integer idProducto) {
		this.productoRepositorio.deleteById(idProducto);
	}

}
