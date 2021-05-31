package groupe4.market.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue
	private Integer userid;
	@Column(name = "accountbalance")
	private Integer accountBalance;
	// @OneToMany(mappedBy="cards")
	@Column(name = "cards")
	private String cards; // save as format: "id1:id2:id3" String::split(':')

	public Account() {
		super();
	}

	public Account(Integer userid, String name, String login, String passwd) {
		this();
		this.userid = userid;
		this.cards = "";
		this.accountBalance = 5000; // start with 5000

	}

	public Integer getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void updateBalance(Integer update) {
		this.accountBalance += update;
	}

	public void addCard(Integer card_id) {
		this.cards = this.cards + card_id + ":";
	}

	public void removeCard(Integer card_id) {
		List<Integer> listc = this.getUserCardList();
		listc.remove(card_id);
		this.cards = "";
		for (Integer id : listc) {
			this.addCard(id);
		}
	}

	public List<Integer> getUserCardList() {
		if (this.cards.length() > 0) {
			List<String> list = Arrays.asList(this.cards.split(":"));
			
			return list.stream().map(Integer::parseInt).collect(Collectors.toList());
		} else {
			ArrayList<Integer> toto = new ArrayList<Integer>();
			return toto;
		}
	}

	public Integer getUserId() {
		return userid;
	}

	public void setUserId(Integer userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return "Account [" + this.userid + "]: balance:" + this.accountBalance + ", card ids:" + this.cards;
	}

}
