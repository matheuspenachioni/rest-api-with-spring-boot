package br.com.ftstore.mp.service;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ReportService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public byte[] generateReport(String nameReport, ServletContext servletContext) throws Exception {
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		String pathReport = servletContext.getRealPath("reports")+ File.separator + nameReport +".jasper";
		JasperPrint print = JasperFillManager.fillReport(pathReport, new HashMap<String, Object>(), conn);
		
		byte[] result = JasperExportManager.exportReportToPdf(print);
		conn.close();
		
		return result;
	}
}
