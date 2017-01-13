package com.timesheet.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.timesheet.model.Dipendente;
import com.timesheet.model.Documento;

public class DaoDocumentoImpl implements DaoDoc {

	@Override
	public void insertDocumento(Documento documento) {
		try {

			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();

			Session session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(documento);

			session.getTransaction().commit();

			session.close();

		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void updateDocumento(Documento documento) {
		try {
			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(documento);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteDocumento(Documento documento) {
		try {

			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(documento);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Documento findByIdDocumento(Documento documento) {
		Documento doc = null;
		try {
			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			doc = (Documento) session.get(Documento.class, documento.getId());

			session.close();
			// sessionFactory.close();
			System.out.println("Restituisco " + doc);
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return doc;
	}

	@Override
	public List<Documento> findAllDocumento() {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Documento";
		Query qry = session.createQuery(hql);
		List<Documento> list = qry.list();
		session.close();
		// sessionFactory.close();
		return list;
	}

}