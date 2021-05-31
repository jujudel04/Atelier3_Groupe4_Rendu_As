package groupe4.market.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupe4.market.model.Account;
import groupe4.market.repository.AccountRepository;



@Service
public class MarketService {
	@Autowired
	AccountRepository accountRepository;

	MarketService() {
	}
	public Account getAccount(Integer userid) {
		Optional<Account> hOpt = accountRepository.findById(userid);
		if (hOpt.isPresent()) {
			return hOpt.get();
		}
		return null;
	}
	
	public void addCard(Integer cardId, Integer cardPrice, Integer userId) {
		Account account = this.getAccount(userId);
		if (account != null) {
			account.addCard(cardId);
			account.updateBalance(- cardPrice);
			this.saveAccount(account);
		}
	}
	public void removeCard(Integer cardId, Integer cardPrice, Integer userId) {
		Account account = this.getAccount(userId);
		if (account != null) {
			account.removeCard(cardId);
			account.updateBalance(cardPrice);
			this.saveAccount(account);
		}
	}
	
	public void saveAccount(Account h) {
		accountRepository.save(h);
	}

/*	public void addCard(Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserDto> user = restTemplate.getForEntity("http://localhost:8080/User", UserDto.class);
		ResponseEntity<CardDto> card = restTemplate.getForEntity("http://localhost:8081/Card", CardDto.class);
		if (user != null) {
			user.addCard(card.getId());
			user.updateBalance(-card.getPrice());
			// for test
			List<Integer> cs = user.getUserCardList();
			this.saveUser(user);
		}
	}

	public void removeCard(Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		UserDto user = restTemplate.getForObject("http://localhost:8080/User", UserDto.class);
		CardDto card = restTemplate.getForObject("http://localhost:8081/Card", CardDto.class);
		if (user != null) {
			user.removeCard(card.getId());
			user.updateBalance(+card.getPrice());
			this.saveUser(user);
		}
	}*/


	}

