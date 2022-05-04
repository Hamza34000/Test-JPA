package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.dao.Idao;
import fr.diginamic.jpa.entities.Emprunt;
import fr.diginamic.jpa.entities.Livre;

public class LivreDao extends Dao implements Idao<Livre> {

	public LivreDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Livre e) throws Exception {
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
	public boolean update(Livre e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Livre liv = em.find(Livre.class, e.getId());
			if (liv != null) {
				liv.setAuteur(e.getAuteur());
				liv.setTitre(e.getTitre());
				em.merge(liv);
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
	public boolean delete(Livre e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Livre.class, e.getId());
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
    public Livre getOne(Livre e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Livre> tqb = em.createQuery("select livre from Livre livre where livre.id = :id", Livre.class);
	        tqb.setParameter("id", e.getId());
	
	        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
		
    }

    @Override
    public List<Livre> getAll() throws Exception {
        TypedQuery<Livre> tqb = fd.getEm().createQuery("select livre from Livre livre", Livre.class);

        return tqb.getResultList();
    }
    
    public List<Emprunt> getEmprunt(Livre liv) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createNamedQuery("Livre.getEmprunts", Emprunt.class);
		tqb.setParameter("livre", liv);

		return tqb.getResultList();
	}

}
