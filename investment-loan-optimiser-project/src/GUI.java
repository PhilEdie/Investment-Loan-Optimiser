import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class GUI {
	
	public AccountForm form;
	public MainProgram mainProgram;
	public JFrame frmLoanInvestment;
	public JTabbedPane tabbedPane;
	public AccountsPanel accountsPanel;
	public ResultsPanel resultsPanel;
	
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
	
	private void initialize() {
		frmLoanInvestment = new JFrame();
		frmLoanInvestment.setTitle("Loan & Investment Optimiser");
		frmLoanInvestment.setBounds(100, 100, 915, 554);
		frmLoanInvestment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		frmLoanInvestment.getContentPane().setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 0;
		frmLoanInvestment.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		accountsPanel = new AccountsPanel(this);
		tabbedPane.addTab("Add Accounts", null, accountsPanel, null);
		
		resultsPanel = new ResultsPanel(this);
        tabbedPane.addTab("Results", null, resultsPanel, null);	
	}

}