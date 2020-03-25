package models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<Item> items;
	List<Integer> itemAmount;
	
	public ShoppingCart() {
		items = new ArrayList<Item>();
		itemAmount = new ArrayList<Integer>();
	}

	public void addItem(Item it) {
		if(this.containsItem(it.getId())) return;
		items.add(it);
		itemAmount.add(1);
	}
	
	public void purgeUnusedItems() {
		for(int i = items.size() - 1; i >= 0; i--) {
			if(itemAmount.get(i) <= 0) {
				items.remove(i);
				itemAmount.remove(i);
			}
		}
	}

	public void removeItem(String id) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId().equals(id)) {
				items.remove(i);
				itemAmount.remove(i);
				break;
			}
		}
	}
	
	public void setItemAmount(String id, int amount) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId().equals(id)) {
				itemAmount.set(i, amount);
				break;
			}
		}
	}
	
	/*
	 * taken from 
	 * https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public String getTotalPrice() {
		double result = 0;
		for(int i = 0; i < items.size(); i++) {
			result += items.get(i).getPrice().doubleValue() * itemAmount.get(i);
		}
		return ""+ round(result, 2);
	}
	
	public int getAmount(String id) {
		int result = 0;
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId().equals(id)) {
				return itemAmount.get(i);
			}
		}
		return result;
	}
	
	public boolean containsItem(String id) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Item> getAllItems(){
		return new ArrayList<Item>(items);
	}
	
	public List<Integer> getAllAmounts(){
		return new ArrayList<Integer>(itemAmount);
	}
}
