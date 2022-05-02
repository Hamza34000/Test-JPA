package fr.diginamic.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="livre")
public class Livre {

	@Id/** Définir la PK **/
	@Column(name = "id",length = 25,nullable = false)
	private int id;
	
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
