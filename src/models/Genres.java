package models;

public enum Genres {
	ACTION("Action"), ADVENTURE("Adventure"), COMEDY("Comedy"), CRIME("Crime"), DRAMA("Drama"), FANTASY("Fantasy"), HISTORICAL("Historical"), HORROR("Horror"), ROMANCE("Romance"), THRILLER("Thriller"), UNDEFINED("Undefined");
	
	
	private String name;
	
	private Genres(String name) {
		this.name = name;
	}
	
	public boolean isEqual(String nameCompare) {
		return (nameCompare.equals(this.name))?true:false;
	}
	
	public String toString() {
		return this.name;
	}
}
