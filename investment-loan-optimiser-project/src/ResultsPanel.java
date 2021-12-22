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
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class ResultsPanel extends JPanel {

	private GUI gui;
	List<PaymentPeriodPanel> childPanels = new ArrayList<PaymentPeriodPanel>();

	public ResultsPanel(GUI gui) {

		this.gui = gui;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new PaymentPeriodPanel());
	}

	public void update() {
		System.out.println("Updated");
		assert gui.mainProgram.getHistory().size() > 0;
		this.removeAll();
		Stack<List<Account>> history = gui.mainProgram.getHistory();

		for (int i = 0; i < history.size(); i++) {
			PaymentPeriodPanel panel = new PaymentPeriodPanel();
			this.add(panel);
			
			DefaultTableModel model = (DefaultTableModel) panel.paymentPeriodTable.getModel();
			List<Account> accounts = history.get(i);
			for (Account account : accounts) {
				model.addRow(new Object[] { "" + i, account.getAccountName(), account.getClass().getSimpleName(),
						AccountForm.convertToDollarFormat(account.getBalance()),
						AccountForm.convertToPercentageFormat((account.getInterestRate() - 1) * 100),
						AccountForm.convertToDollarFormat(account.getPaymentForPeriod()),
						AccountForm.convertToDollarFormat(account.getInterestForPeriod()) });

			}

		}

	}
}
