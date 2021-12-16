import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;

@SuppressWarnings("deprecation")
public class GUI implements Observer {

	private JFrame frame;
	private JFormattedTextField nameTextField;
	private JLabel lblNewLabel_1;
	private JComboBox typeComboBox;
	private JLabel lblNewLabel_2;
	private JFormattedTextField minPaymentTextField;
	private JFormattedTextField balanceTextField;
	private JLabel lblNewLabel_3;
	private JFormattedTextField interestRateTextField;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
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
		frame.setBounds(100, 100, 602, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 20, 111, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Account Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		nameTextField = new JFormattedTextField();
		nameTextField.setText("");
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 1;
		frame.getContentPane().add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Account Type");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		String[] comboBoxEntries = { "Investment", "Loan" };
		typeComboBox = new JComboBox(comboBoxEntries);
		
		typeComboBox.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedBook = (String) combo.getSelectedItem();
		 
		        if (selectedBook.equals("Loan")) {
		            minPaymentTextField.setEnabled(true);
		        } else {
		        	minPaymentTextField.setEnabled(false);
		        }
		    }
		});
		
		
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 2;
		gbc_typeComboBox.gridy = 2;
		frame.getContentPane().add(typeComboBox, gbc_typeComboBox);
		
		
		lblNewLabel_2 = new JLabel("Minimum Annual Payment (If Loan)");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		minPaymentTextField = new JFormattedTextField();
		minPaymentTextField.setEnabled(false);
		GridBagConstraints gbc_minPaymentTextField = new GridBagConstraints();
		gbc_minPaymentTextField.insets = new Insets(0, 0, 5, 5);
		gbc_minPaymentTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaymentTextField.gridx = 2;
		gbc_minPaymentTextField.gridy = 3;
		frame.getContentPane().add(minPaymentTextField, gbc_minPaymentTextField);
		minPaymentTextField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Balance");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		balanceTextField = new JFormattedTextField();
		GridBagConstraints gbc_balanceTextField = new GridBagConstraints();
		gbc_balanceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_balanceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_balanceTextField.gridx = 2;
		gbc_balanceTextField.gridy = 4;
		frame.getContentPane().add(balanceTextField, gbc_balanceTextField);
		balanceTextField.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Annual Interest Rate (%)");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		frame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		interestRateTextField = new JFormattedTextField();
		GridBagConstraints gbc_interestRateTextField = new GridBagConstraints();
		gbc_interestRateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_interestRateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_interestRateTextField.gridx = 2;
		gbc_interestRateTextField.gridy = 5;
		frame.getContentPane().add(interestRateTextField, gbc_interestRateTextField);
		interestRateTextField.setColumns(10);
		
		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Confirm");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 7;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 2;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 9;
		frame.getContentPane().add(table, gbc_table);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
