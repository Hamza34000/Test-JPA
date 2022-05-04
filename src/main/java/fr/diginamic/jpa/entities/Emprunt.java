package fr.diginamic.jpa.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="emprunt")
public class Emprunt {
	/** @nnotation de Mapping avec la BDD*/
	@Id /** Définir la PK */
	@GeneratedValue(strategy = GenerationType.IDENTITY) /**AUTOINCREMENTAL PAR LE SGBDR */
	private int id; /** nom de la colonne */
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DEBUT", nullable = false)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FIN", nullable = true)
	private Date dateFin;
	
	private int delai;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	private Client clientEmprunt;
	/**
	 * FK entre Emprunt et Client
	 */
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "REL_EMP_LIV",
			joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name="ID_LIV", referencedColumnName = "ID"))
	private Set<Livre> empLivres;
	
	public Emprunt() {
		empLivres = new HashSet<>();
		
	}

	public Set<Livre> getEmpLivres() {
		return empLivres;
	}
	public void setEmpLivres(Set<Livre> empLivres) {
		this.empLivres = empLivres;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getDelai() {
		return delai;
	}
	public void setDelai(int delai) {
		this.delai = delai;
	}
	public Client getClientEmprunt() {
		return clientEmprunt;
	}
	public void setClientEmprunt(Client clientEmprunt) {
		this.clientEmprunt = clientEmprunt;
	}
	
	@Override
    public String toString() {
        return "Emprunt [id=" + id + ", Date_Debut=" + dateDebut + ", Date_Fin=" + " ]";
    }
	
	

	
}
