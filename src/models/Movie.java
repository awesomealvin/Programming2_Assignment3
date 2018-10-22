package models;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Movie {
	private static DecimalFormat yrFmt = new DecimalFormat("0.0");
	private String title;
	private String director;
	private Genres genre;
	private int year;
	private double rating;
	private ArrayList<String> castList;
	
	public Movie(String title, String director, Genres genre, int year, double rating, ArrayList<String> castList) {
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.year = year;
		this.rating = rating;
		this.castList = castList;
	}
	
	public Movie() {
		castList = new ArrayList<String>();
		this.title = "Untitled";
		this.director = "Unnamed";
		this.genre = Genres.UNDEFINED;
		this.year = 0;
		this.rating = 0.0;
	}

	public void addCast(String newCast) {
		castList.add(newCast);
	}
	
	public void removeCast(int index) {
		castList.remove(index);
	}
	
	public Object[] toCastRows(int index) {
		Object[] cast = new Object[castList.size()];
		

		cast[0] = castList.get(index);
		
		return cast;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Genres getGenre() {
		return genre;
	}
	
	public String genreToString() {
		return genre.toString();
	}

	public void setGenre(Genres genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public ArrayList<String> getCastList() {
		return castList;
	}

	public void setCastList(ArrayList<String> castList) {
		this.castList = castList;
	}
	
	public String[] toCastList() {
		String[] list = new String[castList.size()];
		
		for (int i = 0; i < list.length; ++i) {
			list[i] = castList.get(i);
		}
		return list;
	}
	
	public void matchGenre(String string) {
		for (Genres g : Genres.values()) {
			if (string.equalsIgnoreCase(g.name())) {
				this.genre = g;
				return;
			}
			else {
				this.genre = Genres.UNDEFINED;
			}
		}
	}



	
	
}
