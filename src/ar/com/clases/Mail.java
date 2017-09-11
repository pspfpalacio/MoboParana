package ar.com.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedProperty;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import dao.impl.DAOCajaImpl;
import dao.impl.DAOClienteImpl;
import dao.impl.DAODatosEmailImpl;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAODatosEmail;
import model.entity.Cliente;
import model.entity.DatosEmail;

public class Mail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(Mail.class);
	
	private DAODatosEmail datosEmailDao = new DAODatosEmailImpl();
	private DAOCliente clienteDao = new DAOClienteImpl();
	
	private Cliente cliente;
	private DatosEmail datosEmail;
	private String asunto;
	private String cuerpo;
	private String destinatarios;
	private String adjunto;
	
	
	public DAODatosEmail getDatosEmailDao() {
		return datosEmailDao;
	}

	public void setDatosEmailDao(DAODatosEmail datosEmailDao) {
		this.datosEmailDao = datosEmailDao;
	}

	public DAOCliente getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(DAOCliente clienteDao) {
		this.clienteDao = clienteDao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public DatosEmail getDatosEmail() {
		return datosEmail;
	}

	public void setDatosEmail(DatosEmail datosEmail) {
		this.datosEmail = datosEmail;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}

	public int send() {
		
		log.info("Iniciando envio de email...");
		log.info("Destinatatios: " + destinatarios);
		
		List<String> listDestinatarios;
		listDestinatarios = new ArrayList<String>();
		String dest[];
		if(destinatarios.length() > 0) {
			dest =  destinatarios.split(",");
		} else {
			dest = null;
		}
		if(dest != null) {
			for(int i = 0; i < dest.length; i++) {
				listDestinatarios.add(dest[i]);
			}
		}
		log.info(listDestinatarios.size());
		
		datosEmail = new DatosEmail();
		datosEmail = datosEmailDao.get();
		log.info("Direccion de correo emisora: " + datosEmail.getCorreo());
		
		if(cliente != null) {
			listDestinatarios.add(cliente.getEmail());
		}
		listDestinatarios.add(datosEmail.getCorreo());
		
		log.info("Cantidad destinatarios: " + listDestinatarios.size());
		
		Address []destinos = new Address[listDestinatarios.size()];
		int i = 0;
		for(String ld : listDestinatarios) {
			try {
				destinos[i] = new InternetAddress(ld);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp." + datosEmail.getServidor() + ".com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.user", datosEmail.getCorreo());
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message mje = new MimeMessage(session);
			mje.setFrom(new InternetAddress(datosEmail.getCorreo()));
			mje.setSubject(asunto);
			mje.addRecipients(Message.RecipientType.TO, destinos);
			mje.setText(cuerpo);
			Transport t = session.getTransport("smtp");
			t.connect(datosEmail.getCorreo(),datosEmail.getPassword());
			t.sendMessage(mje,mje.getAllRecipients());
			t.close();
		} catch (Exception e) {
			log.error(e);
			return 0;
	}	
		return 1;
	}
	

}
