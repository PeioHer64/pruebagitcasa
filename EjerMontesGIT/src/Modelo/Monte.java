package Modelo;
import java.util.Objects;

public class Monte {
	private String nombre;
	private String provincia;
	private int altura;
	private String coordenadas;
	private String macizo;
	private String ruta;
	

	public Monte(String nombre, String provincia, int altura, String coordenadas, String macizo, String ruta) {
		super();
		this.nombre = nombre;
		this.provincia = provincia;
		this.altura = altura;
		this.coordenadas = coordenadas;
		this.macizo = macizo;
		this.ruta = ruta;
	}



	public Monte() {
		// TODO Auto-generated constructor stub
	}


	
	@Override
	public String toString() {
		return "----------------\n"+
				"" + nombre + "   " + provincia + "   " + altura + "   "+ coordenadas + "   " + macizo + "   " + ruta + "";
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monte other = (Monte) obj;
		return Float.floatToIntBits(altura) == Float.floatToIntBits(other.altura)
				&& Objects.equals(coordenadas, other.coordenadas) && Objects.equals(macizo, other.macizo)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(provincia, other.provincia)
				&& Objects.equals(ruta, other.ruta);
	}
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public String getProvincia() {
		return provincia;
	}
	public int getAltura() {
		return altura;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public String getMacizo() {
		return macizo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public void setMacizo(String macizo) {
		this.macizo = macizo;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	
	
	
}
