package banking.common.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import banking.customers.domain.entity.Customer;

public class BaseHibernateRepository<T> implements BaseRepository<T> {

	protected SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void save(T entity) {
		getSession().save(entity);
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}
	
	
	
	
	
}
