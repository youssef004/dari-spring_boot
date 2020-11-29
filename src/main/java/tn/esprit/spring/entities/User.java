package tn.esprit.spring.entities;




import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long id;   
	
	
	private String FirstName;
	
	private String Lastname;

	private Date dateNaissance;
	private String Email;
	private String Password;
	@Override
	public String toString() {
		return "User [id=" + id + ", FirstName=" + FirstName + ", Lastname=" + Lastname + ", dateNaissance="
				+ dateNaissance + ", Email=" + Email + ", Password=" + Password + "]";
	}
	public User(Long id, String firstName, String lastname, Date dateNaissance, String email, String password) {
		super();
		this.id = id;
		FirstName = firstName;
		Lastname = lastname;
		this.dateNaissance = dateNaissance;
		Email = email;
		Password = password;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Ad> Ad;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (FirstName == null) {
			if (other.FirstName != null)
				return false;
		} else if (!FirstName.equals(other.FirstName))
			return false;
		if (Lastname == null) {
			if (other.Lastname != null)
				return false;
		} else if (!Lastname.equals(other.Lastname))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	//youssef

	@OneToMany(mappedBy="idUser",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Commande> commandes;
	
	@OneToOne(mappedBy="user")
	@JsonIgnore
	private  LigneCommande ligneCommande;

	
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}
	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
	public User(Long id, String firstName, String lastname, Date dateNaissance, String email, String password,
			Set<tn.esprit.spring.entities.Ad> ad, List<Commande> commandes, LigneCommande ligneCommande) {
		super();
		this.id = id;
		FirstName = firstName;
		Lastname = lastname;
		this.dateNaissance = dateNaissance;
		Email = email;
		Password = password;
		Ad = ad;
		this.commandes = commandes;
		this.ligneCommande = ligneCommande;
	}


	
	
	
	

}

	
	
	
	
	
	