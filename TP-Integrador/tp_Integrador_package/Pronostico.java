package tp_Integrador_package;

public class Pronostico {
	private Partido match;
	private Equipo team = null;
	private ResultadoEnum result;
	
	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
		this.match = partido;
		this.team = equipo;
		this.result = resultado;
	}
	
	public Pronostico(Partido partido, ResultadoEnum resultado) {
		this.match = partido;
		this.result = resultado;
	}
	
	public Partido getPartido() {
		return this.match;
	}
	
	public Equipo getEquipo() {
		return this.team;
	}
	
	public ResultadoEnum getResultado() {
		return this.result;
	}
	
	public int puntos() {
		ResultadoEnum prediccion;
		
		if(team != null) {
			prediccion = match.resultado(team);
		}else {
			prediccion = match.resultado(result);
		}
		
		if(result == prediccion && prediccion != null) {
			return 1;
		}
		return 0;
	}
}
