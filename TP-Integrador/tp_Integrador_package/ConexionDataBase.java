package tp_Integrador_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConexionDataBase {
	/* Parametros para conexion con base de datos local.
	 *
	 *para utilizar la conexion local solo basta con quitar el comentario
	 *de los siguientes atributos y el comentario en la linea 87.
	 *tambien comentar la linea 88.
	 *
	 *.
	private String dataBaseUrlPort = "localhost:3306";
	private String dataBaseName = "tpintegrador";
	private String dataBaseUser = "root";
	private String dataBasePass = "AlexRosaJava1991";
	*/
	
	private String dbUser = "unrfwolzyn90jkhf";
	private String dbPass = "nnq0FTmJSjUzdVq6T9pZ";
	private String dbHost = "btni04v4si5szgonbgju-mysql.services.clever-cloud.com";
	private String dbPort = "3306";
	private String dbName = "btni04v4si5szgonbgju";
	private Connection miConexion = null;
	
	public ConexionDataBase(){
		System.out.println("Creando conexion con la base de datos. Por favor aguarde un momento.");
	}
	
	public void testConection() {
		String dbUser = "unrfwolzyn90jkhf";
		String dbPass = "nnq0FTmJSjUzdVq6T9pZ";
		String dbHost = "btni04v4si5szgonbgju-mysql.services.clever-cloud.com";
		String dbPort = "3306";
		String dbName = "btni04v4si5szgonbgju";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			miConexion = DriverManager.getConnection("jdbc:mysql://"+ dbUser +":"+ dbPass +"@"+ dbHost +":"+ dbPort +"/" + dbName);
			
			if(miConexion != null) {
				System.out.println("La coneccion con la base de datos fue exitosa.");
			}else {
				System.out.println("Error al intentar conectar con la base de datos.");
			}
			
			// Crear una instancia de la clase Statement
	        Statement stmt = miConexion.createStatement();
	        
	     // Ejecutar una consulta SQL
	        ResultSet rs = stmt.executeQuery("SELECT nombre, equipo, resultado_prono, partido_id FROM personas per LEFT JOIN pronosticos pron ON per.persona_id = pron.persona_id;");
        	String linea;
        	
        	while(rs.next()) {
        		String delimiter = "\t";
        		System.out.print(rs.getString("nombre") + delimiter);
        		System.out.print(rs.getString("equipo") + delimiter);
        		System.out.print(rs.getString("resultado_prono") + delimiter);
        		System.out.print(rs.getString("partido_id") + delimiter);
        		System.out.println();
        	}
        	// Cerrar el objeto ResultSet
        	rs.close();
            
         // Cerrar la conexión
            miConexion.close();
               
		}catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public List<String> pedirTabla(TablasDB tabla) {
		List<String> datosTabla = new ArrayList<String>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//miConeccion = DriverManager.getConnection("jdbc:mysql://"+ dataBaseUrlPort +"/" + dataBaseName, dataBaseUser, dataBasePass);
			miConexion = DriverManager.getConnection("jdbc:mysql://"+ dbUser +":"+ dbPass +"@"+ dbHost +":"+ dbPort +"/" + dbName);
			// Crear una instancia de la clase Statement
	        Statement stmt = miConexion.createStatement();
	        
	        if(tabla == TablasDB.pronosticos ) {
	        	// Ejecutar una consulta SQL
		        ResultSet rs = stmt.executeQuery("SELECT nombre, equipo, resultado_prono, partido_id FROM personas per LEFT JOIN pronosticos pron ON per.persona_id = pron.persona_id;");
	        	String linea;
	        	
	        	while(rs.next()) {
	        		String delimiter = ";";
	        		
	        		datosTabla.add(
	        				rs.getString("nombre") + delimiter +
	        				rs.getString("equipo") + delimiter +
	        				rs.getString("resultado_prono") + delimiter +
	        				rs.getString("partido_id") + delimiter
	        				);
	        	}
	        	// Cerrar el objeto ResultSet
	        	rs.close();
			}
	        
	     // En caso de querer consultar la tabla partidos en baseDatos
	        if(tabla == TablasDB.partidos ) {
	        	// Ejecutar una consulta SQL
		        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabla + ";");
	        	String linea;
	        	
	        	while(rs.next()) {
	        		String delimiter = ";";
	        		
	        		datosTabla.add(
	        				rs.getString("ronda") + delimiter +
	        				rs.getString("equipo1") + delimiter +
	        				rs.getString("golesEquipo1") + delimiter +
	        				rs.getString("golesEquipo2") + delimiter +
	        				rs.getString("equipo2") + delimiter +
	        				rs.getString("partido_id")
	        				);
	        	}
	        	// Cerrar el objeto ResultSet
	        	rs.close();
			}
	        
	     // Cerrar la instancia de Statement y la conexión.
            stmt.close();
            miConexion.close();
	        
		}catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return datosTabla;
	}
}


