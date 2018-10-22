package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Movie;
import models.MovieDatabase;
import views.MovieView;

public class MovieController extends JFrame{
	private MovieView view;
	private	MovieDatabase database;
	private Movie currentMovie;
	private AddWindowController addWindow;
	private EditWindowController editWindow;
	
	public MovieController(String title) throws IOException {
		super(title);
		database = new MovieDatabase();
		readFile();
		currentMovie = null;
		
		view = new MovieView(database);
		
		getContentPane().add(this.view);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 330);
		
		view.getButtonEdit().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editMovie();
				
			}
			
		});
		
		view.getButtonAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addMovie();
			}
			
		});
		
		view.getTableMovie().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectionEvent();
				
			}
			
		});
		
		view.getButtonRemove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelection();
				
			}
			
		});
	}
	
	protected void editMovie() {
		Movie editMovie = database.get(view.getTableMovie().getSelectedRow());
		int selectedIndex = view.getTableMovie().getSelectedRow();
		editWindow = new EditWindowController(database, editMovie, selectedIndex);
	
		editWindow.addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosed(WindowEvent arg0) {
				setEnabled(true);
				toFront();
				view.update();
			}
		});
		this.setEnabled(false);
		editWindow.setVisible(true);
	}

	protected void addMovie() {
		addWindow = new AddWindowController(database);
		addWindow.setVisible(true);
	
		addWindow.addWindowListener(new WindowAdapter() {
	
			@Override
			public void windowClosed(WindowEvent arg0) {
				setEnabled(true);
				toFront();
				view.update();
			}
		});
		setEnabled(false);
	}

	protected void selectionEvent() {
		currentMovie = database.get(view.getTableMovie().getSelectedRow());
		view.getCastModel().setRowCount(0);
		
		if (view.getTableMovie().getSelectedRow() >= 0) {
			for (int i = 0; i < currentMovie.getCastList().size(); ++i) {
				view.getCastModel().addRow(currentMovie.toCastRows(i));
			}
		}
		
		if (view.getTableMovie().getSelectedRow() < 0) {
			view.getButtonRemove().setEnabled(false);
			view.getButtonEdit().setEnabled(false);
		}
		else{
			view.getButtonRemove().setEnabled(true);
			view.getButtonEdit().setEnabled(true);
		}
	}

	protected void removeSelection() {
		int index;
		index = view.getTableMovie().getSelectedRow();
		database.removeIndex(index);
		view.update();
	}

	public static void main(String[] args) throws IOException {
		JFrame app = new MovieController("Movie Manager");
		app.setVisible(true);

	}
	
	public void readFile() throws IOException {

		Movie temp = new Movie();
		try {
			Scanner file = new Scanner(new File("input/movies.txt"));

			do {
				temp = new Movie();

				file.nextLine(); // title
				temp.setTitle(file.nextLine());

				file.nextLine(); // director
				temp.setDirector(file.nextLine());

				file.nextLine(); // genre
				temp.matchGenre(file.nextLine());

				file.nextLine(); // year
				temp.setYear(new Integer(file.nextLine()));

				file.nextLine(); // rating
				temp.setRating(new Double(file.nextLine()));

				file.nextLine(); // cast
				String castTemp = file.nextLine();

				temp.setCastList(database.toCastListArray(castTemp));

				file.nextLine(); // seperator

				database.add(temp);

			} while (file.hasNextLine());

			file.close();
		} catch (FileNotFoundException e) {

		} catch (NumberFormatException e) {
			System.err.println("Exceptional error: " + e);
		}
	}

}
