package com.timesheet.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "notifica")
public class Notifica {

	@Id
	@Column(name = "id_notifica")
	private int idNotifica;
	@Column(name = "data")
	private Date data;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "dl")
	private Blob dl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dipendente")
	private Dipendente dipendente;

	// costruttori()

	public Notifica(int idNotifica, Date data, String nome, String descrizione, Blob dl) {
		super();
		this.idNotifica = idNotifica;
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dl = dl;
	}

	public Notifica(Date data, String nome, String descrizione, Blob dl) {
		super();
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dl = dl;
	}

	// Set() e Get()

	public Notifica() {
		// TODO Auto-generated constructor stub
	}

	public int getIdNotifica() {
		return idNotifica;
	}

	public void setIdNotifica(int idNotifica) {
		this.idNotifica = idNotifica;
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

	public Blob getDl() {
		return dl;
	}

	public void setDl(Blob dl) {
		this.dl = dl;
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
		return "Notifica [idNotifica=" + idNotifica + ", data=" + data + ", nome=" + nome + ", descrizione="
				+ descrizione + ", dl=" + dl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((dl == null) ? 0 : dl.hashCode());
		result = prime * result + idNotifica;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	// hashCode() ed Equals()

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notifica other = (Notifica) obj;
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
		if (idNotifica != other.idNotifica)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}