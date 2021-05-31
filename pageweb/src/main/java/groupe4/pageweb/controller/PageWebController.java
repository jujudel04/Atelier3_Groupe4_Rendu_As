package groupe4.pageweb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class PageWebController {

	@RequestMapping(method = RequestMethod.GET, value = "/web/signup")
	public String signup(Model model, @RequestParam("login") String login, @RequestParam("passwd") String passwd) {
		RestTemplate restTemplate = new RestTemplate();
		Integer authenticateid = restTemplate.getForObject("http://localhost:8082/user/signup/" + login + "/" + passwd,
				Integer.class);
		if (authenticateid != null) {
			return showUser(model, authenticateid);//"redirect:show/" + authenticateid;
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/web/show/{authenticateid}")
	public String showUser(Model model, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			UserDto user = getUser(authenticateid);
			model.addAttribute("name", user.getName());
			AccountDto account = getAccount(authenticateid);
			model.addAttribute("balance", account.getAccountBalance());
			return "show";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/web/showall/{authenticateid}")
	public String showAllCards(Model model, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			UserDto user = getUser(authenticateid);
			model.addAttribute("name", user.getName());
			AccountDto account = getAccount(authenticateid);
			model.addAttribute("balance", account.getAccountBalance());
			return "cardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/web/buy/{cardid}/{authenticateid}")
	public String buyCard(Model model, @PathVariable Integer cardid, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			CardDto card = getCard(cardid,authenticateid);
			if (card != null) {
				RestTemplate restTemplate = new RestTemplate();
				String buyurl = "http://localhost:8084/market/buy/{cardid}/{balance}/{authenticateid}";
				ResponseEntity<Void> response = restTemplate.getForEntity(buyurl, Void.class, cardid, card.getPrice(),
						authenticateid);
				if (response.getStatusCode() == HttpStatus.OK) {
					return showUser(model, authenticateid); //"redirect:show/" + authenticateid;
				}
			}
			model.addAttribute("nok", "error during by card.");
			return "index2";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/web/sell/{cardid}/{authenticateid}")
	public String sellCard(Model model, @PathVariable Integer cardid, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			CardDto card = getCard(cardid,authenticateid);
			if (card != null) {
				RestTemplate restTemplate = new RestTemplate();
				String sellurl = "http://localhost:8084/market/sell/{cardid}/{balance}/{authenticateid}";
				ResponseEntity<Void> response = restTemplate.getForEntity(sellurl, Void.class, cardid, card.getPrice(),
						authenticateid);
				if (response.getStatusCode() == HttpStatus.OK) {
					return showUser(model, authenticateid); //"redirect:show/" + authenticateid;
				}
			}
			model.addAttribute("nok", "error during by card.");
			return "index2";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/web/play/{authenticateid}")
	public String listUserCards(Model model, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			UserDto user = getUser(authenticateid);
			model.addAttribute("name", user.getName());
			AccountDto account = getAccount(authenticateid);
			model.addAttribute("balance", account.getAccountBalance());

			return "playuser";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/web/sellcard/{authenticateid}")
	public String listSellCards(Model model, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			model.addAttribute("authenticateid", authenticateid);
			UserDto user = getUser(authenticateid);
			model.addAttribute("name", user.getName());
			AccountDto account = getAccount(authenticateid);
			model.addAttribute("balance", account.getAccountBalance());

			return "sellcardlist";
		} else {
			model.addAttribute("nok", "unreconnized user");
			return "index2";
		}
	}
	
	private CardDto getCard(Integer cardId, Integer authenticateid) {
		String cardurl = "http://localhost:8083/card/get/{id}/{authenticateid}";
		RestTemplate rescardTemplate = new RestTemplate();
		CardDto card = rescardTemplate.getForObject(cardurl, CardDto.class, cardId, authenticateid);
		return card;
		
	}
	
	private UserDto getUser(Integer authenticateid) {
		String userurl = "http://localhost:8082/user/{id}/{authenticateid}";
		RestTemplate restUserTemplate = new RestTemplate();
		UserDto user = restUserTemplate
				.getForObject(userurl, UserDto.class,authenticateid, authenticateid);
		return user;
		
	}
	
	private AccountDto getAccount(Integer authenticateid) {
		String accounturl = "http://localhost:8084/market/get/{authenticateid}";
		RestTemplate restAccuntTemplate = new RestTemplate();
		AccountDto account = restAccuntTemplate.getForObject(accounturl, AccountDto.class, authenticateid);
		return account;
		
	}
	
	private boolean verifyAuth(Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		Boolean isAuth = restTemplate.getForObject("http://localhost:8082/user/auth/" + authenticateid, Boolean.class);
		return isAuth;

	}

	public static class CardDto {
		private Integer id;
		private Integer price;

		public String name;
		public String family_name;
		public String img_src;
		public String description;
		public Integer hp;
		public Integer energy;
		public Integer attack;
		public Integer defense;

		public CardDto() {

		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

	}

	public static class AccountDto {
		private Integer userID;
		private List<Integer> cards;
		private Integer accountBalance;

		public AccountDto() {
		}

		public Integer getUserID() {
			return userID;
		}

		public void setUserID(Integer userID) {
			this.userID = userID;
		}

		public List<Integer> getCards() {
			return cards;
		}

		public void setCards(List<Integer> cards) {
			this.cards = cards;
		}

		public Integer getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(Integer accountBalance) {
			this.accountBalance = accountBalance;
		}
	}

	public static class UserDto {
		private Integer id;
		private String name;
		private String login;
		private String passwd;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public UserDto() {
		}
	}

}
