package com.group4.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CardController {
	@Autowired
	CardService cardService;

	@RequestMapping(method = RequestMethod.GET, value = "/card/get/{id}/{authenticateid}")
	public Card getCard(@PathVariable Integer id, @PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			Card c = cardService.getCard(id);
			return c;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/list/{authenticateid}")
	public List<Card> listCard(@PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			return cardService.getAllCards();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/card/user/list/{authenticateid}")
	public List<Card> listUserCards(@PathVariable Integer authenticateid) {
		if (verifyAuth(authenticateid)) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Integer[]> response = restTemplate.getForEntity("http://localhost:8084/market/cards/"+authenticateid, Integer[].class);
			Integer[] cardIdList = response.getBody();
			List<Card> cardList = new ArrayList<Card>();
			for (Integer id : cardIdList) {
				Card card = cardService.getCard(id);
				if (card != null) {
					cardList.add(card);
				}
			}

			return cardList;
		}
		return null;
	}
	
	private boolean verifyAuth(Integer authenticateid) {
		RestTemplate restTemplate = new RestTemplate();
		Boolean isAuth = restTemplate.getForObject("http://localhost:8082/user/auth/"+authenticateid, Boolean.class);
		return isAuth;

	}

}
