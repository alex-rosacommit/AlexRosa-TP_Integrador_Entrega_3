package tp_Integrador_package;

import java.util.ArrayList;
import java.util.List;

public class Persona {
	private String nombre;
	private List<Pronostico> miPronostico;
	private int misPuntos;
	
	public Persona(String nom, Pronostico prono) {
		this.miPronostico = new ArrayList<Pronostico>();
		this.miPronostico.add(prono);
		this.nombre = nom;
		this.misPuntos = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void agregarPronostico(Pronostico prono) {
		miPronostico.add(prono);
	}
	
	public void verPuntos() {
		for(Pronostico prono : miPronostico) {
			misPuntos = misPuntos + prono.puntos();
		}
		System.out.println();
		System.out.printf(nombre + "%5s", misPuntos);
		System.out.println();
	}
	
	public void verPronostico() {
		System.out.printf("\t\t\t\tPronosticos de: %2s", nombre );
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%15s\t\t %8s\t\t %8s \t\t%8s \t\t%15s", "Equipo 1", "Gana 1", "Empata", "Gana 2", "Equipo 2");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        
        Partido part = null;
        String gana = null;
    	String empata = null;
    	String pierde = null;
        
        for(Pronostico prono : miPronostico) {
        	part = prono.getPartido();
        	gana = "";
        	empata = "";
        	pierde = "";
        	
        	if(prono.getResultado() == ResultadoEnum.GANADOR) {
        		if(part.getNombreEquipo1().equals(prono.getEquipo().getNombre())) {
        			gana = "X";
        		}else {
        			pierde = "X";
        		}
        	}
        	if(prono.getResultado() == ResultadoEnum.PERDEDOR) {
        		if(part.getNombreEquipo2().equals(prono.getEquipo().getNombre())) {
        			pierde = "X";
        		}else {
        			gana = "X";
        		}
        		pierde = "X";
        	}
        	if(prono.getResultado() == ResultadoEnum.EMPATE) {
        		empata = "X";
        	}
        	
            System.out.printf("%15s\t\t %8s\t\t %8s \t\t%8s \t\t%15s", part.getNombreEquipo1(), gana, empata, pierde, part.getNombreEquipo2());
            System.out.println();
            System.out.println();
        }
	}
	
	
}
