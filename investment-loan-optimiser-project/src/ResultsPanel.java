import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Component;

public class ResultsPanel extends JPanel {

	private GUI gui;
	List<PaymentPeriodPanel> childPanels = new ArrayList<PaymentPeriodPanel>();

	public ResultsPanel(GUI gui) {

		this.gui = gui;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Please enter account information within the \"Accounts\" tab and click \"Confirm\".");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
	}

	public void update() {
		System.out.println("Updated");
		assert gui.mainProgram.getHistory().size() > 0;
		this.removeAll();
		Stack<List<Account>> history = gui.mainProgram.getHistory();

		for (int i = 0; i < history.size(); i++) {
			List<Account> accounts = history.get(i);
			PaymentPeriodPanel panel = new PaymentPeriodPanel();
			if(i == 0) {
				panel.outerPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Starting Accounts", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
			} else {
				panel.outerPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Payment Period " + i, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
			}
			this.add(panel);
			
		
			DefaultTableModel model = (DefaultTableModel) panel.paymentPeriodTable.getModel();
			
			
			//For the payment period table.
			for (Account account : accounts) {
				//Create a row for each account.
				model.addRow(new Object[] {
						account.getAccountName(), account.getClass().getSimpleName(),
						Utilities.convertToDollarFormat(account.getBalance()),
						Utilities.convertToPercentageFormat((account.getInterestRate() - 1) * 100),
						Utilities.convertToDollarFormat(account.getPaymentForPeriod()),
						Utilities.convertToDollarFormat(account.getInterestForPeriod()) });

			}
			
			
			//Change the scroll pane to be only as large as needed.
			Dimension d = panel.paymentPeriodTable.getPreferredSize();
			panel.paymentPeriodScrollPane.setPreferredSize(
			    new Dimension(d.width,panel.paymentPeriodTable.getRowHeight()*accounts.size()));
			
			//For the payment period summarytable.
			
			//There should only be a change in net worth starting from the first payment period. 
			String changeInNetWorth = "$0.00";
			if(i > 0) {
				changeInNetWorth = Utilities.getChangeInNetWorthAsString(history.get(i-1), accounts);
			}
			
			model = (DefaultTableModel) panel.paymentPeriodSummaryTable.getModel();
			model.addRow(new Object[] {
					Utilities.getPaidOffLoanNames(accounts),
					Utilities.getNetWorthAsString(accounts),
					changeInNetWorth,
					Utilities.getTotalInterestAsString(accounts)	
			});
			
			//Change the scroll pane to be only as large as needed.
			d = panel.paymentPeriodSummaryTable.getPreferredSize();
			panel.paymentPeriodSummaryScrollPane.setPreferredSize(
			    new Dimension(d.width,panel.paymentPeriodSummaryTable.getRowHeight()));
		}

	}
}
