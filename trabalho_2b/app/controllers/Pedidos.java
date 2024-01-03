package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import models.Cliente;
import models.Item;
import models.Pedido;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
@With(Seguranca.class) 
public class Pedidos extends Controller {
	
	public static void form() {
		List<Cliente> clientes = Cliente.findAll();
		List<Item> itensCarrinho = Item.findAll();
		List<Item> itens = Cache.get(session.getId(), List.class);
		//List<Item> Valor = Cache.get(session.getId(), List.class);
		
		render(clientes, itensCarrinho, itens);
	}

	public static void addItem(Long id) {
		List<Item> itens = Cache.get(session.getId(), List.class);
	 	if (itens == null) {
	 		itens = new ArrayList<Item>();
	 	}
	 	
	 	Item item = Item.findById(id);
		itens.add(item);
		Cache.set(session.getId(), itens);
		form();
	}
	
	public static void listar() {
		List<Pedido> pedidos = Pedido.findAll();
		render(pedidos);
	}
	
	
	public static void calcularTotal() {
	    List<Item> itensCarrinho = Cache.get(session.getId(), List.class);
	    double total = 0;

	    if (itensCarrinho != null) {
	        for (Item item : itensCarrinho) {
	            total += item.getPreco(); 
	        }
	    }

	    renderJSON("{\"total\": " + total + "}");
	}
	
	public static void remover(Long id) {
		Pedido p = Pedido.findById(id);
		p.delete();
		listar();
	}
	
	public static void removerItens(Long id) {
	    List<Item> itensCarrinho = Cache.get(session.getId(), List.class);

	    if (itensCarrinho == null || itensCarrinho.isEmpty()) {
	        form();
	    }
// Para encontrar o item no carrinho com base no ID,  utilizamos o Optional esta classe foi introduzida para verificar se um valor está presente, 
// evitando o erro de NullPointerException. Um objeto Optional é um container que pode ou não conter um valor.
	    Optional<Item> itemOptional = itensCarrinho.stream()
	            .filter(item -> item.getId().equals(id))
	            .findFirst();

	    if (itemOptional.isPresent()) {
	        Item itemRemover = itemOptional.get();
	        itensCarrinho.remove(itemRemover);

	    
	        Cache.set(session.getId(), itensCarrinho);

	        // essa linha está preparando uma lista contendo apenas o item que  deseja remover do carrinho (itemRemover)  
	        //Essa lista é então usada para excluir apenas esse item específico do pedido.
	        Pedido pedido = new Pedido();
	        pedido.itens = Collections.singletonList(itemRemover);
	        pedido.delete();
	    }

	    form();
	}
	
	public static void editar(Long id) {
		Pedido p = Pedido.findById(id);		
		renderTemplate("Pedidos/form.html", p);	
	}
	
	private static void redirecionarErros() {
		params.flash();
		validation.keep();
		form();
	}
	
	public static void salvar(@Valid Pedido p) {
		if (validation.hasErrors()) {
			flash.error("Preencha todos os campos");
			redirecionarErros();
		}
		
		List<Item> itens = Cache.get(session.getId(), List.class);
		if (itens == null) {
			form();
		}
		
		p.itens = itens;
		p.save();
		
		Cache.clear();
		listar();
		}

}
