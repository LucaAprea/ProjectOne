package com.timesheet.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.timesheet.model.Notifica;

public interface DaoNot {

	// DAO NOTIFICA

	void insertNotifica(Notifica notifica) throws HibernateException;

	void updateNotifica(Notifica notifica) throws HibernateException;

	void deleteNotifica(Notifica notifica) throws HibernateException;

	Notifica findByIdNotifica(Notifica notifica) throws HibernateException;

	List<Notifica> findAllNotifica() throws HibernateException;

}
