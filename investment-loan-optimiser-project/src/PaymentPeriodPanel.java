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
	public final PaymentPeriodTable paymentPeriodTable;
	public final PaymentPeriodSummaryTable paymentPeriodSummaryTable;
	public final JScrollPane paymentPeriodScrollPane;
	public final JScrollPane paymentPeriodSummaryScrollPane;
	public final JPanel outerPanel;
	public final JLabel accountsLabel;
	public final JLabel summaryLabel;
	public final JPanel innerPanel1;
	public final JPanel innerPanel2;
	
	
	public PaymentPeriodPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1176, 0};
		gridBagLayout.rowHeights = new int[]{166, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
				
				outerPanel = new JPanel();
				outerPanel.setBorder(new TitledBorder(new LineBorder(new Color(230, 230, 230)), "Payment Period X", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(230, 230, 230)));
				GridBagConstraints gbc_outerPanel = new GridBagConstraints();
				gbc_outerPanel.fill = GridBagConstraints.BOTH;
				gbc_outerPanel.gridx = 0;
				gbc_outerPanel.gridy = 0;
				add(outerPanel, gbc_outerPanel);
				GridBagLayout gbl_outerPanel = new GridBagLayout();
				gbl_outerPanel.columnWidths = new int[]{1166, 0};
				gbl_outerPanel.rowHeights = new int[]{16, 55, 16, 55, 0};
				gbl_outerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_outerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				outerPanel.setLayout(gbl_outerPanel);
				
				accountsLabel = new JLabel("Accounts");
				GridBagConstraints gbc_accountsLabel = new GridBagConstraints();
				gbc_accountsLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_accountsLabel.insets = new Insets(0, 0, 5, 0);
				gbc_accountsLabel.gridx = 0;
				gbc_accountsLabel.gridy = 0;
				outerPanel.add(accountsLabel, gbc_accountsLabel);
				
				innerPanel1 = new JPanel();
				GridBagConstraints gbc_innerPanel1 = new GridBagConstraints();
				gbc_innerPanel1.fill = GridBagConstraints.BOTH;
				gbc_innerPanel1.insets = new Insets(0, 0, 5, 0);
				gbc_innerPanel1.gridx = 0;
				gbc_innerPanel1.gridy = 1;
				outerPanel.add(innerPanel1, gbc_innerPanel1);
				innerPanel1.setLayout(new BoxLayout(innerPanel1, BoxLayout.X_AXIS));
				
				paymentPeriodScrollPane = new JScrollPane();
				paymentPeriodScrollPane.setViewportBorder(null);
				innerPanel1.add(paymentPeriodScrollPane);
				
				paymentPeriodTable = new PaymentPeriodTable();
				paymentPeriodScrollPane.setViewportView(paymentPeriodTable);
				
				summaryLabel = new JLabel("Summary");
				GridBagConstraints gbc_summaryLabel = new GridBagConstraints();
				gbc_summaryLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_summaryLabel.insets = new Insets(0, 0, 5, 0);
				gbc_summaryLabel.gridx = 0;
				gbc_summaryLabel.gridy = 2;
				outerPanel.add(summaryLabel, gbc_summaryLabel);
				
						
						innerPanel2 = new JPanel();
						GridBagConstraints gbc_innerPanel2 = new GridBagConstraints();
						gbc_innerPanel2.fill = GridBagConstraints.BOTH;
						gbc_innerPanel2.gridx = 0;
						gbc_innerPanel2.gridy = 3;
						outerPanel.add(innerPanel2, gbc_innerPanel2);
						innerPanel2.setLayout(new BoxLayout(innerPanel2, BoxLayout.X_AXIS));
						
						paymentPeriodSummaryScrollPane = new JScrollPane();
						paymentPeriodSummaryScrollPane.setViewportBorder(null);
						innerPanel2.add(paymentPeriodSummaryScrollPane);
						
						paymentPeriodSummaryTable = new PaymentPeriodSummaryTable();
						paymentPeriodSummaryScrollPane.setViewportView(paymentPeriodSummaryTable);
		

	}

}
