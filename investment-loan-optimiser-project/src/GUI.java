import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class GUI {

	private Locale locale = Locale.US;
	private NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
	private JFrame frmLoanInvestment;
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
	private JFormattedTextField nameResult;
	private JFormattedTextField minPaymentResult;
	private JFormattedTextField balanceResult;
	private JFormattedTextField interestRateResult;

	private JScrollPane scrollPane;
	private JTable table;

	private AccountForm form;
	private MainProgram mainProgram;
	private JSeparator separator;
	private JFormattedTextField incomeEntry;
	private JFormattedTextField totalPeriodsEntry;
	private JLabel incomeLabel;
	private JLabel totalPeriodsLabel;
	private JFormattedTextField totalPeriodsResult;
	private JFormattedTextField incomeResult;
	private JLabel nameInvalid;
	private JLabel minPaymentInvalid;
	private JLabel balanceInvalid;
	private JLabel interestRateInvalid;
	private JLabel incomeInvalid;
	private JLabel totalPeriodsInvalid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatIntelliJLaf());
					GUI window = new GUI();
					window.frmLoanInvestment.setVisible(true);

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
		form = new AccountForm();
		mainProgram = new MainProgram();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoanInvestment = new JFrame();
		frmLoanInvestment.setTitle("Loan & Investment Optimiser");
		frmLoanInvestment.setBounds(100, 100, 915, 554);
		frmLoanInvestment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 150, 150, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		frmLoanInvestment.getContentPane().setLayout(gridBagLayout);

		// =====================================================================
		// ACCOUNT NAME
		// =====================================================================

		// Account Name Label

		JLabel nameLabel = new JLabel("Account Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 1;
		frmLoanInvestment.getContentPane().add(nameLabel, gbc_nameLabel);

		// Account Name Text Entry

		nameEntry = new JFormattedTextField();
		nameEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				form.setName(nameEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				form.setName(nameEntry.getText());
				update();
			}
		});
		GridBagConstraints gbc_nameEntry = new GridBagConstraints();
		gbc_nameEntry.insets = new Insets(0, 0, 5, 5);
		gbc_nameEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameEntry.gridx = 2;
		gbc_nameEntry.gridy = 1;
		frmLoanInvestment.getContentPane().add(nameEntry, gbc_nameEntry);
		nameEntry.setColumns(10);

		// Name Results

		nameResult = new JFormattedTextField();
		nameResult.setEditable(false);
		nameResult.setText("Account 1");
		GridBagConstraints gbc_nameResult = new GridBagConstraints();
		gbc_nameResult.insets = new Insets(0, 0, 5, 5);
		gbc_nameResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameResult.gridx = 3;
		gbc_nameResult.gridy = 1;
		frmLoanInvestment.getContentPane().add(nameResult, gbc_nameResult);
		
		nameInvalid = new JLabel("");
		nameInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_nameInvalid = new GridBagConstraints();
		gbc_nameInvalid.gridwidth = 2;
		gbc_nameInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_nameInvalid.gridx = 2;
		gbc_nameInvalid.gridy = 2;
		frmLoanInvestment.getContentPane().add(nameInvalid, gbc_nameInvalid);

		// =====================================================================
		// ACCOUNT TYPE
		// =====================================================================

		// Account Type Label

		typeLabel = new JLabel("Account Type");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 1;
		gbc_typeLabel.gridy = 3;
		frmLoanInvestment.getContentPane().add(typeLabel, gbc_typeLabel);

		// Account Type Combo Box

		String[] comboBoxEntries = { "Investment", "Loan" };
		typeComboBox = new JComboBox(comboBoxEntries);

		typeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();

				if (selectedBook.equals("Loan")) {
					minPaymentEntry.setEnabled(true);
					form.setType(Loan.class);
					form.setMinimumPayment(minPaymentEntry.getText());
					form.setBalance(balanceEntry.getText());
					update();
				} else {
					minPaymentEntry.setEnabled(false);
					form.setType(Investment.class);
					form.setMinimumPayment("0");
					form.setBalance(balanceEntry.getText());
					update();
				}
			}
		});

		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.gridwidth = 2;
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 2;
		gbc_typeComboBox.gridy = 3;
		frmLoanInvestment.getContentPane().add(typeComboBox, gbc_typeComboBox);

		// =====================================================================
		// MINIMUM PAYMENT
		// =====================================================================

		// Min Payment Label

		minPaymentLabel = new JLabel("Minimum Annual Payment (If Loan)");
		GridBagConstraints gbc_minPaymentLabel = new GridBagConstraints();
		gbc_minPaymentLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentLabel.gridx = 1;
		gbc_minPaymentLabel.gridy = 4;
		frmLoanInvestment.getContentPane().add(minPaymentLabel, gbc_minPaymentLabel);

		// Min Payment Text Field

		minPaymentEntry = new JFormattedTextField();
		minPaymentEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				form.setMinimumPayment(minPaymentEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				form.setMinimumPayment(minPaymentEntry.getText());
				update();
			}
		});

		minPaymentEntry.setEnabled(false);
		GridBagConstraints gbc_minPaymentEntry = new GridBagConstraints();
		gbc_minPaymentEntry.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentEntry.gridx = 2;
		gbc_minPaymentEntry.gridy = 4;
		frmLoanInvestment.getContentPane().add(minPaymentEntry, gbc_minPaymentEntry);
		minPaymentEntry.setColumns(10);

		// Min Payment Results

		minPaymentResult = new JFormattedTextField();
		minPaymentResult.setEditable(false);
		minPaymentResult.setText("$0.00");
		GridBagConstraints gbc_minPaymentResult = new GridBagConstraints();
		gbc_minPaymentResult.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentResult.gridx = 3;
		gbc_minPaymentResult.gridy = 4;
		frmLoanInvestment.getContentPane().add(minPaymentResult, gbc_minPaymentResult);
		
		minPaymentInvalid = new JLabel("");
		minPaymentInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_minPaymentInvalid = new GridBagConstraints();
		gbc_minPaymentInvalid.gridwidth = 2;
		gbc_minPaymentInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentInvalid.gridx = 2;
		gbc_minPaymentInvalid.gridy = 5;
		frmLoanInvestment.getContentPane().add(minPaymentInvalid, gbc_minPaymentInvalid);

		// =====================================================================
		// BALANCE
		// =====================================================================

		// Label

		balanceLabel = new JLabel("Balance");
		GridBagConstraints gbc_balanceLabel = new GridBagConstraints();
		gbc_balanceLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_balanceLabel.gridx = 1;
		gbc_balanceLabel.gridy = 6;
		frmLoanInvestment.getContentPane().add(balanceLabel, gbc_balanceLabel);

		// Entry Text Field

		balanceEntry = new JFormattedTextField();
		balanceEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				form.setBalance(balanceEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				form.setBalance(balanceEntry.getText());
				update();
			}
		});

		GridBagConstraints gbc_balanceEntry = new GridBagConstraints();
		gbc_balanceEntry.insets = new Insets(0, 0, 5, 5);
		gbc_balanceEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceEntry.gridx = 2;
		gbc_balanceEntry.gridy = 6;
		frmLoanInvestment.getContentPane().add(balanceEntry, gbc_balanceEntry);
		balanceEntry.setColumns(10);

		// Results Text Field

		balanceResult = new JFormattedTextField();
		balanceResult.setEditable(false);
		balanceResult.setText("$0.00");
		GridBagConstraints gbc_balanceResult = new GridBagConstraints();
		gbc_balanceResult.insets = new Insets(0, 0, 5, 5);
		gbc_balanceResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceResult.gridx = 3;
		gbc_balanceResult.gridy = 6;
		frmLoanInvestment.getContentPane().add(balanceResult, gbc_balanceResult);
		
		balanceInvalid = new JLabel("");
		balanceInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_balanceInvalid = new GridBagConstraints();
		gbc_balanceInvalid.gridwidth = 2;
		gbc_balanceInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_balanceInvalid.gridx = 2;
		gbc_balanceInvalid.gridy = 7;
		frmLoanInvestment.getContentPane().add(balanceInvalid, gbc_balanceInvalid);

		// =====================================================================
		// INTEREST RATE
		// =====================================================================

		// Label

		interestRateLabel = new JLabel("Annual Interest Rate (%)");
		GridBagConstraints gbc_interestRateLabel = new GridBagConstraints();
		gbc_interestRateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateLabel.gridx = 1;
		gbc_interestRateLabel.gridy = 8;
		frmLoanInvestment.getContentPane().add(interestRateLabel, gbc_interestRateLabel);

		// Entry Text Field

		interestRateEntry = new JFormattedTextField();
		interestRateEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				form.setInterestRate(interestRateEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				form.setInterestRate(interestRateEntry.getText());
				update();
			}
		});
		GridBagConstraints gbc_interestRateEntry = new GridBagConstraints();
		gbc_interestRateEntry.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateEntry.gridx = 2;
		gbc_interestRateEntry.gridy = 8;
		frmLoanInvestment.getContentPane().add(interestRateEntry, gbc_interestRateEntry);
		interestRateEntry.setColumns(10);

		// Result Text Field

		interestRateResult = new JFormattedTextField();
		interestRateResult.setEditable(false);
		interestRateResult.setText("0.00%");
		GridBagConstraints gbc_interestRateResult = new GridBagConstraints();
		gbc_interestRateResult.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateResult.gridx = 3;
		gbc_interestRateResult.gridy = 8;
		frmLoanInvestment.getContentPane().add(interestRateResult, gbc_interestRateResult);

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(form.validateEntries()) {
					if(form.getType().equals(Investment.class)) {
						mainProgram.addAccount(new Investment(
								form.getName(),
								form.getInterestRateValue(),
								form.getBalanceValue()
								));
					} else {
						mainProgram.addAccount(new Loan(
								form.getName(),
								form.getInterestRateValue(),
								form.getBalanceValue(),
								form.getMinimumPaymentValue()
								));
					}
				}
				update();
			}
		});
		
		interestRateInvalid = new JLabel("");
		interestRateInvalid.setBackground(Color.RED);
		GridBagConstraints gbc_interestRateInvalid = new GridBagConstraints();
		gbc_interestRateInvalid.gridwidth = 2;
		gbc_interestRateInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateInvalid.gridx = 2;
		gbc_interestRateInvalid.gridy = 9;
		frmLoanInvestment.getContentPane().add(interestRateInvalid, gbc_interestRateInvalid);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.gridwidth = 2;
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 10;
		frmLoanInvestment.getContentPane().add(addButton, gbc_addButton);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 11;
		frmLoanInvestment.getContentPane().add(separator, gbc_separator);
		
				// =====================================================================
				// CONFIRM BUTTON
				// =====================================================================
		
				confirmButton = new JButton("Confirm");
				confirmButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(form.validateEntries() && mainProgram.getStartingAccounts().size() > 0) {
							System.out.println("Valid.");
							mainProgram.run(form.getTotalPeriods(), form.getIncomeValue());
						} else {
							System.out.println("Invalid.");
						}
					}
				});
				
				incomeLabel = new JLabel("Income For Period");
				GridBagConstraints gbc_incomeLabel = new GridBagConstraints();
				gbc_incomeLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_incomeLabel.insets = new Insets(0, 0, 5, 5);
				gbc_incomeLabel.gridx = 1;
				gbc_incomeLabel.gridy = 12;
				frmLoanInvestment.getContentPane().add(incomeLabel, gbc_incomeLabel);
				
				incomeEntry = new JFormattedTextField();
				incomeEntry.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent de) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						form.setIncome(incomeEntry.getText());
						update();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						form.setIncome(incomeEntry.getText());
						update();
					}
				});
				
				GridBagConstraints gbc_incomeEntry = new GridBagConstraints();
				gbc_incomeEntry.insets = new Insets(0, 0, 5, 5);
				gbc_incomeEntry.fill = GridBagConstraints.HORIZONTAL;
				gbc_incomeEntry.gridx = 2;
				gbc_incomeEntry.gridy = 12;
				frmLoanInvestment.getContentPane().add(incomeEntry, gbc_incomeEntry);
				
				incomeResult = new JFormattedTextField();
				
				incomeResult.setText("$0.00");
				incomeResult.setEditable(false);
				GridBagConstraints gbc_incomeResult = new GridBagConstraints();
				gbc_incomeResult.insets = new Insets(0, 0, 5, 5);
				gbc_incomeResult.fill = GridBagConstraints.HORIZONTAL;
				gbc_incomeResult.gridx = 3;
				gbc_incomeResult.gridy = 12;
				frmLoanInvestment.getContentPane().add(incomeResult, gbc_incomeResult);
				
				incomeInvalid = new JLabel("");
				incomeInvalid.setBackground(Color.RED);
				GridBagConstraints gbc_incomeInvalid = new GridBagConstraints();
				gbc_incomeInvalid.gridwidth = 2;
				gbc_incomeInvalid.insets = new Insets(0, 0, 5, 5);
				gbc_incomeInvalid.gridx = 2;
				gbc_incomeInvalid.gridy = 13;
				frmLoanInvestment.getContentPane().add(incomeInvalid, gbc_incomeInvalid);
				
				totalPeriodsLabel = new JLabel("Total Periods");
				GridBagConstraints gbc_totalPeriodsLabel = new GridBagConstraints();
				gbc_totalPeriodsLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPeriodsLabel.insets = new Insets(0, 0, 5, 5);
				gbc_totalPeriodsLabel.gridx = 1;
				gbc_totalPeriodsLabel.gridy = 14;
				frmLoanInvestment.getContentPane().add(totalPeriodsLabel, gbc_totalPeriodsLabel);
				
				totalPeriodsEntry = new JFormattedTextField();
				totalPeriodsEntry.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent de) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						form.setTotalPeriods(totalPeriodsEntry.getText());
						update();
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						form.setTotalPeriods(totalPeriodsEntry.getText());
						update();
					}
				});
				GridBagConstraints gbc_totalPeriodsEntry = new GridBagConstraints();
				gbc_totalPeriodsEntry.insets = new Insets(0, 0, 5, 5);
				gbc_totalPeriodsEntry.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPeriodsEntry.gridx = 2;
				gbc_totalPeriodsEntry.gridy = 14;
				frmLoanInvestment.getContentPane().add(totalPeriodsEntry, gbc_totalPeriodsEntry);
				
				totalPeriodsResult = new JFormattedTextField();
				totalPeriodsResult.setText("1");
				totalPeriodsResult.setEditable(false);
				GridBagConstraints gbc_totalPeriodsResult = new GridBagConstraints();
				gbc_totalPeriodsResult.insets = new Insets(0, 0, 5, 5);
				gbc_totalPeriodsResult.fill = GridBagConstraints.HORIZONTAL;
				gbc_totalPeriodsResult.gridx = 3;
				gbc_totalPeriodsResult.gridy = 14;
				frmLoanInvestment.getContentPane().add(totalPeriodsResult, gbc_totalPeriodsResult);
				
				totalPeriodsInvalid = new JLabel("");
				totalPeriodsInvalid.setBackground(Color.RED);
				GridBagConstraints gbc_totalPeriodsInvalid = new GridBagConstraints();
				gbc_totalPeriodsInvalid.gridwidth = 2;
				gbc_totalPeriodsInvalid.insets = new Insets(0, 0, 5, 5);
				gbc_totalPeriodsInvalid.gridx = 2;
				gbc_totalPeriodsInvalid.gridy = 15;
				frmLoanInvestment.getContentPane().add(totalPeriodsInvalid, gbc_totalPeriodsInvalid);
				GridBagConstraints gbc_confirmButton = new GridBagConstraints();
				gbc_confirmButton.gridwidth = 2;
				gbc_confirmButton.insets = new Insets(0, 0, 5, 5);
				gbc_confirmButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_confirmButton.gridx = 2;
				gbc_confirmButton.gridy = 16;
				frmLoanInvestment.getContentPane().add(confirmButton, gbc_confirmButton);
		DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Account Name");
	    model.addColumn("Account Type");
	    model.addColumn("Balance");
	    model.addColumn("Interest Rate");
	    model.addColumn("Minimum Payment (if loan)");
				
						scrollPane = new JScrollPane();
						GridBagConstraints gbc_scrollPane = new GridBagConstraints();
						gbc_scrollPane.gridwidth = 3;
						gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
						gbc_scrollPane.fill = GridBagConstraints.BOTH;
						gbc_scrollPane.gridx = 1;
						gbc_scrollPane.gridy = 17;
						frmLoanInvestment.getContentPane().add(scrollPane, gbc_scrollPane);
						table = new JTable(model);
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setSurrendersFocusOnKeystroke(true);
						table.setEnabled(false);
						table.setShowVerticalLines(true);
						scrollPane.setViewportView(table);

	}

	public void update() {
		// Set the results fields
		nameResult.setText(form.getName());
		minPaymentResult.setText(form.getFormattedMinimumPayment());
		balanceResult.setText(form.getFormattedBalance());
		interestRateResult.setText(form.getFormattedInterestRate());
		incomeResult.setText(form.getFormattedIncome());
		totalPeriodsResult.setText("" + form.getTotalPeriods());

		// Set error messages
		
		if (nameEntry.getText().isEmpty() || form.isValidName()) {
			nameInvalid.setText("");
		} else {
			nameInvalid.setText("Invalid name.");
		}

		if (minPaymentEntry.getText().isEmpty() ||form.isValidMinimumPayment()) {
			minPaymentInvalid.setText("");
		} else {
			minPaymentInvalid.setText("Invalid minimum payment.");
		}
		
		if (interestRateEntry.getText().isEmpty() || form.isValidInterestRate()) {
			interestRateInvalid.setText("");
		} else {
			interestRateInvalid.setText("Invalid interest rate.");
		}

		if (balanceEntry.getText().isEmpty() ||form.isValidBalance()) {
			balanceInvalid.setText("");
		} else {
			balanceInvalid.setText("Invalid balance.");
		}
		
		if (incomeEntry.getText().isEmpty() ||form.isValidIncome()) {
			incomeInvalid.setText("");
		} else {
			incomeInvalid.setText("Invalid income.");
		}
		
		if (totalPeriodsEntry.getText().isEmpty() || form.isValidTotalPeriods()) {
			totalPeriodsInvalid.setText("");
		} else {
			totalPeriodsInvalid.setText("Invalid total periods.");
		}


		// clear table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for (Account account : mainProgram.getStartingAccounts()) {
			
			if (account instanceof Investment) {
				model.addRow(new Object[] { 
						account.getAccountName(), 
						account.getClass().getSimpleName(),
						AccountForm.convertToDollarFormat(account.getBalance()),
						AccountForm.convertToPercentageFormat((account.getInterestRate() - 1) * 100), 
						"N/A" });
			} else {
				Loan loan = (Loan) account;
				model.addRow(new Object[] { 
						loan.getAccountName(), 
						loan.getClass().getSimpleName(), 
						AccountForm.convertToDollarFormat(loan.getBalance()),
						AccountForm.convertToPercentageFormat((loan.getInterestRate() - 1) * 100), 			
						AccountForm.convertToDollarFormat(loan.getMinimumPayment())
						});
			}

		}

	}
}
