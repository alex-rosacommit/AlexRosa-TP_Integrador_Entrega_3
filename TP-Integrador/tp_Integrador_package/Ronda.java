package tp_Integrador_package;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	private String nro;
	private List<Partido> partidos;
	
	public Ronda(String matchNumber) {
		this.nro = matchNumber;
		this.partidos = new ArrayList<Partido>();
	}
	
	public Ronda() {
		this.nro = null;
		this.partidos = new ArrayList<Partido>();
	}
	
	public String getNumero() {
		return nro;
	}
	
	public void setNumero(String numero) {
		nro = numero;
	}
	
	public int puntos() {
		return 0;
	}
	
	public void agregarPartido(Partido nvPart) {
		partidos.add(nvPart);
	}
	
	public Partido getPartido(String partidoId) {
		Partido partidoEncontrado = null;
		
		for(Partido part : partidos) {
			if(partidoId.equals(part.getId())) {
				partidoEncontrado = part;
			}
		}
		
		return partidoEncontrado;
	}
	
	public void verPartidos() {
		System.out.println("\t\t\t\tResultados Ronda: " + this.nro );
		System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%15s\t\t %12s\t\t %12s \t\t%15s", "Equipo 1", "Cant. goles 1", "Cant. goles 2", "Equipo 2");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
		for(int i = 0; i < partidos.size(); i++) {
			this.partidos.get(i).verPartido();
		}
	}
}
