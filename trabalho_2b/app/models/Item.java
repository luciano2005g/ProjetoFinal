package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import play.db.jpa.Model;

@Entity
public class Item extends Model {
	
	public String nome;
	public double preco;
	
	@Override
	public String toString() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	
}
