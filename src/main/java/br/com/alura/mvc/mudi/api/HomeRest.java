package br.com.alura.mvc.mudi.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@RestController
@RequestMapping("/api/home")
public class HomeRest {

	@Autowired
	private PedidoRepository pedidoRepository;	
	
	@GetMapping("lista")
	    public List<Pedido> getHome() {
		
		String username  = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		Pageable pageable = PageRequest.of(0, 5);
		return  pedidoRepository.findAllByUser(username,pageable);
	}	
}
