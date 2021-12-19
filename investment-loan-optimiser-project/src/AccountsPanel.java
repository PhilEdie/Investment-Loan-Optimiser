import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AccountsPanel extends JPanel {

	private GUI gui;
	
	private Locale locale = Locale.US;
	private NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
	
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
	private JScrollPane accountsScrollPane;
	private JTable accountsTable;
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
	 * Create the application.
	 */
	public AccountsPanel(GUI gui) {
		this.gui = gui;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		GridBagLayout gbl_this = new GridBagLayout();
		gbl_this.columnWidths = new int[] { 0, 150, 150, 0 };
		gbl_this.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0 };
		gbl_this.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_this.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_this);

		// =====================================================================
		// ACCOUNT NAME
		// =====================================================================

		// Account Name Label

		JLabel nameLabel = new JLabel("Account Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		this.add(nameLabel, gbc_nameLabel);

		// Account Name Text Entry

		nameEntry = new JFormattedTextField();
		GridBagConstraints gbc_nameEntry = new GridBagConstraints();
		gbc_nameEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameEntry.insets = new Insets(0, 0, 5, 5);
		gbc_nameEntry.gridx = 1;
		gbc_nameEntry.gridy = 1;
		this.add(nameEntry, gbc_nameEntry);
		nameEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setName(nameEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setName(nameEntry.getText());
				update();
			}
		});
		nameEntry.setColumns(10);

		// Name Results

		nameResult = new JFormattedTextField();
		GridBagConstraints gbc_nameResult = new GridBagConstraints();
		gbc_nameResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameResult.insets = new Insets(0, 0, 5, 0);
		gbc_nameResult.gridx = 2;
		gbc_nameResult.gridy = 1;
		this.add(nameResult, gbc_nameResult);
		nameResult.setEditable(false);
		nameResult.setText("Account 1");

		nameInvalid = new JLabel("");
		GridBagConstraints gbc_nameInvalid = new GridBagConstraints();
		gbc_nameInvalid.gridwidth = 2;
		gbc_nameInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_nameInvalid.gridx = 1;
		gbc_nameInvalid.gridy = 2;
		this.add(nameInvalid, gbc_nameInvalid);
		nameInvalid.setBackground(Color.RED);

		// =====================================================================
		// ACCOUNT TYPE
		// =====================================================================

		// Account Type Label

		typeLabel = new JLabel("Account Type");
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 3;
		this.add(typeLabel, gbc_typeLabel);

		// Type Combo box

		String[] comboBoxEntries = { "Investment", "Loan" };
		typeComboBox = new JComboBox(comboBoxEntries);
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridwidth = 2;
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 3;
		this.add(typeComboBox, gbc_typeComboBox);

		// =====================================================================
		// MINIMUM PAYMENT
		// =====================================================================

		// Min Payment Label

		minPaymentLabel = new JLabel("Minimum Annual Payment (If Loan)");
		GridBagConstraints gbc_minPaymentLabel = new GridBagConstraints();
		gbc_minPaymentLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentLabel.gridx = 0;
		gbc_minPaymentLabel.gridy = 4;
		this.add(minPaymentLabel, gbc_minPaymentLabel);

		// Min Payment Text Field

		minPaymentEntry = new JFormattedTextField();
		GridBagConstraints gbc_minPaymentEntry = new GridBagConstraints();
		gbc_minPaymentEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentEntry.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentEntry.gridx = 1;
		gbc_minPaymentEntry.gridy = 4;
		this.add(minPaymentEntry, gbc_minPaymentEntry);
		minPaymentEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setMinimumPayment(minPaymentEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setMinimumPayment(minPaymentEntry.getText());
				update();
			}
		});

		minPaymentEntry.setEnabled(false);
		minPaymentEntry.setColumns(10);

		// Min Payment Results

		minPaymentResult = new JFormattedTextField();
		GridBagConstraints gbc_minPaymentResult = new GridBagConstraints();
		gbc_minPaymentResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentResult.insets = new Insets(0, 0, 5, 0);
		gbc_minPaymentResult.gridx = 2;
		gbc_minPaymentResult.gridy = 4;
		this.add(minPaymentResult, gbc_minPaymentResult);
		minPaymentResult.setEditable(false);
		minPaymentResult.setText("$0.00");

		minPaymentInvalid = new JLabel("");
		GridBagConstraints gbc_minPaymentInvalid = new GridBagConstraints();
		gbc_minPaymentInvalid.gridwidth = 2;
		gbc_minPaymentInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_minPaymentInvalid.gridx = 1;
		gbc_minPaymentInvalid.gridy = 5;
		this.add(minPaymentInvalid, gbc_minPaymentInvalid);
		minPaymentInvalid.setBackground(Color.RED);

		// =====================================================================
		// BALANCE
		// =====================================================================

		// Label

		balanceLabel = new JLabel("Balance");
		GridBagConstraints gbc_balanceLabel = new GridBagConstraints();
		gbc_balanceLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_balanceLabel.gridx = 0;
		gbc_balanceLabel.gridy = 6;
		this.add(balanceLabel, gbc_balanceLabel);

		// Entry Text Field

		balanceEntry = new JFormattedTextField();
		GridBagConstraints gbc_balanceEntry = new GridBagConstraints();
		gbc_balanceEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceEntry.insets = new Insets(0, 0, 5, 5);
		gbc_balanceEntry.gridx = 1;
		gbc_balanceEntry.gridy = 6;
		this.add(balanceEntry, gbc_balanceEntry);
		balanceEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setBalance(balanceEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setBalance(balanceEntry.getText());
				update();
			}
		});
		balanceEntry.setColumns(10);

		// Results Text Field

		balanceResult = new JFormattedTextField();
		GridBagConstraints gbc_balanceResult = new GridBagConstraints();
		gbc_balanceResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceResult.insets = new Insets(0, 0, 5, 0);
		gbc_balanceResult.gridx = 2;
		gbc_balanceResult.gridy = 6;
		this.add(balanceResult, gbc_balanceResult);
		balanceResult.setEditable(false);
		balanceResult.setText("$0.00");

		balanceInvalid = new JLabel("");
		GridBagConstraints gbc_balanceInvalid = new GridBagConstraints();
		gbc_balanceInvalid.gridwidth = 2;
		gbc_balanceInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_balanceInvalid.gridx = 1;
		gbc_balanceInvalid.gridy = 7;
		this.add(balanceInvalid, gbc_balanceInvalid);
		balanceInvalid.setBackground(Color.RED);

		// =====================================================================
		// INTEREST RATE
		// =====================================================================

		// Label

		interestRateLabel = new JLabel("Annual Interest Rate (%)");
		GridBagConstraints gbc_interestRateLabel = new GridBagConstraints();
		gbc_interestRateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateLabel.gridx = 0;
		gbc_interestRateLabel.gridy = 8;
		this.add(interestRateLabel, gbc_interestRateLabel);

		// Entry Text Field

		interestRateEntry = new JFormattedTextField();
		GridBagConstraints gbc_interestRateEntry = new GridBagConstraints();
		gbc_interestRateEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateEntry.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateEntry.gridx = 1;
		gbc_interestRateEntry.gridy = 8;
		this.add(interestRateEntry, gbc_interestRateEntry);
		interestRateEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setInterestRate(interestRateEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setInterestRate(interestRateEntry.getText());
				update();
			}
		});
		interestRateEntry.setColumns(10);

		// Result Text Field

		interestRateResult = new JFormattedTextField();
		GridBagConstraints gbc_interestRateResult = new GridBagConstraints();
		gbc_interestRateResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateResult.insets = new Insets(0, 0, 5, 0);
		gbc_interestRateResult.gridx = 2;
		gbc_interestRateResult.gridy = 8;
		this.add(interestRateResult, gbc_interestRateResult);
		interestRateResult.setEditable(false);
		interestRateResult.setText("0.00%");

		interestRateInvalid = new JLabel("");
		GridBagConstraints gbc_interestRateInvalid = new GridBagConstraints();
		gbc_interestRateInvalid.gridwidth = 2;
		gbc_interestRateInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_interestRateInvalid.gridx = 1;
		gbc_interestRateInvalid.gridy = 9;
		this.add(interestRateInvalid, gbc_interestRateInvalid);
		interestRateInvalid.setBackground(Color.RED);

		addButton = new JButton("Add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.gridwidth = 2;
		gbc_addButton.insets = new Insets(0, 0, 5, 0);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 10;
		this.add(addButton, gbc_addButton);

		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 11;
		this.add(separator, gbc_separator);

		incomeLabel = new JLabel("Income For Period");
		GridBagConstraints gbc_incomeLabel = new GridBagConstraints();
		gbc_incomeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_incomeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_incomeLabel.gridx = 0;
		gbc_incomeLabel.gridy = 12;
		this.add(incomeLabel, gbc_incomeLabel);

		incomeEntry = new JFormattedTextField();
		GridBagConstraints gbc_incomeEntry = new GridBagConstraints();
		gbc_incomeEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_incomeEntry.insets = new Insets(0, 0, 5, 5);
		gbc_incomeEntry.gridx = 1;
		gbc_incomeEntry.gridy = 12;
		this.add(incomeEntry, gbc_incomeEntry);

		incomeResult = new JFormattedTextField();
		GridBagConstraints gbc_incomeResult = new GridBagConstraints();
		gbc_incomeResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_incomeResult.insets = new Insets(0, 0, 5, 0);
		gbc_incomeResult.gridx = 2;
		gbc_incomeResult.gridy = 12;
		this.add(incomeResult, gbc_incomeResult);

		incomeResult.setText("$0.00");
		incomeResult.setEditable(false);

		incomeInvalid = new JLabel("");
		GridBagConstraints gbc_incomeInvalid = new GridBagConstraints();
		gbc_incomeInvalid.gridwidth = 2;
		gbc_incomeInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_incomeInvalid.gridx = 1;
		gbc_incomeInvalid.gridy = 13;
		this.add(incomeInvalid, gbc_incomeInvalid);
		incomeInvalid.setBackground(Color.RED);

		totalPeriodsLabel = new JLabel("Total Periods");
		GridBagConstraints gbc_totalPeriodsLabel = new GridBagConstraints();
		gbc_totalPeriodsLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalPeriodsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_totalPeriodsLabel.gridx = 0;
		gbc_totalPeriodsLabel.gridy = 14;
		this.add(totalPeriodsLabel, gbc_totalPeriodsLabel);

		totalPeriodsEntry = new JFormattedTextField();
		GridBagConstraints gbc_totalPeriodsEntry = new GridBagConstraints();
		gbc_totalPeriodsEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalPeriodsEntry.insets = new Insets(0, 0, 5, 5);
		gbc_totalPeriodsEntry.gridx = 1;
		gbc_totalPeriodsEntry.gridy = 14;
		this.add(totalPeriodsEntry, gbc_totalPeriodsEntry);

		totalPeriodsResult = new JFormattedTextField();
		GridBagConstraints gbc_totalPeriodsResult = new GridBagConstraints();
		gbc_totalPeriodsResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalPeriodsResult.insets = new Insets(0, 0, 5, 0);
		gbc_totalPeriodsResult.gridx = 2;
		gbc_totalPeriodsResult.gridy = 14;
		this.add(totalPeriodsResult, gbc_totalPeriodsResult);
		totalPeriodsResult.setText("1");
		totalPeriodsResult.setEditable(false);

		totalPeriodsInvalid = new JLabel("");
		GridBagConstraints gbc_totalPeriodsInvalid = new GridBagConstraints();
		gbc_totalPeriodsInvalid.gridwidth = 2;
		gbc_totalPeriodsInvalid.insets = new Insets(0, 0, 5, 0);
		gbc_totalPeriodsInvalid.gridx = 1;
		gbc_totalPeriodsInvalid.gridy = 15;
		this.add(totalPeriodsInvalid, gbc_totalPeriodsInvalid);
		totalPeriodsInvalid.setBackground(Color.RED);

		// =====================================================================
		// CONFIRM BUTTON
		// =====================================================================

		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.form.validateEntries() && gui.mainProgram.getStartingAccounts().size() > 0) {
					System.out.println("Valid.");
					gui.mainProgram.run(gui.form.getTotalPeriods(), gui.form.getIncomeValue());
					gui.tabbedPane.setSelectedIndex(1);
					gui.resultsPanel.update();
				} else {
					System.out.println("Invalid.");
				}
			}
		});
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmButton.gridwidth = 2;
		gbc_confirmButton.insets = new Insets(0, 0, 5, 0);
		gbc_confirmButton.gridx = 1;
		gbc_confirmButton.gridy = 16;
		this.add(confirmButton, gbc_confirmButton);

		// =====================================================================
		// TABLE
		// =====================================================================

		accountsScrollPane = new JScrollPane();
		GridBagConstraints gbc_accountsScrollPane = new GridBagConstraints();
		gbc_accountsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_accountsScrollPane.gridwidth = 3;
		gbc_accountsScrollPane.gridx = 0;
		gbc_accountsScrollPane.gridy = 17;
		this.add(accountsScrollPane, gbc_accountsScrollPane);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Account Name");
		model.addColumn("Account Type");
		model.addColumn("Balance");
		model.addColumn("Interest Rate");
		model.addColumn("Minimum Payment (if loan)");
		
		accountsTable = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		accountsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accountsTable.setSurrendersFocusOnKeystroke(true);
		accountsTable.setShowVerticalLines(true);
		accountsScrollPane.setViewportView(accountsTable);
		accountsTable.getTableHeader().setReorderingAllowed(false);
	    accountsTable.getTableHeader().setResizingAllowed(false);
		
		InputMap inputMap = accountsTable.getInputMap(WHEN_FOCUSED);
		ActionMap actionMap = accountsTable.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
		actionMap.put("delete", new AbstractAction() {
		    public void actionPerformed(ActionEvent evt) {
		       int row = accountsTable.getSelectedRow();
		       DefaultTableModel model = (DefaultTableModel)accountsTable.getModel();
		       String accountName = (String) model.getValueAt(row, 0); 
		       model.removeRow(row);
		       gui.mainProgram.removeAccount(accountName);
		    }
		});
		
		
		
		totalPeriodsEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setTotalPeriods(totalPeriodsEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setTotalPeriods(totalPeriodsEntry.getText());
				update();
			}
		});
		incomeEntry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent de) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				gui.form.setIncome(incomeEntry.getText());
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				gui.form.setIncome(incomeEntry.getText());
				update();
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.form.validateEntries()) {
					if (gui.form.getType().equals(Investment.class)) {
						gui.mainProgram.addAccount(
								new Investment(gui.form.getName(), gui.form.getInterestRateValue(), gui.form.getBalanceValue()));
					} else {
						gui.mainProgram.addAccount(new Loan(gui.form.getName(), gui.form.getInterestRateValue(),
								gui.form.getBalanceValue(), gui.form.getMinimumPaymentValue()));
					}
				}
				update();
			}
		});

		typeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();

				if (selectedBook.equals("Loan")) {
					minPaymentEntry.setEnabled(true);
					gui.form.setType(Loan.class);
					gui.form.setMinimumPayment(minPaymentEntry.getText());
					gui.form.setBalance(balanceEntry.getText());
					update();
				} else {
					minPaymentEntry.setEnabled(false);
					gui.form.setType(Investment.class);
					gui.form.setMinimumPayment("0");
					gui.form.setBalance(balanceEntry.getText());
					update();
				}
			}
		});

	}

	public void update() {
		// Set the results fields
		nameResult.setText(gui.form.getName());
		minPaymentResult.setText(gui.form.getFormattedMinimumPayment());
		balanceResult.setText(gui.form.getFormattedBalance());
		interestRateResult.setText(gui.form.getFormattedInterestRate());
		incomeResult.setText(gui.form.getFormattedIncome());
		totalPeriodsResult.setText("" + gui.form.getTotalPeriods());

		// Set error messages

		if (nameEntry.getText().isEmpty() || gui.form.isValidName()) {
			nameInvalid.setText("");
		} else {
			nameInvalid.setText("Invalid name.");
		}

		if (minPaymentEntry.getText().isEmpty() || gui.form.isValidMinimumPayment()) {
			minPaymentInvalid.setText("");
		} else {
			minPaymentInvalid.setText("Invalid minimum payment.");
		}

		if (interestRateEntry.getText().isEmpty() || gui.form.isValidInterestRate()) {
			interestRateInvalid.setText("");
		} else {
			interestRateInvalid.setText("Invalid interest rate.");
		}

		if (balanceEntry.getText().isEmpty() || gui.form.isValidBalance()) {
			balanceInvalid.setText("");
		} else {
			balanceInvalid.setText("Invalid balance.");
		}

		if (incomeEntry.getText().isEmpty() || gui.form.isValidIncome()) {
			incomeInvalid.setText("");
		} else {
			incomeInvalid.setText("Invalid income.");
		}

		if (totalPeriodsEntry.getText().isEmpty() || gui.form.isValidTotalPeriods()) {
			totalPeriodsInvalid.setText("");
		} else {
			totalPeriodsInvalid.setText("Invalid total periods.");
		}

		// clear table
		DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
		model.setRowCount(0);

		for (Account account : gui.mainProgram.getStartingAccounts()) {

			if (account instanceof Investment) {
				model.addRow(new Object[] { account.getAccountName(), account.getClass().getSimpleName(),
						AccountForm.convertToDollarFormat(account.getBalance()),
						AccountForm.convertToPercentageFormat((account.getInterestRate() - 1) * 100), "N/A" });
			} else {
				Loan loan = (Loan) account;
				model.addRow(new Object[] { loan.getAccountName(), loan.getClass().getSimpleName(),
						AccountForm.convertToDollarFormat(loan.getBalance()),
						AccountForm.convertToPercentageFormat((loan.getInterestRate() - 1) * 100),
						AccountForm.convertToDollarFormat(loan.getMinimumPayment()) });
			}

		}

	}
}
