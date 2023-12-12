package com.pos.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pos.domain.Item;
import com.pos.repository.ItemRepository;

public class ItemRepositoryDummy implements ItemRepository {

	@Override
	public void save() {
		System.out.println("Item Succesfully added to Database!");	
	}

	@Override
	public Item findByItemCode(String itemCode) {
		Item item1 = new Item("001", 5000.0, "Aqua", "Minuman", false);
		Item item2 = new Item("002", 15000.0, "Beras", "Makanan", true);
		Item item3 = new Item("003", 10000.0, "Telur", "Minuman", false);
		Item item4 = new Item("004", 20000.0, "Kopi", "Makanan", true);
		Item item5 = new Item("005", 50000.0, "Baju", "Pakaian", true);
		Item item6 = new Item("006", 30000.0, "Marlboro", "Rokok", true);
		Item item7 = new Item("007", 32000.0, "Sampoerna Mild", "Rokok", true);
		Item item8 = new Item("008", 100000.0, "Celana", "Pakaian", true);
		
		List<Item> items = new ArrayList<Item>(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8));
		
		for(Item item : items) {
			if (item.getItemCode().equals(itemCode)) {
				return item;
			}
		}
		return null;
	}

}
