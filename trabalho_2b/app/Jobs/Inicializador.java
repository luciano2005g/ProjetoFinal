package Jobs;

import java.util.Date;

import models.Cliente;
import models.Item;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Cliente.count() == 0) {
			Cliente camila = new Cliente();
			camila.nome = "Camila Araujo";
			camila.email = "camilaaraujo100604@gmail.com";
			camila.endereco = "Touros";
			camila.senha = "12345";
			camila.save();
			
			Cliente luciano = new Cliente();
			luciano.nome = "Luciano Tenorio";
			luciano.email = "luciano.tenorio@gmail.com";
			luciano.endereco = "Santa Luzia";
			luciano.senha = "12345";
			luciano.save();
		}
		if (Item.count() == 0) {
		 Item p1 = new Item();
		 p1.nome = "Morango";
		 p1.preco = 4.00;
		 p1.save();
		 
		 Item p2 = new Item();
		 p2.nome = "chocolate";
		 p2.preco = 4.00;
		 p2.save();
		 
		 Item p3 = new Item();
		 p3.nome = "ninho";
		 p3.preco = 4.00;
		 p3.save();
		 
		 Item p4 = new Item();
		 p4.nome = "doce de leite";
		 p4.preco = 4.00;
		 p4.save();
		 
	}
	}
}