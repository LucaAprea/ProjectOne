package com.timesheet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.timesheet.model.Dipendente;

public class DaoDipendenteImpl implements DaoDip {

	@Override
	public void insertDipendente(Dipendente dipendente) {
		// Create session factory object
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		session.beginTransaction();

		session.save(dipendente);

		session.getTransaction().commit();

		session.close();
		// sessionFactory.close();

	}

	@Override
	public void updateDipendente(Dipendente dipendente) {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(dipendente);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void deleteDipendente(Dipendente dipendente) {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(dipendente);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Dipendente findByIdDipendente(Dipendente dipendente) {

		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Dipendente dip = (Dipendente) session.get(Dipendente.class, dipendente.getId());

		session.close();
		// sessionFactory.close();
		System.out.println("Restituisco " + dipendente);
		return dip;
	}

	@Override
	public List<Dipendente> findAllDipendente() {
		SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String hql = "from Dipendente order by Cognome,Nome";
		Query qry = session.createQuery(hql);
		List<Dipendente> list = qry.list();
		session.close();
		// sessionFactory.close();
		return list;
	}

	// Metodo per la Login
	
	@Override
	public Dipendente cercaDip(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		Dipendente dip = null;
		try {
			SessionFactory sessionFactory = com.timesheet.util.HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Dipendente.class);
			Criterion crit1 = Restrictions.eq("username", d.getUsername());
			Criterion crit2 = Restrictions.eq("password", d.getPassword());
			LogicalExpression lx = Restrictions.and(crit1, crit2);
			cr.add(lx);
			dip = (Dipendente) cr.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		System.out.println("cerca dip"+dip);
		return dip;
	}

}