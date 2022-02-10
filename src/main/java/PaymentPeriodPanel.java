import javax.swing.*;
import java.awt.*;

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
    public final JLabel outerPanelLabel;

    public PaymentPeriodPanel() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{500, 0};
        gridBagLayout.rowHeights = new int[]{25, 0, 100, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        outerPanelLabel = new JLabel("Payment Period X");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        add(outerPanelLabel, gbc_lblNewLabel);

        outerPanel = new JPanel();
        GridBagConstraints gbc_outerPanel = new GridBagConstraints();
        gbc_outerPanel.fill = GridBagConstraints.BOTH;
        gbc_outerPanel.gridx = 0;
        gbc_outerPanel.gridy = 2;
        add(outerPanel, gbc_outerPanel);
        GridBagLayout gbl_outerPanel = new GridBagLayout();
        gbl_outerPanel.columnWidths = new int[]{500, 0};
        gbl_outerPanel.rowHeights = new int[]{16, 55, 16, 55, 0};
        gbl_outerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_outerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
        GridBagLayout gbl_innerPanel1 = new GridBagLayout();
        gbl_innerPanel1.columnWidths = new int[]{1166, 0};
        gbl_innerPanel1.rowHeights = new int[]{50, 0};
        gbl_innerPanel1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_innerPanel1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        innerPanel1.setLayout(gbl_innerPanel1);

        paymentPeriodScrollPane = new JScrollPane();
        paymentPeriodScrollPane.setViewportBorder(null);
        GridBagConstraints gbc_paymentPeriodScrollPane = new GridBagConstraints();
        gbc_paymentPeriodScrollPane.fill = GridBagConstraints.BOTH;
        gbc_paymentPeriodScrollPane.gridx = 0;
        gbc_paymentPeriodScrollPane.gridy = 0;
        innerPanel1.add(paymentPeriodScrollPane, gbc_paymentPeriodScrollPane);

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
        GridBagLayout gbl_innerPanel2 = new GridBagLayout();
        gbl_innerPanel2.columnWidths = new int[]{1166, 0};
        gbl_innerPanel2.rowHeights = new int[]{55, 0};
        gbl_innerPanel2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_innerPanel2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        innerPanel2.setLayout(gbl_innerPanel2);

        paymentPeriodSummaryScrollPane = new JScrollPane();
        paymentPeriodSummaryScrollPane.setViewportBorder(null);
        GridBagConstraints gbc_paymentPeriodSummaryScrollPane = new GridBagConstraints();
        gbc_paymentPeriodSummaryScrollPane.fill = GridBagConstraints.BOTH;
        gbc_paymentPeriodSummaryScrollPane.gridx = 0;
        gbc_paymentPeriodSummaryScrollPane.gridy = 0;
        innerPanel2.add(paymentPeriodSummaryScrollPane, gbc_paymentPeriodSummaryScrollPane);

        paymentPeriodSummaryTable = new PaymentPeriodSummaryTable();
        paymentPeriodSummaryScrollPane.setViewportView(paymentPeriodSummaryTable);

    }

}
