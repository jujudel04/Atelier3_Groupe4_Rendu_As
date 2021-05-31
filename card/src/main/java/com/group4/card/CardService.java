package com.group4.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardService {
	@Autowired
	CardRepository cardRepository;

	CardService() {
	}

	public List<Card> getAllCards() {
		List<Card> result = new ArrayList<Card>();
		cardRepository.findAll().forEach(result::add);
		return result;
	}

	public Card getCard(Integer id) {
		Optional<Card> hOpt = cardRepository.findById(id);
		if (hOpt.isPresent()) {
			return hOpt.get();
		}
		return null;
	}

	public void saveCard(Card c) {
		cardRepository.save(c);
	}
}
