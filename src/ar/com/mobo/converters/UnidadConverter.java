package ar.com.mobo.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.impl.DAOUnidadMovilImpl;
import dao.interfaces.DAOUnidadMovil;
import model.entity.UnidadMovil;

@FacesConverter("unidadConverter")
public class UnidadConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//		System.out.println("Value: " + value);
		if(value != null && value.trim().length() > 0) {
            try {
//            	System.out.println("Value: " + value);
            	DAOUnidadMovil unidadMovilDAO = new DAOUnidadMovilImpl();
            	int id = Integer.parseInt(value);
            	UnidadMovil unidad = new UnidadMovil();
            	unidad = unidadMovilDAO.get(id);
                return unidad;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversion", null));
            }
        }
        else {
            return null;
        }
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object != null) {
            return String.valueOf(((UnidadMovil) object).getId());
        }
        else {
            return null;
        }
	}

}
