package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;

public class CiudadDAO {
	
	public void insertCiudad(Ciudad c) {
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.persist(c);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	public void updateCiudad(Ciudad c) {
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.merge(c);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	public void deleteCiudad(int id) {
		Transaction transaction=null;
		Ciudad c=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			c=session.get(Ciudad.class, id);
			session.remove(c);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	public Ciudad selectCiudadById(int id) {
		Transaction transaction=null;
		Ciudad c=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			c=session.get(Ciudad.class, id);
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return c;
	}
	
	public List<Ciudad> selectAllCiudad(){
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			ciudades=session.createQuery("FROM Ciudad", Ciudad.class).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return ciudades;
	}
	
	public Ciudad selectCiudadByNombre(String nombre) {
		Ciudad c=null;
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			Query<Ciudad> query=session.createQuery("FROM Ciudad WHERE nombre = :nomParam", Ciudad.class);
			query.setParameter("nomParam", nombre);
			c=query.uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<Ciudad> selectCiudadByHabitantes(int numH){
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			Query<Ciudad> query=session.createQuery("FROM Ciudad WHERE numHabitantes < :numHabitantesParam", Ciudad.class);
			query.setParameter("numHabitantesParam", numH);
			ciudades=query.getResultList();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return ciudades;
	}
	
	public List<Ciudad> selectAllCiudadMillon(){
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			ciudades=session.createQuery("FROM Ciudad WHERE numHabitantes > 1000000", Ciudad.class).getResultList();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		return ciudades;
	}
	
}
