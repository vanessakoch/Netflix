package ifsc.edu.poo2.Netflix.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	private String name;
	private String email;
	private String senha;
	private String plano;
	private float valorMensal;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Perfil> listaPerfis;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Filme> listaFilme;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Serie> listaSerie;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Pergunta> listaPergunta;

	public User() {
		super();
	}

	public User(String name, String email, String senha, String plano, float valorMensal) {
		super();
		this.name = name;
		this.email = email;
		this.senha = senha;
		this.plano = plano;
		this.valorMensal = valorMensal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public float getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(float valorMensal) {
		this.valorMensal = valorMensal;
	}

	public void shareFilme(Filme movie) {
		if (listaFilme == null)
			listaFilme = new ArrayList<>();
		this.listaFilme.add(movie);
	}

	public void removeShareFilme(Filme movie) {
		this.listaFilme.remove(movie);
	}

	public void removeShareSerie(Serie serie) {
		this.listaSerie.remove(serie);
	}

	public void shareSerie(Serie serie) {
		if (listaSerie == null)
			listaSerie = new ArrayList<>();
		this.listaSerie.add(serie);
	}

	public void sharePergunta(Pergunta pergunta) {
		if (listaPergunta == null)
			listaPergunta = new ArrayList<>();
		this.listaPergunta.add(pergunta);
	}

	public void sharePerfil(Perfil perfil) {
		if (listaPerfis == null)
			listaPerfis = new ArrayList<>();
		this.listaPerfis.add(perfil);
	}

	public void removeSharePerfil(Perfil perfil) {
		this.listaPerfis.remove(perfil);
	}

	public List<Filme> getListaFilme() {
		return listaFilme;
	}

	public void setListaFilme(List<Filme> listaFilme) {
		this.listaFilme = listaFilme;
	}

	public List<Serie> getListaSerie() {
		return listaSerie;
	}

	public void setListaSerie(List<Serie> listaSerie) {
		this.listaSerie = listaSerie;
	}

	public List<Pergunta> getListaPergunta() {
		return listaPergunta;
	}

	public void setListaPergunta(List<Pergunta> listaPergunta) {
		this.listaPergunta = listaPergunta;
	}

	public List<Perfil> getPerfis() {
		return listaPerfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.listaPerfis = perfis;
	}

	public List<Perfil> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	@Override
	public String toString() {
		return "Nome: " + name + " Email: " + email + " Senha: " + senha + " Plano: " + plano + " Valor: " + valorMensal
				+ "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((listaFilme == null) ? 0 : listaFilme.hashCode());
		result = prime * result + ((listaPerfis == null) ? 0 : listaPerfis.hashCode());
		result = prime * result + ((listaPergunta == null) ? 0 : listaPergunta.hashCode());
		result = prime * result + ((listaSerie == null) ? 0 : listaSerie.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + Float.floatToIntBits(valorMensal);
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (listaFilme == null) {
			if (other.listaFilme != null)
				return false;
		} else if (!listaFilme.equals(other.listaFilme))
			return false;
		if (listaPerfis == null) {
			if (other.listaPerfis != null)
				return false;
		} else if (!listaPerfis.equals(other.listaPerfis))
			return false;
		if (listaPergunta == null) {
			if (other.listaPergunta != null)
				return false;
		} else if (!listaPergunta.equals(other.listaPergunta))
			return false;
		if (listaSerie == null) {
			if (other.listaSerie != null)
				return false;
		} else if (!listaSerie.equals(other.listaSerie))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (Float.floatToIntBits(valorMensal) != Float.floatToIntBits(other.valorMensal))
			return false;
		return true;
	}

}
