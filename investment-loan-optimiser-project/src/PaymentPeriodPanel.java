import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

public class PaymentPeriodPanel extends JPanel {
	public PaymentPeriodTable paymentPeriodTable;
	public PaymentPeriodSummaryTable paymentPeriodSummaryTable;
	
	public PaymentPeriodPanel() {
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{239, 0};
		gridBagLayout.rowHeights = new int[]{150, 150, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{239, 0};
		gbl_panel.rowHeights = new int[]{150, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		paymentPeriodTable = new PaymentPeriodTable();
		GridBagConstraints gbc_paymentPeriodTable = new GridBagConstraints();
		gbc_paymentPeriodTable.fill = GridBagConstraints.BOTH;
		gbc_paymentPeriodTable.gridx = 0;
		gbc_paymentPeriodTable.gridy = 0;
		panel.add(paymentPeriodTable, gbc_paymentPeriodTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{239, 0};
		gbl_panel_1.rowHeights = new int[]{150, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		paymentPeriodSummaryTable = new PaymentPeriodSummaryTable();
		GridBagConstraints gbc_paymentPeriodSummaryTable = new GridBagConstraints();
		gbc_paymentPeriodSummaryTable.fill = GridBagConstraints.BOTH;
		gbc_paymentPeriodSummaryTable.gridx = 0;
		gbc_paymentPeriodSummaryTable.gridy = 0;
		panel_1.add(paymentPeriodSummaryTable, gbc_paymentPeriodSummaryTable);

	}

}
