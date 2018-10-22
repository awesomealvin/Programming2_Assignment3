package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Genres;
import models.Movie;
import models.MovieDatabase;
import views.EditView;

public class EditWindowController extends JFrame{
	
	private int selectedIndex;
	private Movie editMovie;
	private MovieDatabase database;
	private EditView view;
	
	public EditWindowController(MovieDatabase database, Movie editMovie, int selectedIndex) {
		super("Edit Movie");
		this.selectedIndex = selectedIndex;
		this.database = database;
		this.editMovie = editMovie;
		
		view = new EditView(editMovie);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 253);
		
		getContentPane().add(view);
		
		view.getListCastList().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				listSelectionEvent();
			}
		});
		
		view.getButtonDiscard().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				discardMovie();
			}
		});
		
		view.getButtonAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCast();	
			}	
		});
		
		view.getButtonRemove().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeCast();	
			}
		});
		
		view.getButtonConfirm().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmNewMovie();		
			}	
		});	
		
		view.getTextFieldAdd().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				view.update();	
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				view.update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				view.update();
			}	
		});
	
	}
	
	protected void listSelectionEvent() {
		if (view.getListCastList().getSelectedIndex() >= 0){
			view.getButtonRemove().setEnabled(true);
		}
	}

	protected void discardMovie() {
		dispose();
	}

	protected void confirmNewMovie() {

		if (!view.getTextFieldTitle().getText().isEmpty()) {
			editMovie.setTitle(view.getTextFieldTitle().getText());
		}
		
		if (!view.getTextFieldDirector().getText().isEmpty()) {
			editMovie.setDirector(view.getTextFieldDirector().getText());
		}
		
		
		try {
			if (!view.getTextFieldYear().getText().isEmpty()) {
				editMovie.setYear(new Integer(view.getTextFieldYear().getText()));
			}
		} catch (NumberFormatException e){
			System.err.println("Exceptional event: "+e);
			editMovie.setYear(0);
		}
		
		try {
			if (!view.getTextFieldRating().getText().isEmpty()) {
				editMovie.setRating(new Double(view.getTextFieldRating().getText()));
			}
		} catch (NumberFormatException e) {
			System.err.println("Exceptional event: "+e);
			editMovie.setRating(0);
		}

		
		ArrayList<String> cast = new ArrayList<String>();
		int listSize = view.getListCastList().getSelectionModel().getMaxSelectionIndex();
		for (int i = 0; i < listSize; ++i) {
			cast.add(view.getListCastList().getSelectedValue());
		}

		Genres genre = (Genres) view.getComboBoxGenre().getSelectedItem();
		editMovie.setGenre(genre);
		
		database.getMovies().set(selectedIndex, editMovie);
		dispose();
		
	}

	protected void removeCast() {
		int index = view.getListCastList().getSelectedIndex();
		editMovie.removeCast(index);
		view.update();
		
	}
	protected void addCast() {
		
		String newCast = view.getTextFieldAdd().getText();
		newCast = newCast.trim();
		if (!newCast.isEmpty()) {
			editMovie.addCast(newCast);
			view.getTextFieldAdd().setText("");
			view.update();
		}
		
	}
}
