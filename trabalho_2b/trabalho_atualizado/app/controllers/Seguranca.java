package controllers;

import anotacoes.Administrador;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	
	@Before
    static void checkAuthentification() {
    	if (session.get("clienteLogado") == null) {
    		Logins.formLogin();
    	}
	}

	@Before
	static void verificarAdm() {
		String perfil = session.get("perfiUsuario");
		Administrador adminAnnotation = getActionAnnotation(Administrador.class);
		if (adminAnnotation != null && !"administrador".equals(perfil)) {
			forbidden("Acesso retrito aos administradores do sistema.");
		}
		}
}

