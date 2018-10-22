package views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Genres;
import models.Movie;

public class AddView extends JPanel{
	
	private Movie movie;
	private JButton buttonConfirm;
	private JLabel labelTitle;
	private JTextField textFieldTitle;
	private JLabel labelDirector;
	private JTextField textFieldDirector;
	private JLabel labelYear;
	private JTextField textFieldYear;
	private JLabel labelRating;
	private JTextField textFieldRating;
	private JLabel labelGenre;
	private JComboBox<Genres> comboBoxGenre;
	private JList<String> listCastList;
	private JLabel labelCastList;
	private JTextField textFieldAdd;
	private JLabel labelAddCast;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JButton buttonDiscard;
	private JButton buttonClearAll;
	
	public AddView(Movie movie) {
		this.movie = movie;
		setLayout(null);
		
		labelTitle = new JLabel("Title:");
		labelTitle.setBounds(10, 11, 46, 14);
		add(labelTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(10, 29, 150, 20);
		add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		labelDirector = new JLabel("Director:");
		labelDirector.setBounds(10, 60, 62, 14);
		add(labelDirector);
		
		textFieldDirector = new JTextField();
		textFieldDirector.setBounds(10, 78, 150, 20);
		add(textFieldDirector);
		textFieldDirector.setColumns(10);
		
		labelYear = new JLabel("Year Released:");
		labelYear.setBounds(10, 109, 89, 14);
		add(labelYear);
		
		textFieldYear = new JTextField();
		textFieldYear.setBounds(108, 106, 52, 20);
		add(textFieldYear);
		textFieldYear.setColumns(10);
		
		labelRating = new JLabel("Average Rating:");
		labelRating.setBounds(10, 135, 89, 14);
		add(labelRating);
		
		textFieldRating = new JTextField();
		textFieldRating.setColumns(10);
		textFieldRating.setBounds(108, 135, 52, 20);
		add(textFieldRating);
		
		labelGenre = new JLabel("Genre:");
		labelGenre.setBounds(10, 160, 55, 16);
		add(labelGenre);
		
		comboBoxGenre = new JComboBox<Genres>(Genres.values());
		comboBoxGenre.setBounds(10, 180, 150, 25);
		comboBoxGenre.setSelectedItem(Genres.UNDEFINED);
		add(comboBoxGenre);
		
		listCastList = new JList<String>();
		listCastList.setBounds(172, 30, 150, 175);
		add(listCastList);
		
		labelCastList = new JLabel("Cast List:");
		labelCastList.setBounds(174, 10, 55, 16);
		add(labelCastList);
		
		textFieldAdd = new JTextField();
		textFieldAdd.setBounds(336, 31, 150, 20);
		add(textFieldAdd);
		
		labelAddCast = new JLabel("Add Cast:");
		labelAddCast.setBounds(336, 10, 55, 16);
		add(labelAddCast);
		
		buttonAdd = new JButton("Add Cast Member");
		buttonAdd.setBounds(336, 54, 150, 26);
		add(buttonAdd);
		
		buttonRemove = new JButton("Remove Selected");
		buttonRemove.setBounds(336, 86, 150, 26);
		add(buttonRemove);
		
		buttonConfirm = new JButton("Confirm Movie");
		buttonConfirm.setBounds(336, 179, 150, 26);
		add(buttonConfirm);
		
		buttonDiscard = new JButton("Discard Movie");
		buttonDiscard.setBounds(336, 147, 150, 26);
		add(buttonDiscard);
		
		buttonClearAll = new JButton("Clear All");
		buttonClearAll.setBounds(172, 217, 150, 26);
		add(buttonClearAll);
		
		update();
	}
	
	public void update() {
		listCastList.setListData(movie.toCastList());
		buttonRemove.setEnabled((listCastList.isSelectionEmpty())?false:true);
		buttonAdd.setEnabled((textFieldAdd.getText().isEmpty())?false:true);
	}

	public Movie getMovie() {
		return movie;
	}

	public JButton getButtonConfirm() {
		return buttonConfirm;
	}

	public JLabel getLabelTitle() {
		return labelTitle;
	}

	public JTextField getTextFieldTitle() {
		return textFieldTitle;
	}

	public JLabel getLabelDirector() {
		return labelDirector;
	}

	public JTextField getTextFieldDirector() {
		return textFieldDirector;
	}

	public JLabel getLabelYear() {
		return labelYear;
	}

	public JTextField getTextFieldYear() {
		return textFieldYear;
	}

	public JLabel getLabelRating() {
		return labelRating;
	}

	public JTextField getTextFieldRating() {
		return textFieldRating;
	}

	public JLabel getLabelGenre() {
		return labelGenre;
	}

	public JComboBox getComboBoxGenre() {
		return comboBoxGenre;
	}

	public JList<String> getListCastList() {
		return listCastList;
	}

	public JLabel getLabelCastList() {
		return labelCastList;
	}

	public JTextField getTextFieldAdd() {
		return textFieldAdd;
	}

	public JLabel getLabelAddCast() {
		return labelAddCast;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public JButton getButtonRemove() {
		return buttonRemove;
	}

	public JButton getButtonDiscard() {
		return buttonDiscard;
	}

	public JButton getButtonClearAll() {
		return buttonClearAll;
	}
	
	
}
