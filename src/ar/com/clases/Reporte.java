package ar.com.clases;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class Reporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generar(Map parameters, List lista, String reporte, String printeo){
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/reportes/" + reporte + ".jrxml");
			
			JasperReport jasperReport;
			jasperReport = JasperCompileManager.compileReport(reportStream);
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista);
			
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			ServletOutputStream servletOutputStream = response.getOutputStream();
			facesContext.responseComplete();
			response.setContentType("application/pdf");
			
			response.addHeader("Content-disposition", printeo + ";filename=" + reporte + ".pdf");
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, source);
			JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
			
			servletOutputStream.flush();
			servletOutputStream.close();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public void exportXls(Map parameters, List lista, String reporte, String printeo) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/reportes/" + reporte + ".jrxml");

			JasperReport jasperReport;
			jasperReport = JasperCompileManager.compileReport(reportStream);
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(lista);		

			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, source);

			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			ServletOutputStream servletOutputStream = response.getOutputStream();
			facesContext.responseComplete();

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

			JRXlsExporter exporterXLS = new JRXlsExporter();                

			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporterXLS.exportReport();

			response.setHeader("Content-disposition", "attachment; filename=" + reporte + ".xls");
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(arrayOutputStream.toByteArray().length);

			servletOutputStream.write(arrayOutputStream.toByteArray());
			servletOutputStream.flush();
			servletOutputStream.close();
	
		 } catch(JRException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

}
