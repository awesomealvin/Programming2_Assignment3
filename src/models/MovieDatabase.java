package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {
	private static DecimalFormat yrFmt = new DecimalFormat("0.0");
	private ArrayList<Movie> movies;
	
	public MovieDatabase() throws IOException {
		movies = new ArrayList<Movie>();
	}
	
	public void add(Movie movie) {
		movies.add(movie);
	}
	
	public void remove(Movie movie) {
		movies.remove(movie);
	}
	
	public void removeIndex(int index) {
		movies.remove(index);
	}
	
	public int getSize() {
		return movies.size();
	}
	
	public Movie get(int index) {
		
		if(index >= 0 && index < movies.size())
		{
			return movies.get(index);
		}
		return null;
	}
	
	public Movie[] toList() {
		Movie[] list = new Movie[movies.size()];
		
		for (int i = 0; i < list.length; ++i) {
			list[i] = movies.get(i);
		}
		
		return list;
	}
	
	public Object[] indexToRow(int index) {
		Object[] row = new Object[5];
		row[0] = movies.get(index).getTitle();
		row[1] = movies.get(index).getDirector();
		row[2] = movies.get(index).genreToString();
		row[3] = movies.get(index).getYear();
		row[4] = yrFmt.format(movies.get(index).getRating());
		
		return row;
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public ArrayList<String> toCastListArray(String castTemp) {
		String[] toArrayList = castTemp.split(",");
		
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < toArrayList.length; ++i){
			list.add(toArrayList[i]);
		}
		
		return list;
	}
	
}
