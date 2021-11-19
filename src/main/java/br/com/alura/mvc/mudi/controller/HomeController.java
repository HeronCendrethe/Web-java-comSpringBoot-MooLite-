package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/home")
	public String home(Model model , Principal principal) {
		
		Pageable pageable = PageRequest.of(0, 5);
		List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName(),pageable);
		model.addAttribute("pedidos", pedidos);
	
		return "home"; 
	}


}