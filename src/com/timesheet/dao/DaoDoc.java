package com.timesheet.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.timesheet.model.Documento;

public interface DaoDoc {

	// DAO DOCUMENTO

	void insertDocumento(Documento documento) throws HibernateException;

	void updateDocumento(Documento documento) throws HibernateException;

	void deleteDocumento(Documento documento) throws HibernateException;

	Documento findByIdDocumento(Documento documento) throws HibernateException;

	List<Documento> findAllDocumento() throws HibernateException;

}
