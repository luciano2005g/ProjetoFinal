package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pedido extends Model {

	
	@Required
	@Temporal(TemporalType.DATE)
	public Date data;
	
	@Required
	public String entregaPedido;
	
	@Required
	public String formaPagamento;
	
	@ManyToOne
	public Cliente cliente;
	
	@ManyToMany
	public List<Item> itens;
	
	
}
