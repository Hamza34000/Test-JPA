package fr.diginamic.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="livre")
@NamedQueries(
		@NamedQuery(
				name="Livre.getEmprunts",
				query="select e from Emprunt e where :livre MEMBER OF e.empLivres"
				)
		)
public class Livre {

	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */
	
	@Column(name = "titre",length = 25,nullable = false)
	private String titre;
	
	@Column(name = "auteur",length = 25,nullable = false)
	private String auteur;
	
	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Livre () {
		
	}
}
