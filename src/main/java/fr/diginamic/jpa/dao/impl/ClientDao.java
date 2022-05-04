package fr.diginamic.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.dao.dao.Idao;
import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;
import fr.diginamic.jpa.entities.Livre;

public class ClientDao extends Dao implements Idao<Client> {

	public ClientDao(FactoryDao fd) {
		super(fd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Client e) throws Exception {
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
	public boolean update(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			Client ecli = em.find(Client.class, e.getId());
			if (ecli != null) {
				ecli.setNom(e.getNom());
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
	public boolean delete(Client e) throws Exception {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = fd.getEm();
			em.getTransaction().begin();
			e = em.find(Client.class, e.getId());
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
    public Client getOne(Client e) throws Exception {
		EntityManager em = null;
		try {
			em = fd.getEm();
	        TypedQuery<Client> tqb = em.createQuery("select client from Client client where client.id = :id", Client.class);
	        tqb.setParameter("id", e.getId());

        return tqb.getResultList().get(0);
		}
		finally {
			fd.close(em);
		}
    }

	@Override
    public List<Client> getAll() throws Exception {
        TypedQuery<Client> tqb = fd.getEm().createQuery("select client from Client client", Client.class);

        return tqb.getResultList();
    }
	
	
	public List<Emprunt> getEmprunts(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tqb = fd.getEm().createNamedQuery("Client.getEmprunts", Emprunt.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}
	
	public List<Livre> getLivres(Client e) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Livre> tqb = fd.getEm().createNamedQuery("Client.getLivres", Livre.class);
		tqb.setParameter("id", e.getId());

		return tqb.getResultList();

	}
	

}
