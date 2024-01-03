package controllers;

import java.util.List;
import models.Cliente;
import models.Pedido;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
@With(Seguranca.class) 
public class Clientes extends Controller {

	public static void form() {
		render();
	}
	
	public static void remover(Long id) {
		Cliente c = Cliente.findById(id);
		c.delete();
		listar(null);
		
	}
	
	public static void editar(Long id) {
		Cliente c = Cliente.findById(id);
		renderTemplate("Clientes/form.html", c);
		
	}
	
	public static void salvar(@Valid Cliente c) {
		if (validation.hasErrors()) {
			flash.error("Preencha todos os campos");
			redirecionarErros();
		}
			
		c.save();
		listar(null);
		}
	
	private static void redirecionarErros() {
		params.flash();
		validation.keep();
		form();
	}
	
	public static void listar(String termo) {
		List<Cliente> clientes = null;
				if (termo == null || termo.isEmpty()) {
					clientes = Cliente.findAll();			
				} else {
					clientes = Cliente.find("lower(nome) like ?1 or lower(email) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
							}
		render(clientes);
	}
	
	public static void listaAjax(String termo) {
		List<Cliente> clientes = null;
		if (termo == null || termo.isEmpty()) {
			clientes = Cliente.findAll();			
		} else {
			clientes = Cliente.find("lower(nome) like ?1 or lower(email) like ?1",
					"%"+ termo.toLowerCase() +"%").fetch();
		}
		System.out.println(clientes.size());
		renderJSON(clientes);
		}
	
}
