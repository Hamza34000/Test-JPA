package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.dao.Idao;
import fr.diginamic.jpa.entities.Emprunt;

public class EmpruntDao extends Dao implements Idao<Emprunt> {

	public EmpruntDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();			
			return true;
		}
		catch(Exception ex) {
			throw new Exception (ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public boolean update(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Emprunt ecli = em.find(Emprunt.class, e.getId());
			if (ecli != null) {
				ecli.setDateDebut(e.getDateDebut());
				ecli.setDateFin(e.getDateFin());
				ecli.setDelai(e.getDelai());
				ecli.setClientEmprunt(e.getClientEmprunt());
				ecli.setEmpLivres(e.getEmpLivres());
				em.merge(ecli);
				em.getTransaction().commit();
				return true;
			}
			return false;

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			fd.close(em);
		}
	}

	@Override
	public boolean delete(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Emprunt.class, e.getId());
			if(e != null) {
				em.remove(e);
				em.getTransaction().commit();			
				return true;
			}
			return false;
			
		}
		catch(Exception ex) {
			throw new Exception (ex.getMessage());
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public Emprunt getOne(Emprunt e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
		TypedQuery<Emprunt> tqb =
				em.createQuery("SELECT emprunt FROM Emprunt emprunt WHERE emprunt.id = :id", Emprunt.class);
		tqb.setParameter("id", e.getId());
		
		return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
	}

	@Override
	public List<Emprunt> getAll() throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createQuery("SELECT emprunt FROM Emprunt emprunt", Emprunt.class);

		return tqb.getResultList();
	}

}
