package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaymentPeriodTable extends JTable {

    public PaymentPeriodTable() {
        setRowSelectionAllowed(false);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Account Name");
        model.addColumn("Account Type");
        model.addColumn("Balance");
        model.addColumn("Interest Rate");
        model.addColumn("Payments Made");
        model.addColumn("Interest For Period");
        this.setModel(model);
        this.setEnabled(false);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
    }
}
