package controllers;


import java.util.List;
import models.Cliente;
import models.Pedido;
import play.mvc.Controller;
import play.mvc.With;
@With(Seguranca.class)

public class Pedidos extends Controller {
	
	public static void form() {
		List<Cliente> clientes = Cliente.findAll();
		render(clientes);
	}

	public static void listar() {
		List<Pedido> pedidos = Pedido.findAll();
		render(pedidos);
	}
	
	public static void remover(Long id) {
		Pedido p = Pedido.findById(id);
		p.delete();
		listar();
		
	}
	
	public static void editar(Long id) {
		Pedido p = Pedido.findById(id);		
		renderTemplate("Pedidos/form.html", p);
		
	}
	
	public static void salvar(Pedido p) {
		p.save();
		listar();
	}

}
