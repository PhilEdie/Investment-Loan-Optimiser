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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PaymentPeriodPanel extends JPanel {
	public PaymentPeriodTable paymentPeriodTable;
	public PaymentPeriodSummaryTable paymentPeriodSummaryTable;
	public JScrollPane paymentPeriodScrollPane;
	public JScrollPane paymentPeriodSummaryScrollPane;
	public JPanel outerPanel;
	private JLabel accountsLabel;
	private JLabel summaryLabel;
	public JPanel innerPanel1;
	public JPanel innerPanel2;
	
	
	public PaymentPeriodPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		outerPanel = new JPanel();
		outerPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Payment Period X", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(outerPanel);
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));
		
		accountsLabel = new JLabel("Accounts");
		outerPanel.add(accountsLabel);
		
		innerPanel1 = new JPanel();
		outerPanel.add(innerPanel1);
		GridBagLayout gbl_innerPanel1 = new GridBagLayout();
		gbl_innerPanel1.columnWidths = new int[]{239, 0};
		gbl_innerPanel1.rowHeights = new int[]{150, 0, 0};
		gbl_innerPanel1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_innerPanel1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		innerPanel1.setLayout(gbl_innerPanel1);
		
		paymentPeriodScrollPane = new JScrollPane();
		paymentPeriodScrollPane.setViewportBorder(null);
		GridBagConstraints gbc_oldPaymentPeriodScrollPane = new GridBagConstraints();
		gbc_oldPaymentPeriodScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_oldPaymentPeriodScrollPane.fill = GridBagConstraints.BOTH;
		gbc_oldPaymentPeriodScrollPane.gridx = 0;
		gbc_oldPaymentPeriodScrollPane.gridy = 0;
		innerPanel1.add(paymentPeriodScrollPane, gbc_oldPaymentPeriodScrollPane);
		
		paymentPeriodTable = new PaymentPeriodTable();
		paymentPeriodScrollPane.setViewportView(paymentPeriodTable);
		
		summaryLabel = new JLabel("Summary");
		outerPanel.add(summaryLabel);

		
		innerPanel2 = new JPanel();
		outerPanel.add(innerPanel2);
		GridBagLayout gbl_innerPanel2 = new GridBagLayout();
		gbl_innerPanel2.columnWidths = new int[]{239, 0};
		gbl_innerPanel2.rowHeights = new int[]{150, 0};
		gbl_innerPanel2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_innerPanel2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		innerPanel2.setLayout(gbl_innerPanel2);
		
		paymentPeriodSummaryScrollPane = new JScrollPane();
		paymentPeriodSummaryScrollPane.setViewportBorder(null);
		GridBagConstraints gbc_oldPaymentPeriodSummaryScrollPane = new GridBagConstraints();
		gbc_oldPaymentPeriodSummaryScrollPane.fill = GridBagConstraints.BOTH;
		gbc_oldPaymentPeriodSummaryScrollPane.gridx = 0;
		gbc_oldPaymentPeriodSummaryScrollPane.gridy = 0;
		innerPanel2.add(paymentPeriodSummaryScrollPane, gbc_oldPaymentPeriodSummaryScrollPane);
		
		paymentPeriodSummaryTable = new PaymentPeriodSummaryTable();
		paymentPeriodSummaryScrollPane.setViewportView(paymentPeriodSummaryTable);
		

	}

}
