
/*==========================================================*/
/* This is the Context class in the State pattern. This  class together
/* with the state class hierarchy are so organized that the BankContext
/* uses the state class hierarchy but the state class hierarchy will not call
/* the Context class BankContext
/*==========================================================*/
public class BankContext {

  private State objState;
  private String accountNumber;
  private double balance;
  public static final double MIN_BALANCE = 2000.00;
  
  public static final double OVERDRAW_LIMIT = -1000.00;
  public static final double TRANS_FEE_NORMAL = 2.00;
  public static final double TRANS_FEE_OVERDRAW = 5.00;
  public static final String ERR_OVER_LIMIT = "Overdraw limit exceeded.";
  
  // Add
  public static final double MAX_FREETAX_BALANCE = 100000.00;
  public static final double TAXPERCENT = 0.05;

  public BankContext(State st, String accountNum){
      objState = st;
  }
  public String getState()  {
    return objState.getState();
  }
  public void setStateObj(State objState)  {
       this.objState = objState;
  }
  // use a concrete state class to do deposit
  public void deposit(double amount){
	if( amount>0 ){
	   objState.setContext(this);
       objState.deposit(amount);
    }
    else{
	   System.out.println("Deposit amount cannot be 0 or negative");
	}
  }
  // use a concrete state class to do withdraw
  public void withdraw(double amount){
	 if( amount>0){
	    objState.setContext(this);
        objState.withdraw(amount);
     }
     else{
	 	System.out.println("Withdraw amount cannot be 0 or negative");
	 }
  }
  public double getBalance()  {
	  return balance;
  }
  public void updateBalance(double balance)  {
  	  this.balance = balance;
  }
  public boolean isOverDrawnLimitHit(){
	  return objState.isOverDrawnLimitReached();
  }
}
