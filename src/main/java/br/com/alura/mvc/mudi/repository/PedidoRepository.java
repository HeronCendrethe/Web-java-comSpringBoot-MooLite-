package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.alura.mvc.mudi.model.Pedido;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido,Integer>{

	@Cacheable("pedidos")
	@Query("select p from Pedido p join p.user u where u.username =  :username order by p.nomeProduto asc")
	List<Pedido> findAllByUser (@Param("username") String username, Pageable pageable);
}
