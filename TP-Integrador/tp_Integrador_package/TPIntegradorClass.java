package tp_Integrador_package;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TPIntegradorClass {

	public static void main(String[] args) {
		List<String> resultados;
		List<String> pronoEnArchivo;
		List<Ronda> listaRondas = new ArrayList<Ronda>();
		List<Persona> listaPersonas = new ArrayList<Persona>();
		
		//Objeto encargado de realizar la concección con la base de datos
		// y realizar las consultas necesarias.
		ConexionDataBase con = new ConexionDataBase();
		
		//Consulta la tabla 'partidos' a la base de datos.
		resultados = con.pedirTabla(TablasDB.partidos);
		
		//Consulta la tabla 'pronosticos' a la base de datos.
		pronoEnArchivo = con.pedirTabla(TablasDB.pronosticos);
		
		// Se instancian las rondas con sus partidos (segun figure en el archivo -resultados.txt-)
		listaRondas = instanciarRondas(resultados);
		
		// Se instancian las personas con sus pronosticos (Segun figure en el archivo -pronostico.txt)
		listaPersonas = instanciarPersonas(listaRondas,pronoEnArchivo);
		
		// Ver partidos en ronda.
		for(Ronda ronda : listaRondas ) {
			ronda.verPartidos();
		}
		
		// Ver pronostico de cada persona
		for(Persona per : listaPersonas) {
			per.verPronostico();
		}
				
		// Ver puntos de cada persona
		for(Persona per : listaPersonas) {
			per.verPuntos();
		}
		
	}
	/*Metodo que se encarga de instanciar las rondas segun los partidos
	 * que recibe en su parametro como un List<String>
	 */
	public static List<Ronda> instanciarRondas(List<String> dataInFile) {
		
		List<Ronda> rondas = new ArrayList<Ronda>();
		
		for(int i = 0; i < cantRondas(dataInFile); i++) {
			
			//Instanciación de una nueva ronda.
			Ronda nuevaRonda = new Ronda(String.valueOf(i+1));
			
			for(String lineaArchivo : dataInFile) {
				String[] datosLineaArch = lineaArchivo.split(";");
				
				if(Integer.valueOf(nuevaRonda.getNumero()) == Integer.valueOf(datosLineaArch[0])) {
					// Instanciación de los equipos y goles de equipos respectivamente.
					Equipo nuevoEquipo1 = new Equipo(datosLineaArch[1]);
					Equipo nuevoEquipo2 = new Equipo(datosLineaArch[4]);
					
					int cantGoles1 = Integer.valueOf(datosLineaArch[2]);
					int cantGoles2 = Integer.valueOf(datosLineaArch[3]);
					
					//Instanciación de Partido a base de los equipos instanciados anteriormente.
					Partido nuevoPartido = new Partido(nuevoEquipo1, nuevoEquipo2, cantGoles1, cantGoles2, datosLineaArch[5]);
					
					// Adición de partido a la nueva ronda.
					nuevaRonda.agregarPartido(nuevoPartido);
				}
			}
			// Adición de ronda a la lista de rondas.
			rondas.add(nuevaRonda);
		}
		// Retorno de la lista de rondas
		return rondas;
	}
	
	/*Metodo que se encarga de instanciar cada persona con sus respectivos
	 * pronosticos según el List<String> que resibe en su parametro
	 */
	private static List<Persona> instanciarPersonas(List<Ronda>rondas, List<String> dataInFile){
		List<Persona> personas = new ArrayList<Persona>();
		
		for(int i = 0; i < dataInFile.size(); i++) {
			String[] dataInLine = dataInFile.get(i).split(";");
			Partido nuevoPartido = null;
			Pronostico nuevoPronostico = null;
			
			for(Ronda ronda : rondas) {
				boolean flag = false;
				nuevoPartido = ronda.getPartido(dataInLine[3]);
				
				if(nuevoPartido != null) {
					if(dataInLine[2].equals("empate")) {
						
						nuevoPronostico = new Pronostico(nuevoPartido, ResultadoEnum.EMPATE);
						
					}else if(dataInLine[2].equals("ganador")){
						
						Equipo nuevoEquipo = new Equipo(dataInLine[1]);
						
						nuevoPronostico = new Pronostico(nuevoPartido, nuevoEquipo, ResultadoEnum.GANADOR);
					}else {
						Equipo nuevoEquipo = new Equipo(dataInLine[1]);
						
						nuevoPronostico = new Pronostico(nuevoPartido, nuevoEquipo, ResultadoEnum.PERDEDOR);
					}
					
					for(Persona perso : personas) {
						if(perso.getNombre().equals(dataInLine[0])) {
							perso.agregarPronostico(nuevoPronostico);
							flag = true;
						}
					}
					if(flag == false) {
						Persona nuevaPersona = new Persona(dataInLine[0], nuevoPronostico);
						personas.add(nuevaPersona);
					}
					
				}	
				
			}
		}
		
		return personas;
	}
	
	/*Metodo que se encarga de contar la cantidad de rondas que existen
	 * en el archivo -resultados.txt-.
	 * Se que es algo que podria mejor el programa para que no dependa de este metodo
	 * pero aun no he encontrado la manera.
	 */
	private static int cantRondas(List<String> dataInText) {
		int cantRond = 0;
		
		for(int i = 0; i < dataInText.size(); i++) {
			String rondaPartido = dataInText.get(i).split(";")[0]; 
			if(Integer.valueOf(rondaPartido) != cantRond) {
				cantRond++;
			}
		}
		return cantRond;
	}

}
