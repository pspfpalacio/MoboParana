package dao.interfaces;

import model.entity.Cheque;
import model.entity.PagosCliente;

public interface DAOCheque {
	
	public int insertar(Cheque cheque);
	
	public int update(Cheque cheque);
	
	public Cheque get(int id);
	
	public Cheque get(PagosCliente pagosCliente);

}
