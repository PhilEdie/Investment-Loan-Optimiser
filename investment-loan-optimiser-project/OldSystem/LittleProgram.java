
public class LittleProgram {

	public LittleProgram() {
		Loan loan = new Loan(1.02, -100000, 1000);
		Investment investment = new Investment(1.08, 5000);
		
		double income = 5000;
		
		scenarios(loan, investment, income);
		
		loan = new Loan(1.08, -100000, 1000);
		investment = new Investment(1.08, 5000);

		scenarios(loan, investment, income);
		
		loan = new Loan(1.04, -100000, 200);
		investment = new Investment(1.08, 0);

		scenarios(loan, investment, income);
	}

	public void scenarios(Loan loan, Investment investment, double income) {
		System.out.println(loan.toString() + ",\n" + investment.toString() + ",\n income=$"+String.format("%.2f", income));
		Loan loan1 = new Loan(loan);
		Investment investment1 = new Investment(investment);

		// Scenario 1, Prioritise the with high balance and low interest rate.
		for (int i = 0; i < 7; i++) {
			loan1.makePayment(income);
			loan1.applyInterest();
			investment1.applyInterest();
		}

		System.out.println("Prioritise the Loan: $" + String.format("%.2f",(loan1.getBalance() + investment1.getBalance())));

		Loan loan2 = new Loan(loan);
		Investment investment2 = new Investment(investment);
		
		// Scenario 2, Prioritise investment with low balance and high interest rate.
		for (int i = 0; i < 7; i++) {
			double remainingIncome = income - loan2.getMinimumPayment();
			loan2.makeMinimumPayment();
			loan2.applyInterest();
			investment2.makePayment(remainingIncome);
			investment2.applyInterest();
		}
		
		
		System.out.println("Prioritise the Investment: $" + String.format("%.2f",(loan2.getBalance() + investment2.getBalance())));
		System.out.println("-------------------------");
	}

	public static void main(String[] args) {
		new LittleProgram();
	}

}
