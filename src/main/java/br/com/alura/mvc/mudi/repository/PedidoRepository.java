package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.alura.mvc.mudi.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido,Integer>{

	@Query("select p from Pedido p join p.user u where u.username =  :username order by p.nomeProduto asc")
	List<Pedido> findAllByUser (@Param("username") String username);
}
