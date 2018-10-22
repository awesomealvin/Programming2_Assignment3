package views;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Genres;
import models.Movie;
import models.MovieDatabase;

public class MovieView extends JPanel{
	
	private MovieDatabase database;
	private Movie currentMovie;
	
	private JTable tableMovie;
	private JScrollPane scTableMovie;
	private JScrollPane scTableCastList;
	
	private final String[] COLUMNCAST = {"Cast"};
	
	private final String[] COLUMN = {"Title", "Director", "Genre", "Year", "Rating"};
	
	private final String[] RATINGSCBOX = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	private JTextField textFieldSearch;
	private JTextField textFieldStartYear;
	private JTextField textFieldEndYear;
	private JTable tableCastList;
	
	private JButton buttonSearch;
	private JButton buttonRemove;
	private JButton buttonClear;
	private JComboBox<Genres> cBoxGenre;
	private JLabel labelGenre;
	
	private JComboBox<String> cBoxRating;
	
	private	JLabel labelRating;
	private JLabel labelRange;
	private JLabel labelYearRange;
	private JButton buttonRead;
	private JButton buttonSave;
	private JButton buttonAdd;
	private JButton buttonEdit;
	


	private DefaultTableModel model;
	private DefaultTableModel modelCast;
	
	
	public void update() {

		model.setRowCount(0);
		for (int i = 0; i < database.getSize(); ++i) {
			model.addRow(database.indexToRow(i));
		}


		if (database.getSize() < 1 || tableMovie.getSelectedRow() < 0) {
			buttonRemove.setEnabled(false);
		}
		
		if (database.getSize() < 1 || tableMovie.getSelectedRow() < 0) {
			buttonEdit.setEnabled(false);
		}
		
	}
	
	public MovieView(MovieDatabase database) throws IOException {
		this.database = database;
		currentMovie = null;
		setLayout(null);
		
		
		database = new MovieDatabase();

		model = new DefaultTableModel();
		model.setColumnIdentifiers(COLUMN);
		
		modelCast = new DefaultTableModel();
		modelCast.setColumnIdentifiers(COLUMNCAST);

		tableMovie = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		scTableMovie = new JScrollPane(tableMovie);
		scTableMovie.setBounds(12, 100, 590, 150);
		add(scTableMovie);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(12, 66, 286, 20);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		buttonSearch = new JButton("Search");
		buttonSearch.setBounds(310, 65, 92, 23);
		add(buttonSearch);
		
		buttonRemove = new JButton("Remove Selected");
		buttonRemove.setBounds(262, 258, 133, 23); //280, 258, 115, 23
		add(buttonRemove);
		
		buttonAdd = new JButton("Add Film");
		buttonAdd.setBounds(12, 258, 115, 23);
		add(buttonAdd);
		
		buttonEdit = new JButton("Edit Selected");
		buttonEdit.setBounds(137, 258, 115, 23);  //137, 258, 133, 23
		add(buttonEdit);
		
		buttonSave = new JButton("Save");
		buttonSave.setBounds(405, 258, 70, 23);
		add(buttonSave);
		
		buttonRead = new JButton("Read from File");
	
		buttonRead.setBounds(486, 258, 115, 23);
		add(buttonRead);
		
		textFieldStartYear = new JTextField();
		textFieldStartYear.setBounds(12, 35, 34, 20);
		add(textFieldStartYear);
		//textFieldStartYear.setColumns(10);
		
		labelYearRange = new JLabel("Year Range:");
		labelYearRange.setBounds(12, 12, 75, 16);
		add(labelYearRange);
		
		labelRange = new JLabel("to");
		labelRange.setBounds(55, 37, 11, 16);
		add(labelRange);
		
		textFieldEndYear = new JTextField();
		//textFieldEndYear.setColumns(10);
		textFieldEndYear.setBounds(76, 35, 34, 20);
		add(textFieldEndYear);
		
		tableCastList = new JTable(modelCast) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scTableCastList = new JScrollPane(tableCastList);
		scTableCastList.setBounds(609, 100, 163, 150);
		add(scTableCastList);
		
		labelRating = new JLabel("Min Rating:");
		labelRating.setBounds(126, 12, 68, 16);
		add(labelRating);
		
		cBoxRating = new JComboBox<String>(RATINGSCBOX);
		cBoxRating.setSelectedIndex(-1);
		cBoxRating.setBounds(122, 33, 72, 25);
		add(cBoxRating);
		
		buttonClear = new JButton("Clear");
		buttonClear.setToolTipText("Clear current filters");
		buttonClear.setBounds(310, 32, 92, 26);
		add(buttonClear);
		
		cBoxGenre = new JComboBox<Genres>(Genres.values());
		cBoxGenre.setBounds(206, 33, 92, 25);
		cBoxGenre.setSelectedIndex(-1);
		add(cBoxGenre);
		
		labelGenre = new JLabel("Genre:");
		labelGenre.setBounds(206, 12, 55, 16);
		add(labelGenre);
		update();
	}
	
	
	
	public JTable getTableMovie() {
		return tableMovie;
	}

	public JTextField getTextFieldSearch() {
		return textFieldSearch;
	}

	public JTextField getTextFieldStartYear() {
		return textFieldStartYear;
	}

	public JTextField getTextFieldEndYear() {
		return textFieldEndYear;
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}

	public JButton getButtonRemove() {
		return buttonRemove;
	}

	public JButton getButtonClear() {
		return buttonClear;
	}

	public JButton getButtonRead() {
		return buttonRead;
	}

	public JButton getButtonSave() {
		return buttonSave;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public DefaultTableModel getMovieModel() {
		return model;
	}
	
	public DefaultTableModel getCastModel() {
		return modelCast;
	}
	
	public JButton getButtonEdit() {
		return buttonEdit;
	}
	
	
}
