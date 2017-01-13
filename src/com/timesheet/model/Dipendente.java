package com.timesheet.model;

import java.util.List;
import java.util.Set;
import java.lang.CloneNotSupportedException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dipendente")
public class Dipendente implements Cloneable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "cf")
	private String cf;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	private int admin;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<Documento> documento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
	private Set<Notifica> notifica;

	// costruttori()

	public Dipendente() {

	}

	public Dipendente(int id, String nome, String cognome, String cf, String username, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.username = username;
		this.password = password;
	}

	public Dipendente(String nome, String cognome, String cf, String username, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.username = username;
		this.password = password;
	}

	public Dipendente(String string, String string2) {

	}

	// Set() e Get()
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(Set<Documento> documento) {
		this.documento = documento;
	}

	public Set<Notifica> getNotifica() {
		return notifica;
	}

	public void setNotifica(Set<Notifica> notifica) {
		this.notifica = notifica;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	// toString()
	@Override
	public String toString() {
		return "Dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", username="
				+ username + ", password=" + password + "]";
	}

	// hashCode() ed Equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cf == null) ? 0 : cf.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Dipendente other = (Dipendente) obj;
		if (cf == null) {
			if (other.cf != null)
				return false;
		} else if (!cf.equals(other.cf))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
		
	} 

}
