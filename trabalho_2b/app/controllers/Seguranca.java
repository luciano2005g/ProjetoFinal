package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	
	@Before
    static void checkAuthentification() {
    	if (session.get("clienteLogado") == null) {
    		Logins.formLogin();
    	}
	}
}

		


