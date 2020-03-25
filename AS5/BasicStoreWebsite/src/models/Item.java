package models;

import java.math.BigDecimal;

public class Item {
	private String id;
	private String name;
	private String imageFile;
	private BigDecimal price;
	
	public Item(String id, String name, String imageFile, BigDecimal price) {
		this.name = name;
		this.imageFile = imageFile;
		this.price = price;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getImageFile() {
		return imageFile;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}
	
	public boolean equals(Item it) {
		if(this == it) return true;
		if(it == null) return false;
		if(this.getClass() != it.getClass()) return false;
		return it.getId().equals(this.getId());
	}
	
	@Override
	public String toString() {
		return ("id - " + this.id + ", name - " + this.name + ", image file - "
				+ this.imageFile + ", price - " + this.price + ".");
	}
	
}
