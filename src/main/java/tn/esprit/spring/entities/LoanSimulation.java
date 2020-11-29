package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_LOAN_SIMULATION")
public class LoanSimulation implements Serializable {


	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLoan")
	private Long idLoan;
	
					/* Association */
	
	@ManyToOne
	Bank bank;
	
//	@ManyToOne
//	User user;
	
	///////////////////////////////////////////////////				

	
}
