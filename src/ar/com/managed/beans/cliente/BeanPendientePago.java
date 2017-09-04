package ar.com.managed.beans.cliente;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.entity.Usuario;

@ManagedBean
@SessionScoped
public class BeanPendientePago implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String goPendientePago(Usuario user) {
		return "pendientesdepago";	
	}

}
