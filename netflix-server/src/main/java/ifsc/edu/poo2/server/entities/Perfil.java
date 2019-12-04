package ifsc.edu.poo2.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String idioma;
	private String permissao;
	private boolean menorIdade;

	public Perfil() {
		super();
	}

	public Perfil(String nome, String idioma, String permissao, boolean menorIdade) {
		super();
		this.nome = nome;
		this.idioma = idioma;
		this.permissao = permissao;
		this.menorIdade = menorIdade;
	}

	public Perfil(int id, String nome, String idioma, String permissao, boolean menorIdade) {
		super();
		this.id = id;
		this.nome = nome;
		this.idioma = idioma;
		this.permissao = permissao;
		this.menorIdade = menorIdade;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public boolean isMenorIdade() {
		return menorIdade;
	}

	public void setMenorIdade(boolean menorIdade) {
		this.menorIdade = menorIdade;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", Idioma: " + idioma + ", Permissao: " + permissao + ", Crianca: " + menorIdade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + (menorIdade ? 1231 : 1237);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
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
		Perfil other = (Perfil) obj;
		if (id != other.id)
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (menorIdade != other.menorIdade)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}

}
