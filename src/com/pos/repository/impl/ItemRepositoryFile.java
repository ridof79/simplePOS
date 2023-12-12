package com.pos.repository.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pos.domain.Item;
import com.pos.repository.ItemRepository;

public class ItemRepositoryFile implements ItemRepository{
	
	private List<Item> items = new ArrayList<Item>();
	
	public ItemRepositoryFile() {
		try {
			BufferedReader bufferedReader = new BufferedReader (new FileReader("items.txt"));
			String item;
			while((item = bufferedReader.readLine()) != null) {
				String[] itemToken = item.split(",");
				boolean isTaxable = taxableCheck(itemToken[4]);
				addItem(itemToken, isTaxable);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addItem(String[] itemToken, boolean isTaxable) {
		items.add(new Item(itemToken[0], Double.parseDouble(itemToken[1]), itemToken[2], itemToken[3], isTaxable));
	}

	private boolean taxableCheck(String taxable) {
		boolean isTaxable = false;
		if(taxable.equals("true")) {
			isTaxable = true;
		}
		return isTaxable;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item findByItemCode(String itemCode) {		
		for(Item item : items) {
			if (item.getItemCode().equals(itemCode)) {
				return item;
			}
		}	
		return null;
	}

}
