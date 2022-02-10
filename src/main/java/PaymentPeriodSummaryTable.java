import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaymentPeriodSummaryTable extends JTable {
	
	public PaymentPeriodSummaryTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Loans Paid Off");
		model.addColumn("Net Worth");
		model.addColumn("Change In Net Worth");
		model.addColumn("Total Interest For Period");
		
		this.setModel(model);
		this.setEnabled(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
