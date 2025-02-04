package edu.baylor.cs.junit.demo.app.objects;

/**
 * Song entity
 * 
 * @author cerny
 *
 */
public class Song {
	private Integer index;
	private Float cost;
	private String name;

	public Song(Integer index, String name, Float cost) {
		this.index = index;
		this.name = name;
		this.cost = cost;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
}