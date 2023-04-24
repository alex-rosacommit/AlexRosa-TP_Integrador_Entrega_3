package tp_Integrador_package;

public class Partido {
	private String partidoId;
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	public Partido(Equipo team1, Equipo team2, int gTeam1, int gTeam2, String idPartido) {
		this.equipo1 = team1;
		this.equipo2 = team2;
		this.golesEquipo1 = gTeam1;
		this.golesEquipo2 = gTeam2;
		this.partidoId = idPartido;
	}
	
	public ResultadoEnum resultado(Equipo equipoExtra) {
		
		String nombreEquip1 = equipo1.getNombre();
		String nombreEquip2 = equipo2.getNombre();
		String nombreEquipExt = equipoExtra.getNombre();
		
		if(golesEquipo2 > golesEquipo1) {
			if(nombreEquip2.equals(nombreEquipExt)) {
				return ResultadoEnum.GANADOR;
			}
			if(nombreEquip1.equals(nombreEquipExt)) {
				return ResultadoEnum.PERDEDOR;
			}
		}
		
		if(golesEquipo1 > golesEquipo2) {
			if(nombreEquip1.equals(nombreEquipExt)) {
				return ResultadoEnum.GANADOR;
			}
			if(nombreEquip2.equals(nombreEquipExt)) {
				return ResultadoEnum.PERDEDOR;
			}
		}
		
		return ResultadoEnum.EMPATE;
	}
	
	public ResultadoEnum resultado(ResultadoEnum resultado) {
		if(golesEquipo2 == golesEquipo1) {
			return ResultadoEnum.EMPATE;
		}else {
			return null;
		}
		
	}
	
	public String getNombreEquipo1() {
		return this.equipo1.getNombre();
	}
	
	public String getNombreEquipo2() {
		return this.equipo2.getNombre();
	}
	
	public String getId() {
		return this.partidoId;
	}
	
	public void verPartido() {
        System.out.printf("%15s\t\t %12s\t\t%12s \t\t%15s",equipo1.getNombre(), String.valueOf(this.golesEquipo1), String.valueOf(this.golesEquipo2), this.equipo2.getNombre());
        System.out.println();
        System.out.println();
	}
}
