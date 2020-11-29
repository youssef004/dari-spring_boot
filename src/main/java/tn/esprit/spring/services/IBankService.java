package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Bank;

public interface IBankService {

	List<Bank> getAllBanks();
	public Long addBank(Bank bank);
	public void deleteBankByID(Long bankID );
	public Bank updateBank(Bank bank);
	List<String> getAllBankByNames();
}
