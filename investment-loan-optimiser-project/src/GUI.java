import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

@SuppressWarnings("deprecation")
public class GUI implements Observer {

	private Locale locale = Locale.US;
	private NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
	private JFrame frame;
	private JFormattedTextField nameEntry;
	private JLabel typeLabel;
	private JComboBox typeComboBox;
	private JLabel minPaymentLabel;
	private JFormattedTextField minPaymentEntry;
	private JFormattedTextField balanceEntry;
	private JLabel balanceLabel;
	private JFormattedTextField interestRateEntry;
	private JLabel interestRateLabel;
	private JButton addButton;
	private JButton confirmButton;
	private JFormattedTextField nameResults;
	private JFormattedTextField minPaymentResults;
	private JFormattedTextField balanceResults;
	private JFormattedTextField interestRateResults;
	private JLabel title;
	private JLabel nameInvalid;
	private JLabel minPaymentInvalid;
	private JLabel balanceInvalid;
	private JLabel interestRateInvalid;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    UIManager.setLookAndFeel( new FlatIntelliJLaf() );
					GUI window = new GUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 734, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 150, 150, 20, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		//=====================================================================
		//	TITLE
		//=====================================================================
		
		
		title = new JLabel("Loan & Investment Optimiser");
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 4;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 1;
		frame.getContentPane().add(title, gbc_title);
		
		//=====================================================================
		//	ACCOUNT NAME
		//=====================================================================
		
		//Account Name Label 
		
		JLabel nameLabel = new JLabel("Account Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 3;
		frame.getContentPane().add(nameLabel, gbc_nameLabel);
		
		//Account Name Text Entry
		
		nameEntry = new JFormattedTextField();
		nameEntry.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent de) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateNameFields(nameEntry.getText(), nameResults, nameInvalid);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateNameFields(nameEntry.getText(), nameResults, nameInvalid);
			}
        });
		GridBagConstraints gbc_nameEntry = new GridBagConstraints();
		gbc_nameEntry.insets = new Insets(0, 0, 5, 5);
		gbc_nameEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameEntry.gridx = 2;
		gbc_nameEntry.gridy = 3;
		frame.getContentPane().add(nameEntry, gbc_nameEntry);
		nameEntry.setColumns(10);
		
		//Name Results
		
		nameResults = new JFormattedTextField();
		nameResults.setEditable(false);
		nameResults.setText("Account 1");
		GridBagConstraints gbc_nameResults = new GridBagConstraints();
		gbc_nameResults.insets = new Insets(0, 0, 5, 5);
		gbc_nameResults.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameResults.gridx = 3;
		gbc_nameResults.gridy = 3;
		frame.getContentPane().add(nameResults, gbc_nameResults);
		
		//Name Invalid Message
		
		nameInvalid = new JLabel("");
		nameInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_nameInvalidInput = new GridBagConstraints();
		gbc_nameInvalidInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameInvalidInput.insets = new Insets(0, 0, 5, 5);
		gbc_nameInvalidInput.gridx = 4;
		gbc_nameInvalidInput.gridy = 3;
		frame.getContentPane().add(nameInvalid, gbc_nameInvalidInput);
		
		
		//=====================================================================
		//	ACCOUNT TYPE
		//=====================================================================
		
		
		//Account Type Label
		
		typeLabel = new JLabel("Account Type");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 1;
		gbc_typeLabel.gridy = 4;
		frame.getContentPane().add(typeLabel, gbc_typeLabel);
		
		//Account Type Combo Box
		
		String[] comboBoxEntries = { "Investment", "Loan" };
		typeComboBox = new JComboBox(comboBoxEntries);
		
		typeComboBox.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedBook = (String) combo.getSelectedItem();
		 
		        if (selectedBook.equals("Loan")) {
		            minPaymentEntry.setEnabled(true);
		            updateCurrencyFields(minPaymentEntry.getText(), minPaymentResults, minPaymentInvalid);
		        } else {
		        	minPaymentEntry.setEnabled(false);
		        	minPaymentResults.setText("$0.00");
		        }
		    }
		});
		
		
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.gridwidth = 2;
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 2;
		gbc_typeComboBox.gridy = 4;
		frame.getContentPane().add(typeComboBox, gbc_typeComboBox);
		
		//=====================================================================
		//	MINIMUM PAYMENT
		//=====================================================================
		
		//Min Payment Label
		
		minPaymentLabel = new JLabel("Minimum Annual Payment (If Loan)");
		GridBagConstraints gbc_minPaymentLabel = new GridBagConstraints();
		gbc_minPaymentLabel.anchor = GridBagConstraints.EAST;
		gbc_minPaymentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentLabel.gridx = 1;
		gbc_minPaymentLabel.gridy = 5;
		frame.getContentPane().add(minPaymentLabel, gbc_minPaymentLabel);
		
		//Min Payment Text Field
		
		minPaymentEntry = new JFormattedTextField();
		minPaymentEntry.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent de) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateCurrencyFields(minPaymentEntry.getText(), minPaymentResults, minPaymentInvalid);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateCurrencyFields(minPaymentEntry.getText(), minPaymentResults, minPaymentInvalid);
			}
        });
		
		minPaymentEntry.setEnabled(false);
		GridBagConstraints gbc_minPaymentEntry = new GridBagConstraints();
		gbc_minPaymentEntry.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentEntry.gridx = 2;
		gbc_minPaymentEntry.gridy = 5;
		frame.getContentPane().add(minPaymentEntry, gbc_minPaymentEntry);
		minPaymentEntry.setColumns(10);
		

		
		//Min Payment Results
		
		minPaymentResults = new JFormattedTextField();
		minPaymentResults.setEditable(false);
		minPaymentResults.setText("$0.00");
		GridBagConstraints gbc_minPaymentResults = new GridBagConstraints();
		gbc_minPaymentResults.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentResults.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentResults.gridx = 3;
		gbc_minPaymentResults.gridy = 5;
		frame.getContentPane().add(minPaymentResults, gbc_minPaymentResults);
		
		//Min Payment Invalid
		
		minPaymentInvalid = new JLabel("");
		minPaymentInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_annualPaymentInvalid = new GridBagConstraints();
		gbc_annualPaymentInvalid.fill = GridBagConstraints.HORIZONTAL;
		gbc_annualPaymentInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_annualPaymentInvalid.gridx = 4;
		gbc_annualPaymentInvalid.gridy = 5;
		frame.getContentPane().add(minPaymentInvalid, gbc_annualPaymentInvalid);
		
		//=====================================================================
		//	BALANCE
		//=====================================================================
		
		// Label
		
		balanceLabel = new JLabel("Balance");
		GridBagConstraints gbc_balanceLabel = new GridBagConstraints();
		gbc_balanceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_balanceLabel.anchor = GridBagConstraints.EAST;
		gbc_balanceLabel.gridx = 1;
		gbc_balanceLabel.gridy = 6;
		frame.getContentPane().add(balanceLabel, gbc_balanceLabel);
		
		// Entry Text Field
		
		balanceEntry = new JFormattedTextField();
		balanceEntry.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent de) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateCurrencyFields(balanceEntry.getText(), balanceResults, balanceInvalid);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateCurrencyFields(balanceEntry.getText(), balanceResults, balanceInvalid);
			}
        });

		
		
		GridBagConstraints gbc_balanceEntry = new GridBagConstraints();
		gbc_balanceEntry.insets = new Insets(0, 0, 5, 5);
		gbc_balanceEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceEntry.gridx = 2;
		gbc_balanceEntry.gridy = 6;
		frame.getContentPane().add(balanceEntry, gbc_balanceEntry);
		balanceEntry.setColumns(10);
		
		//Results Text Field
		
		balanceResults = new JFormattedTextField();
		balanceResults.setEditable(false);
		balanceResults.setText("$0.00");
		GridBagConstraints gbc_balanceResults = new GridBagConstraints();
		gbc_balanceResults.insets = new Insets(0, 0, 5, 5);
		gbc_balanceResults.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceResults.gridx = 3;
		gbc_balanceResults.gridy = 6;
		frame.getContentPane().add(balanceResults, gbc_balanceResults);
		
		// Invalid Label
		
		balanceInvalid = new JLabel("");
		balanceInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_balanceInvalid = new GridBagConstraints();
		gbc_balanceInvalid.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_balanceInvalid.gridx = 4;
		gbc_balanceInvalid.gridy = 6;
		frame.getContentPane().add(balanceInvalid, gbc_balanceInvalid);
		
		//=====================================================================
		//	INTEREST RATE
		//=====================================================================
		
		// Label
		
		interestRateLabel = new JLabel("Annual Interest Rate (%)");
		GridBagConstraints gbc_interestRateLabel = new GridBagConstraints();
		gbc_interestRateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateLabel.anchor = GridBagConstraints.EAST;
		gbc_interestRateLabel.gridx = 1;
		gbc_interestRateLabel.gridy = 7;
		frame.getContentPane().add(interestRateLabel, gbc_interestRateLabel);
		
		// Entry Text Field
		
		interestRateEntry = new JFormattedTextField();
		interestRateEntry.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent de) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updatePercentageFields(interestRateEntry.getText(), interestRateResults, interestRateInvalid);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updatePercentageFields(interestRateEntry.getText(), interestRateResults, interestRateInvalid);
			}
        });
		GridBagConstraints gbc_interestRateEntry = new GridBagConstraints();
		gbc_interestRateEntry.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateEntry.gridx = 2;
		gbc_interestRateEntry.gridy = 7;
		frame.getContentPane().add(interestRateEntry, gbc_interestRateEntry);
		interestRateEntry.setColumns(10);
		
		// Result Text Field
		
		interestRateResults = new JFormattedTextField();
		interestRateResults.setEditable(false);
		interestRateResults.setText("0.00%");
		GridBagConstraints gbc_interestRateResults = new GridBagConstraints();
		gbc_interestRateResults.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateResults.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateResults.gridx = 3;
		gbc_interestRateResults.gridy = 7;
		frame.getContentPane().add(interestRateResults, gbc_interestRateResults);
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// Invalid Label
		
		interestRateInvalid = new JLabel("");
		interestRateInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_interestRateInvalid = new GridBagConstraints();
		gbc_interestRateInvalid.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateInvalid.gridx = 4;
		gbc_interestRateInvalid.gridy = 7;
		frame.getContentPane().add(interestRateInvalid, gbc_interestRateInvalid);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.gridwidth = 2;
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 8;
		frame.getContentPane().add(addButton, gbc_addButton);
		
		//=====================================================================
		//	CONFIRM BUTTON
		//=====================================================================
		
		confirmButton = new JButton("Confirm");
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.gridwidth = 2;
		gbc_confirmButton.insets = new Insets(0, 0, 5, 5);
		gbc_confirmButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmButton.gridx = 2;
		gbc_confirmButton.gridy = 9;
		frame.getContentPane().add(confirmButton, gbc_confirmButton);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	public boolean validNumber(String text) {
		try {
			double i = Double.parseDouble(text);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean validName(String text) {
		if(!text.matches(".*[a-zA-Z].*")) {
			return false;
		}
		return true;
	}
	
	public void updateCurrencyFields(String entry, JFormattedTextField resultsField, JLabel invalidLabel) {
		if(entry.isEmpty() || entry.isBlank()) {
			invalidLabel.setText("");
			resultsField.setText("$0.00");
		} else if(validNumber(entry)){
			invalidLabel.setText("");
			resultsField.setText(dollarFormat.format(Double.parseDouble(entry)));
		} else {
			invalidLabel.setText("Invalid Number.");
			resultsField.setText("$0.00");
		}
	}
	
	public void updatePercentageFields(String entry, JFormattedTextField resultsField, JLabel invalidLabel) {
		
		if(entry.isEmpty() || entry.isBlank()) {
			invalidLabel.setText("");
			resultsField.setText("0.00%");
		} else if(validNumber(entry)){
			invalidLabel.setText("");
			String percentageText = String.format("%.2f", Double.parseDouble(entry)) + "%";
			resultsField.setText(percentageText);
		} else {
			invalidLabel.setText("Invalid Percentage.");
			resultsField.setText("0.00%");
		}
	}
	
	
	
	public void updateNameFields(String entry, JFormattedTextField resultsField, JLabel invalidLabel) {
		if(entry.isEmpty() || entry.isBlank()) {
			invalidLabel.setText("Invalid Name.");
			resultsField.setText("Default");
		} else if(validName(entry)){
			invalidLabel.setText("");
			resultsField.setText(entry);
		} else {
			invalidLabel.setText("Invalid Name.");
			resultsField.setText("Default");
		}
	}
}
