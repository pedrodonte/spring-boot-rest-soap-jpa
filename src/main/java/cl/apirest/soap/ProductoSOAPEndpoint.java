package cl.apirest.soap;

import cl.apirest.db.entity.ProductoEntity;
import cl.apirest.db.repository.ProductoRepository;
import cl.solaria.mensajes.producto.GetProductoRequest;
import cl.solaria.mensajes.producto.GetProductoResponse;
import cl.solaria.mensajes.producto.Producto;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductoSOAPEndpoint {

	private static final String NAMESPACE_URI = "http://www.solaria.cl/mensajes/producto";

	@Autowired
	private ProductoRepository repositorio;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductoRequest")
	@ResponsePayload
	public GetProductoResponse getProducto(@RequestPayload GetProductoRequest request) {

		GetProductoResponse response = new GetProductoResponse();

		Producto productoSOAP = new Producto();

		try {
			Optional<ProductoEntity> entity = repositorio.findById(request.getCodigo());

			productoSOAP.setCodigo(entity.get().getCodigo());
			productoSOAP.setNombre(entity.get().getNombre());
			productoSOAP.setPrecio(entity.get().getPrecio());
			response.setResultado("Encontrado");
		} catch (NoSuchElementException e) {
			response.setResultado("Sin Registros");
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultado(e.getLocalizedMessage());
		}

		response.setProducto(productoSOAP);

		return response;
	}
}