package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.LoanSimulation;

public interface LoanSimulationRepository extends JpaRepository<LoanSimulation, Long> {

}
