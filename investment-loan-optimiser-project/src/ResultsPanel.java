import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class ResultsPanel extends JPanel {

	private GUI gui;

	private JScrollPane resultsScrollPane;
	private JTable resultsTable;

	public ResultsPanel(GUI gui) {

		this.gui = gui;

		GridBagLayout gbl_resultsPanel = new GridBagLayout();
		gbl_resultsPanel.columnWidths = new int[] { 0, 0 };
		gbl_resultsPanel.rowHeights = new int[] { 0, 0 };
		gbl_resultsPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_resultsPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_resultsPanel);

		resultsScrollPane = new JScrollPane();
		resultsScrollPane.setEnabled(false);
		GridBagConstraints gbc_resultsScrollPane = new GridBagConstraints();
		gbc_resultsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_resultsScrollPane.gridx = 0;
		gbc_resultsScrollPane.gridy = 0;
		this.add(resultsScrollPane, gbc_resultsScrollPane);

		// =======================================================
		// TABLE
		// =======================================================

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Payment Period");
		model.addColumn("Account Name");
		model.addColumn("Account Type");
		model.addColumn("Balance");
		model.addColumn("Interest Rate");
		model.addColumn("Payments Made");
		model.addColumn("Interest For Period");
		resultsTable = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		resultsTable.setEnabled(false);
		resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultsTable.getTableHeader().setReorderingAllowed(false);
		resultsTable.getTableHeader().setResizingAllowed(false);

		resultsScrollPane.setViewportView(resultsTable);
	}

	public void update() {

		assert gui.mainProgram.getHistory().size() > 0;

		// clear table
		DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
		model.setRowCount(0);

		Stack<List<Account>> history = gui.mainProgram.getHistory();

		for (int i = 0; i < history.size(); i++) {
			for (Account account : history.get(i)) {
				model.addRow(new Object[] { "" + i, account.getAccountName(), account.getClass().getSimpleName(),
						AccountForm.convertToDollarFormat(account.getBalance()),
						AccountForm.convertToPercentageFormat((account.getInterestRate() - 1) * 100),
						AccountForm.convertToDollarFormat(account.getPaymentForPeriod()),
						AccountForm.convertToDollarFormat(account.getInterestForPeriod()) });
			}
			model.addRow(new Object[] {});	// Add blank row between payment periods.
		}
	}
}
