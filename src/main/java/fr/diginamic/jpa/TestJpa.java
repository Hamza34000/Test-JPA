package fr.diginamic.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.diginamic.jpa.entities.Livre;

public class TestJpa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory efm = null;

		try {
			efm = Persistence.createEntityManagerFactory("bddCompta");
			/**
			 * Désormais je passe par efm pour obtenir mes EntityManager
			 */
			EntityManager em = efm.createEntityManager();

			// !!!!! Create Livre !!!!!

			em.getTransaction().begin();
			// J'ouvre une transaction pour protéger les mise à jours liées à la BDD
			Livre li = new Livre();// @Entity
			li.setTitre("Le livre du JPA");
			li.setAuteur("Marc Balouzet");

			em.persist(li);

			// commit -> Insérer concrétement mon Four dans la BDD
			em.getTransaction().commit();// Sinon RollBack

			
			

			/** UPDATE LIVRE */
			em.getTransaction().begin();
			// J'ouvre une transaction pour protéger les mise à jours liées à la BDD
			li = em.find(Livre.class, 5);
			if (li != null) {
				li.setTitre("Du plaisir dans la cuisine");
				em.merge(li); // UPDATE
				// commit -> Insérer concrétement mon Four dans la BDD
				em.getTransaction().commit();// Sinon RollBack
			} else {
				em.getTransaction().rollback();
				// J'annule tout ce qui c'est passé dans ma transaction de DATA
			}

			
			
			// !!!!! Recherche par Id de livre !!!!!
			int idRecherche = 3;
			TypedQuery<Livre> qf0 = em.createQuery("SELECT li FROM Livre li WHERE li.id = :idRecherche", Livre.class)
					.setParameter("idRecherche", idRecherche);
			List<Livre> lli0 = qf0.getResultList();
			lli0.stream().forEach(fob -> System.out
					.println("Id : " + fob.getId() + " Titre : " + fob.getTitre() + " Auteur : " + fob.getAuteur()));

			
			
			// !!!!! Recherche par Titre de livre !!!!!
			String titreRecherche = "Germinal";
			TypedQuery<Livre> qf = em
					.createQuery("SELECT li FROM Livre li WHERE li.titre = :titreRecherche", Livre.class)
					.setParameter("titreRecherche", titreRecherche);
			List<Livre> lli = qf.getResultList();
			lli.stream().forEach(fob -> System.out
					.println("Id : " + fob.getId() + " Titre : " + fob.getTitre() + " Auteur : " + fob.getAuteur()));

			
			
			// !!!!! Recherche par Auteur de livre !!!!!
			String auteurRecherche = "Jules Verne";
			TypedQuery<Livre> qf2 = em
					.createQuery("SELECT li FROM Livre li WHERE li.auteur = :auteurRecherche", Livre.class)
					.setParameter("auteurRecherche", auteurRecherche);
			List<Livre> lli2 = qf2.getResultList();
			lli2.stream().forEach(fob -> System.out
					.println("Id : " + fob.getId() + " Titre : " + fob.getTitre() + " Auteur : " + fob.getAuteur()));

			
			
			// !!!!! Suppression d'un livre !!!!!
			em.getTransaction().begin();
			li = em.find(Livre.class, 28);// on a null dans fo si pas trouvé
			if (li != null) {
				em.remove(li); // DELETE
				// commit -> Insérer concrétement mon Four dans la BDD
				em.getTransaction().commit();// Sinon RollBack
			} else {
				em.getTransaction().rollback();
				// J'annule tout ce qui c'est passé dans ma transaction de DATA
			}

			
			
			// !!!!! Liste de tous les livres !!!!!

			TypedQuery<Livre> qf3 = em.createQuery("SELECT li FROM Livre li", Livre.class);

			List<Livre> lli3 = qf3.getResultList();

			lli3.stream().forEach(fob -> System.out
					.println("Id : " + fob.getId() + " Titre : " + fob.getTitre() + " Auteur : " + fob.getAuteur()));

			/** Canal ouvert vers un SGBDR */
			em.close();
			efm.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
