/*========================================*/
/* A concrete state in the state pattern  */
/*========================================*/

// Add
public class TaxState extends State {
	private boolean overDrawnLismitFlag = false;

	public TaxState() {
		state = TAXSTATE;
	}

	public void deposit(double amount) {
		if (amount > 0) {
			balance = context.getBalance()
					- BankContext.TAXPERCENT * (amount + context.getBalance() - BankContext.MAX_FREETAX_BALANCE);
			balance = balance + amount;
			context.updateBalance(balance);
			changeState();
		} else {
			System.out.println("Deposit amount cannot be 0 or negative");
		}
	}

	public void withdraw(double amount) {
		if (amount > 0) {
			if ((context.getBalance() - amount) > BankContext.OVERDRAW_LIMIT) {
				balance = context.getBalance() - amount;
				context.updateBalance(balance);
				changeState();
			} else {
				overDrawnLismitFlag = true;
			}
		} else {
			System.out.println("Withdraw amount cannot be 0 or negative");
		}
	}

	public boolean isOverDrawnLimitReached() {
		return overDrawnLismitFlag;
	}
}
