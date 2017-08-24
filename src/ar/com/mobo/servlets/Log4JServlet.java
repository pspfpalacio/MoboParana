package ar.com.mobo.servlets;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Log4JServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This initializes Log4J by retrieving the .properties file in WEB-INF
	 */
	public void init(ServletConfig config) throws ServletException {
		String log4jLocation = config.getInitParameter("log4jprop-location");

		ServletContext sc = config.getServletContext();
		String webAppPath = sc.getRealPath("/");
		String log4jPath = webAppPath + log4jLocation;
		File log4jFile = new File(log4jPath);

		System.out.println("Initializing Log4J: " + log4jPath);
		if (log4jFile.exists()) {

			PropertyConfigurator.configure(log4jPath);
		} else {
			BasicConfigurator.configure();
		}

		super.init(config);
	}
}
