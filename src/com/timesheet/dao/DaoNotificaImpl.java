package com.timesheet.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.timesheet.model.Notifica;

public class DaoNotificaImpl implements DaoNot {

	@Override
	public void insertNotifica(Notifica notifica) {
		// Create session factory object
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		session.beginTransaction();

		session.save(notifica);

		session.getTransaction().commit();

		session.close();

	}

	@Override
	public void updateNotifica(Notifica notifica) {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(notifica);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void deleteNotifica(Notifica notifica) {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(notifica);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Notifica findByIdNotifica(Notifica notifica) {

		Notifica not = null;
		try {
			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			not = (Notifica) session.get(Notifica.class, notifica.getIdNotifica());

			session.close();
			// sessionFactory.close();
			System.out.println("Restituisco " + not);
		} catch (HibernateException e) {

			e.printStackTrace();
		}
		return not;
	}

	@Override
	public List<Notifica> findAllNotifica() {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Notifica";
		Query qry = session.createQuery(hql);
		List<Notifica> list = qry.list();
		session.close();
		// sessionFactory.close();
		return list;
	}

}