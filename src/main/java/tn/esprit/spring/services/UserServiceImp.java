package tn.esprit.spring.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Admin;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.agent;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class UserServiceImp implements UserService{
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CryptWithMD5 cryptWithMD5;
	
	

	@Override
	public void ajouterClient(Client client) {

	//	notificationServeur.sendNotification(client);
		String password = cryptWithMD5.cryptWithMD5(client.getPassword());

		client.setPassword(password);

		clientRepo.save(client);
		// clientRepo.save(client);

	}

	@Override
	public void ajouterAdmin(Admin admin) {
		//adminRepository.save(admin);

	}

	@Override
	public String authentification(String email, String password) {
		List<User> users = (List<User>) userRepo.findAll();
		int verifyemail = 0;
		int verifypassword = 0;
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				verifyemail++;

				if (user.getPassword().equals(cryptWithMD5.cryptWithMD5(password))) {
					verifypassword++;
					if (user instanceof Admin) {
						// System.out.println();
						return ("Welcome Admin");

					} else if (user instanceof Client) {
						if (((Client) user).isBlock()) {
							return ((Client) user).getDescriptionBlock();

						} else {
							return ("Welcome Client" + user.getFirstName() + user.getLastname());

						}

					} else if (user instanceof agent) {
						return ("Welcome agent");

					}
				} else {
					if (user instanceof Client) {
						int nbre = ((Client) user).getNbre();
						((Client) user).setNbre(nbre + 1);
						userRepo.save(user);
						if (((Client) user).getNbre() == 3) {
							((Client) user).setBlock(true);
							((Client) user).setDescriptionBlock("security problem");
							userRepo.save(user);
							return ("votre compte est blocke security problem");

						} else if (((Client) user).getNbre() >= 3) {
							((Client) user).setNbre(3);
							userRepo.save(user);
							return ("votre compte est blocke security problem");
						} else {
							return ("password incorrect");
						}

					}

				}

			}
		}
		if (verifyemail == 0) {
			return ("email not found ");
		} else {
			return ("");
		}

	}

	private static boolean checkString(String str) {
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		if (str.length() > 8) {
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (Character.isDigit(ch)) {
					numberFlag = true;
				} else if (Character.isUpperCase(ch)) {
					capitalFlag = true;
				} else if (Character.isLowerCase(ch)) {
					lowerCaseFlag = true;
				}
				if (numberFlag && capitalFlag && lowerCaseFlag)
					return true;
			}
		}
		return false;
	}

	@Override
	public String changerPassword(String id, String OldPassword,String password, String newPassword) {

		Long j = Long.parseLong(id);

		List<Client> users = (List<Client>) clientRepo.findAll();
		for (User user : users) {
			if (user.getId().equals(j)) {

				if (user.getPassword().equals(cryptWithMD5.cryptWithMD5(OldPassword))) {
					if(newPassword.equals(password)){
						
					
					if (checkString(newPassword)) {
						user.setPassword(cryptWithMD5.cryptWithMD5(newPassword));
						userRepo.save(user);
						return "password changer with succes";
					} else {
						return "password have 8 caracter upper lower and number";
					}
					}else {
						return "the password not the same";
					}

				} else {
					return "password incorrect";
				}
			}
		}
		return ".";
	}

	public User findUserwithId(long id) {
		User u = clientRepo.findById(id).orElse(null);
		return u;
	}

	/*
	 * @Overrid public user retrieveUser(String id) { user
	 * u=userRepository.findById(Long.parseLong(id)).orElse(null); //optional<user>
	 * soit elle retourne un user soit not //user
	 * u=userRepository.findById(Long.parseLong(id)).get(); // soit .get soit
	 * .orElse
	 * 
	 * l.info("user+++++"+u); return null; }
	 */
	@Override
	public List<Client> retrieveAllUsers() {
		List<Client> users = (List<Client>) clientRepo.findAll();
		for (Client user : users) {

			System.out.println(user.getId());
		}
		return users;
	}

	@Override
	public List<Ad> retrouveannoncesuivit(String Id) {

		return null;
	}

	@Override
	public String depblockCompte(String email) {
		List<Client> users = (List<Client>) clientRepo.findAll();
		for (Client user : users) {
			System.out.println(user.getEmail());
			if (user.getEmail().equals(email)) {

				if (user.getDescriptionBlock().equals("security problem")) {
			//		notificationServeur.sendNotification(user);
					String password = cryptWithMD5.cryptWithMD5(user.getPassword());

					user.setPassword(password);
					user.setDescriptionBlock("nothing");
					user.setNbre(0);
					user.setBlock(false);
					userRepo.save(user);
					return "we send you a new password pleaz chek your email";
				} else {
					return "this is not a security problem";
				}

			}
		}
		return "";

	}

	public void checkemail(String email) {

	}

	@Override
	public User authentification2(String email, String password) {
		System.out.println(email + password);
		List<User> users = (List<User>) userRepo.findAll();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				if (user.getPassword().equals(password)) {
					return user;
				} else {
					if (user instanceof Client) {
						
						((Client) user).setNbre(((Client) user).getNbre()+1);
						userRepo.save(user);
						if (((Client) user).getNbre() == 3 ) {
							((Client) user).setBlock(true);
							((Client) user).setDescriptionBlock("security problem");
							userRepo.save(user);
							

						}else if (((Client) user).getNbre() == 3 ) {
							((Client) user).setBlock(true);
							((Client) user).setDescriptionBlock("security problem");
							userRepo.save(user);
							
						}
					}
				}

			}
		}
		return null;

	}

	@Override
	public List<User> allUser() {
		List<User> users = (List<User>) userRepo.findAll();
		return users;
	}

	@Override
	public String forgetPassword(String email) {
		int existe=0;
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return "invalid email";
		}
		List<Client>users =(List<Client>)clientRepo.findAll();
		for(Client us:users) {
			if(us.getEmail().equals(email)) {
		//		notificationServeur.sendNotification(us);
				
				
				existe=1;
				return "we sind you a new password pleaz try to connect with it";
				
			
			}
		}
		if(existe==0) {
			return"this compte n'existe pas you can create an accompte";
		}
		return null;
	}

	@Override
	public Client afficherProfile(Long id) {
		Client cl = new Client();
		List<Client>users =(List<Client>)clientRepo.findAll();
		for(Client us:users) {
			if(us.getId().equals(id)) {
				cl = us;
			}
			
		} 
		return cl;
	}

	@Override
	public List<User> findyourfollows(Long id) {
		/*List<Client> clients=null;
		List<User> users=null;
		
		List<Client> users=(List<Client>) clientRepo.findAll();
		for(Client us:users) {
			int x=0;
			for(Client U:users) {
				
				
			}
			
		}
		//List<Client> users
		users=userRepo.whofollowyou(id);
		
		
		
		return users;*/
		return null;
	}

}
