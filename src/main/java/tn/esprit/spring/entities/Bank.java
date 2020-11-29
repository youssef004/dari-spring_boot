package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_BANK")
public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBank")
	private Long id;
	
	@Column(name = "nameBank")
	private String name;
	
	@Column(name = "descriptionBank")
	private String description; 
	
					/* Association */
	
//	@ManyToOne
//	AgentBank agentBank;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="bank")
	private Set<BankOffers> bankOffer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="bank")
	private Set<LoanSimulation> loanSimulation;

		///////////////////////////////////////////////////	
	
	public Bank() {
		super();
	}

	public Bank(Long id, String name, String description, Set<BankOffers> bankOffer,
			Set<LoanSimulation> loanSimulation) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.bankOffer = bankOffer;
		this.loanSimulation = loanSimulation;
	}

	public Bank(String name, String description, Set<BankOffers> bankOffer, Set<LoanSimulation> loanSimulation) {
		super();
		this.name = name;
		this.description = description;
		this.bankOffer = bankOffer;
		this.loanSimulation = loanSimulation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<BankOffers> getBankOffer() {
		return bankOffer;
	}

	public void setBankOffer(Set<BankOffers> bankOffer) {
		this.bankOffer = bankOffer;
	}

	public Set<LoanSimulation> getLoanSimulation() {
		return loanSimulation;
	}

	public void setLoanSimulation(Set<LoanSimulation> loanSimulation) {
		this.loanSimulation = loanSimulation;
	}
	
	
	
	
}
