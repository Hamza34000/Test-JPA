package fr.diginamic.jpa;

import java.util.Date;
import java.util.Iterator;

import fr.diginamic.jpa.dao.impl.ClientDao;
import fr.diginamic.jpa.dao.impl.EmpruntDao;
import fr.diginamic.jpa.dao.impl.FactoryDao;
import fr.diginamic.jpa.dao.impl.LivreDao;
import fr.diginamic.jpa.entities.Client;
import fr.diginamic.jpa.entities.Emprunt;
import fr.diginamic.jpa.entities.Livre;

public class AppFactory {

	public static FactoryDao COMPTA;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AppFactory.COMPTA = new FactoryDao("bddCompta");
			ClientDao cdo = new ClientDao(AppFactory.COMPTA);
			EmpruntDao edo = new EmpruntDao(AppFactory.COMPTA);
			Client cl = new Client();
			LivreDao ldo = new LivreDao(AppFactory.COMPTA);
			Livre liv = new Livre();
			
			//Creation Client
			cl.setNom("Hamza");
			cl.setPrenom("Amhaouch");
			cdo.add(cl);
			
			//Creation Emprunt
			Emprunt emprunt = new Emprunt();
			emprunt.setDateDebut(new Date());
			emprunt.setDateFin(new Date());
			emprunt.setDelai(5);
			emprunt.setClientEmprunt(cl);
			edo.add(emprunt);
			
			
			edo.getAll().forEach(boi->System.out.println(boi));//@ManyToOne -> 1 Four

            cdo.getAll().forEach(foi-> { 
                System.out.println(foi);
                //Je veux afficher la liste des Bons de mon Fournisseur
                //foi.getBons().forEach(boi->System.out.println(boi));//@OneToMany -> n Bons
                try {
                    cdo.getEmprunts(foi).forEach(boi->System.out.println(boi));
                }
                catch(Exception ex) {
                    System.err.println(ex.getMessage());
                }
            });
            
          //Creation Livre
			liv.setAuteur("Auteur");
			liv.setTitre("Titre");;
			ldo.add(liv);
			
			//Modification Livre
			liv.setAuteur("Hamzaa");
			ldo.update(liv);
			
			/**
			 * Modification d'un livre sélectionné
			 */
			liv.setId(7); 
			liv = ldo.getOne(liv);
			cl.setId(5); // Selection du Client 5
			cl = cdo.getOne(cl);
			liv.setAuteur("Hamza2");
			liv.setTitre("Hamza3");
			ldo.update(liv);
			
			/**
			 * Suppression de l'article 10
			 **/
			liv.setId(10);
			ldo.delete(liv);
			
			/**
			 * Gestion des Emprunts + Livre
			 */
			emprunt.setId(7); // Sélection de L'Emprunt N° 7
			Emprunt em7 = edo.getOne(emprunt);
			emprunt.setId(3); // Sélection de L'Emprunt N° 3
			Emprunt em3 = edo.getOne(emprunt);
			liv.setId(1);// Sélection du Livre N° 1
			Livre li1 = ldo.getOne(liv);
			liv.setId(5);// Sélection du livre N° 5
			Livre li5 = ldo.getOne(liv);
			liv.setId(4);// Sélection du livre N° 4
			Livre li4 = ldo.getOne(liv);
			
			/**
			 * Affection des livre 4 et 1 a l'emprunt 7
			 */
			em7.getEmpLivres().add(li4);
			em7.getEmpLivres().add(li1);
			
			/**
			 * Sauvegarde des modifications dans la BDD
			 */
			edo.update(em7);
			
			/**
			 * Affection des livres 4 et 5 a l'emprunt 3
			 */
			em3.getEmpLivres().add(li4);
			em3.getEmpLivres().add(li5);
			
			/**
			 * Sauvegarde des modifications dans la BDD
			 */
			edo.update(em3);
			
			/**
             * Sauvegarde des modifications dans la BDD
             */
            //edo.update(em3);

            edo.getAll().forEach(emp->System.out.println(emp));

            ldo.getEmprunt(li4).forEach(emp->System.out.println(emp));
            
            /**
			 * Gérer la suppression des livres de l'Emprunt em3
			 */
			//em3.getEmpLivres().clear();// Vide la collection des Livres liés a l'emprunt 3
			//edo.update(em3); // EMPRUNT.JAVA EST LE MASTER DE LA JOINTABLE
			// Déclenchement de la suppression BDD dans la table REL_EMP_LIV
            
			
			/**
			 * Gérer la suppression du livre 4 de l'Emprunt em7
			 
			System.out.println("AVANT : " + em7.getEmpLivres());
			// bo7.getBonArticles().remove(ab4);
			Iterator<Livre> ia = em7.getEmpLivres().iterator();
			while (ia.hasNext()) {
				Livre l = ia.next();
				if (l.getId() == li4.getId()) {
					ia.remove();
				}
			}
			System.out.println("APRES : " + em7.getEmpLivres());
			
			edo.update(em7);
			*/
			
			
			
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
}
}
