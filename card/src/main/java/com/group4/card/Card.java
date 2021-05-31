package com.group4.card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CARDS")
public final class Card {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer price;
	
	private String family_name;
	private String img_src;
	private String description;
	private Integer hp;
	private Integer energy;
	private Integer attack;
	private Integer defense;
	
	public Card() {
		super();
	}

	public Card(Integer id, String name, Integer price, String family_name, String img_src, String description,
			Integer hp, Integer energy, Integer attack, Integer defense) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.family_name = family_name;
		this.img_src = img_src;
		this.description = description;
		this.hp = hp;
		this.energy = energy;
		this.attack = attack;
		this.defense = defense;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
