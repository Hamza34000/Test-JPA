package fr.diginamic.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name="client")
@NamedQueries({ 
	@NamedQuery(
			name = "Client.getEmprunts", 
			query = "select e from Emprunt e where e.clientEmprunt.id = :id"
			),
	
})
public class Client {
	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */
	
	@Column(name = "nom", length = 50, nullable = false)
	private String nom;
	
	@Column(name = "prenom", length = 50, nullable = false)
	private String prenom;
	

	public Client() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	
	

}
