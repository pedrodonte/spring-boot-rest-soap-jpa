package cl.apirest.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.apirest.db.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoEntity, Long> {


}
