package jt.inventarios.servicio;

import java.util.List;

import jt.inventarios.modelo.Producto;

public interface IProductoServicio {

	public List<Producto> listarProductos();

	public Producto guardarProducto(Producto producto);
	
	public Producto buscarProductoPorId(Integer idProducto);
	
	public void eliminarProductoPorId(Integer idProducto);
}
