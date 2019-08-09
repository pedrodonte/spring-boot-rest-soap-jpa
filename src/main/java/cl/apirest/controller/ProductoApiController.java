package cl.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.apirest.db.entity.ProductoEntity;
import cl.apirest.db.repository.ProductoRepository;

@RestController
@RequestMapping({ "/api/producto" })
public class ProductoApiController {

	@Autowired
	private ProductoRepository repositorio;

	/**
	 * Metodo GET que retorna todos los registros de producto encontrados en la
	 * tabla
	 * 
	 * @return
	 */
	@GetMapping
	public List<ProductoEntity> buscarTodosLosProductos() {
		return (List<ProductoEntity>) repositorio.findAll();
	}

	/**
	 * Metodo GET que retorna el registro segun el identificador de producto
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<ProductoEntity> buscarPorCodigo(@PathVariable long id) {
		return repositorio.findById(id).map(registro -> ResponseEntity.ok().body(registro))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Metodo POST que permite crear un producto
	 * 
	 * @param producto
	 * @return
	 */
	@PostMapping
	public ProductoEntity crearProducto(@RequestBody ProductoEntity producto) {
		return repositorio.save(producto);
	}

	/**
	 * Metodo PUT que permite actualizar un producto
	 * 
	 * @param id
	 * @param producto
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductoEntity> actualizarProducto(@PathVariable("id") long id,
			@RequestBody ProductoEntity producto) {
		return repositorio.findById(id).map(registro -> {
			registro.setNombre(producto.getNombre());
			registro.setPrecio(producto.getPrecio());
			ProductoEntity productoActualizado = repositorio.save(registro);
			return ResponseEntity.ok().body(productoActualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Metodo DELETE que permite eliminar un producto
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> eliminarProducto(@PathVariable("id") long id) {
		return repositorio.findById(id).map(registro -> {
			repositorio.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
