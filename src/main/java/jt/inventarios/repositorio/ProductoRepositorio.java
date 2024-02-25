package jt.inventarios.repositorio;

import org.springframework.stereotype.Repository;
import jt.inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

}
