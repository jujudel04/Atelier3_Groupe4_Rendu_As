package groupe4.market.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import groupe4.market.model.Account;
import groupe4.market.service.MarketService;

@RestController
public class MarketController {
	@Autowired
	MarketService marketService;

	@RequestMapping(method = RequestMethod.GET, value = "/market/buy/{cardid}/{price}/{authenticateid}")
	public void buyCard(@PathVariable Integer cardid, @PathVariable Integer price, @PathVariable Integer authenticateid) {
		//no user verification because not mapped by proxy
		marketService.addCard(cardid, price, authenticateid);
//		System.out.println("user cad list:"+getAccountCardIdList(authenticateid).size());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/market/sell/{cardid}/{price}/{authenticateid}")
	public void sellCard(@PathVariable Integer cardid, @PathVariable Integer price, @PathVariable Integer authenticateid) {
		marketService.removeCard(cardid, price, authenticateid);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/market/get/{authenticateid}")
	public Account getAccount(@PathVariable Integer authenticateid) {
		return marketService.getAccount(authenticateid);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/market/cards/{authenticateid}")
	public List<Integer> getAccountCardIdList(@PathVariable Integer authenticateid) {
		Account acc = marketService.getAccount(authenticateid);
		return acc.getUserCardList();
	}
}
