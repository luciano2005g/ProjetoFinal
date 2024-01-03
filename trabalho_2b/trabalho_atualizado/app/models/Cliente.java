package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;


@Entity
public class Cliente extends Model {
	
	public String nome;
    public String email;
	public String endereco;
	public String senha;
	public String perfil;
	

}
