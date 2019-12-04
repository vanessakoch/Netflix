package ifsc.edu.poo2.Netflix.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Serie {
	
	@Id
	@Column(name = "Série")
	private String titulo;
	@Column(name = "Temporadas")
	private int qtdTemporadas;
	@Column(name = "Ano")
	private int ano;

	public Serie() {
		super();
	}

	public Serie(String titulo) {
		super();
		this.titulo = titulo;
	}

	public Serie(String titulo, int qtdTemporadas, int ano) {
		super();
		this.titulo = titulo;
		this.qtdTemporadas = qtdTemporadas;
		this.ano = ano;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumTemporada() {
		return qtdTemporadas;
	}

	public void setNumTemporada(int numTemporada) {
		this.qtdTemporadas = numTemporada;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "" + titulo + ", Temporadas: " + qtdTemporadas + ", Ano: " + ano + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + qtdTemporadas;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (ano != other.ano)
			return false;
		if (qtdTemporadas != other.qtdTemporadas)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
