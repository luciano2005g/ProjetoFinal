package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Cliente extends Model {
	
	@Required
	public String nome;
	@Required
    public String email;
	@Required
	public String endereco;
	@Required
	public String senha;	

}
