package controllers;

import models.Cliente;
import play.mvc.Controller;

public class Logins extends Controller{

	public static void formLogin() {
		render();
	}
	
	public static void logar(String email, String senha) {
		Cliente cliente = Cliente.find("email = ?1 and senha = ?2", email.trim(), senha.trim()).first();
		if(cliente == null) {
			flash.error("E-mail ou Senha inválidos");
			formLogin();
			
		}else {
			session.put("clienteLogado", cliente.nome);
			session.put("perfilCliente", cliente.perfil);
			flash.success("Login realizado com sucesso!");
			Clientes.listar("");
		}
	}
		public static void logout() {
			session.clear();
			flash.success("Você saiu do sistema");
			formLogin();
	}
}
