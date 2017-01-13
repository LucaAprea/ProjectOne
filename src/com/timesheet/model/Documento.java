package com.timesheet.model;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "data")
	private Date data;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "dl")
	private Blob dl;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dip")
	private Dipendente dipendente;

	// costruttori()

	public Documento(int id, Date data, String nome, String descrizione, Blob dl) {
		super();
		this.id = id;
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dl = dl;
	}

	public Documento() {
	}

	public Documento(Date data, String nome, String descrizione, Blob dl) {
		super();
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dl = dl;
	}

	public Documento(Date data, String nome, String descrizione) {
		super();
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	// Set() e Get()

	public int getId() {
		return id;
	}

	public Blob getDl() {
		return dl;
	}

	public void setDl(Blob dl) {
		this.dl = dl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	// toString()

	@Override
	public String toString() {
		return "Documento [id=" + id + ", data=" + data + ", nome=" + nome + ", descrizione=" + descrizione + ", dl="
				+ dl + ", dipendente=" + dipendente + "]";
	}

	// hashCode() ed Equals()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((dl == null) ? 0 : dl.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Documento other = (Documento) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (dl == null) {
			if (other.dl != null)
				return false;
		} else if (!dl.equals(other.dl))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
