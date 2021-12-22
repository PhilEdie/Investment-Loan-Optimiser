import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PaymentPeriodTable extends JTable {
	
	public PaymentPeriodTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Payment Period");
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
