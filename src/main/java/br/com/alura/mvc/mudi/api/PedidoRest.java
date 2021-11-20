package br.com.alura.mvc.mudi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.dto.ReqNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@RestController
@RequestMapping ("/api/pedido")
public class PedidoRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping("formulario") 
	public String formulario(ReqNovoPedido requisicao) {
		
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public Boolean novo(@Valid ReqNovoPedido requisicao, BindingResult result,HttpStatus httpStatus) {
		
		if(result.hasErrors() == true) {
			return httpStatus.is3xxRedirection();
		}
		
		String username  = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		User usuario = userRepository.findByUsername(username);
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(usuario);
		pedidoRepository.save(pedido);
		
		return httpStatus.is2xxSuccessful();
	}
	
	@PostMapping ("delete")
	public String deletar(Integer Id) {			
		System.out.println(Id);
//			String username  = SecurityContextHolder
//					.getContext()
//					.getAuthentication()
//					.getName();		
			
		pedidoRepository.deletarById(Id);
			
		 return "redirect:/home/lista";
		}

	

	
}


