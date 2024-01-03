package controllers;


import java.util.List;

import anotacoes.Administrador;
import models.Cliente;
import models.Pedido;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Clientes extends Controller {

	public static void form() {
		render();
	}

	public static void listar(String termo) {
		List<Cliente> clientes = null;
		if (termo == null || termo.isEmpty()) {
			clientes = Cliente.findAll();			
		} else {
			clientes = Cliente.find("lower(nome) like ?1 or lower(email) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
		}
		render(clientes, termo);
	}
	
	@Administrador
	public static void remover(Long id) {
		Cliente c = Cliente.findById(id);
		c.delete();
		listar(null);
		
	}
	
	public static void editar(Long id) {
		Cliente c = Cliente.findById(id);
		renderTemplate("Clientes/form.html", c);
		
	}
	
	public static void salvar(Cliente c) {
		c.save();
		listar(null);
	}
}