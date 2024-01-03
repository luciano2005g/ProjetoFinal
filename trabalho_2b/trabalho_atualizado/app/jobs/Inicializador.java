package jobs;

import java.util.Date;

import models.Cliente;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Cliente.count() == 0) {
			Cliente joao = new Cliente();
			joao.nome = "Joao Silva";
			joao.email = "joaojj@gmail.com";
			joao.endereco = "Touros";
			joao.senha = "12345";
			joao.save();
			
			Cliente maria = new Cliente();
			maria.nome = "Maria Silva";
			maria.email = "mmmmjj@gmail.com";
			joao.endereco = "Touros";
			maria.senha = "54321";
			maria.save();
		}
	}

}