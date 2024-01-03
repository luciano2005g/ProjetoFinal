package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pedido extends Model {

	public String tipoPedidos;
	public Date data;
	public String entregaPedido;
	public String formaPagamento;
	
	@ManyToOne
	public Cliente cliente;
}
