package com.timesheet.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.timesheet.model.Dipendente;

public interface DaoDip {

	// DAO DIPENDENTE

	void insertDipendente(Dipendente dipendente) throws HibernateException;

	void updateDipendente(Dipendente dipendente) throws HibernateException;

	void deleteDipendente(Dipendente dipendente) throws HibernateException;

	Dipendente findByIdDipendente(Dipendente dipendente) throws HibernateException;

	List<Dipendente> findAllDipendente() throws HibernateException;

	 Dipendente cercaDip(Dipendente d);
}
