import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JScrollPane;

/*=================================================*/
/* This is a GUI for testing the state pattern.    */
/*=================================================*/
public class ClientGUI extends JPanel{
   private JSplitPane splitPane;
   private JScrollPane textPane;
   private JTextArea dataTextArea;
   private JPanel btnPanel;
   private JComboBox cmbTransType;
   private JTextField txtAmount;
   private JLabel lblTransType;
   private JLabel lblTransAmount;
   private BankContext accContext;
   public static final String DEPOSIT = "Deposit";
   public static final String WITHDRAW = "Withdraw";
   public static final String SUBMIT = "Submit";
   public static final String EXIT = "Exit";

   public ClientGUI(){
	  super(new GridLayout(1,0));
	  State initState = new TransactionFeeState();
	  accContext = new BankContext(initState, "100 200 300 4000");
	  initState.setContext(accContext);

      buildUpScrollGUI();
   }
   private void buildUpScrollGUI(){
	  setUpButtonPanel();
      dataTextArea = new JTextArea(8, 2);
	  textPane = new JScrollPane(dataTextArea);
	  textPane.setMinimumSize(new Dimension(100, 100));
	  splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, btnPanel, textPane);
	  splitPane.setDividerLocation(160);
	  add(splitPane);
	  setSize(new Dimension(600, 500));
   }
   private void setUpButtonPanel(){
      cmbTransType = new JComboBox();
      cmbTransType.addItem(DEPOSIT);
      cmbTransType.addItem(WITHDRAW);

      txtAmount = new JTextField(13);

      lblTransType = new JLabel("Transaction Type:");
      lblTransAmount = new JLabel("Transaction Amount:");

      JButton srchButton = new JButton(SUBMIT);
      srchButton.setMnemonic(KeyEvent.VK_S);
      JButton exitButton = new JButton(EXIT);
      exitButton.setMnemonic(KeyEvent.VK_X);

      ButtonListener listener = new ButtonListener();
      srchButton.addActionListener(listener);
      exitButton.addActionListener(listener);

      btnPanel = new JPanel();
      GridBagLayout gridbag = new GridBagLayout();
      btnPanel.setLayout(gridbag);
      GridBagConstraints gbc = new GridBagConstraints();

      btnPanel.add(lblTransType);
      btnPanel.add(cmbTransType);
      btnPanel.add(lblTransAmount);
      btnPanel.add(txtAmount);
      btnPanel.add(srchButton);
      btnPanel.add(exitButton);

      gbc.insets.top = 5;
      gbc.insets.bottom = 5;
      gbc.insets.left = 5;
      gbc.insets.right = 5;

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gridbag.setConstraints(lblTransType, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 0;
      gridbag.setConstraints(cmbTransType, gbc);
      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 1;
      gridbag.setConstraints(lblTransAmount, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 1;
      gridbag.setConstraints(txtAmount, gbc);
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 25;
      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 3;
      gridbag.setConstraints(srchButton, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 3;
      gridbag.setConstraints(exitButton, gbc);
   }

  public String getTransactionType(){
     return (String) cmbTransType.getSelectedItem();
  }
  public String getTransactionAmount(){
     return txtAmount.getText();
  }
  public BankContext getAccount(){
     return accContext;
  }
  public void setResult(String searchResult){
     dataTextArea.append(searchResult);
  }

  class ButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e){
        String searchResult = null;
        boolean result = false;

        searchResult = "\n\nOriginal Balance=" + accContext.getBalance() +
                       "\n"+"Original State = "+accContext.getState()+"\n\n";
        setResult(searchResult);

        if (e.getActionCommand().equals(EXIT)){
           System.exit(1);
        }
        if (e.getActionCommand().equals(SUBMIT)){
    	   String type = getTransactionType();
    	   String amount =  getTransactionAmount();

      	   if (type.equals(DEPOSIT)){
              accContext.deposit( new Double(amount).doubleValue());
           }
           if (type.equals(WITHDRAW)){
              accContext.withdraw( new Double(amount).doubleValue());
           }
		   searchResult =  "Transaction Successful: \n" + "New Balance=" +
                                        accContext.getBalance() +
                                        "\n"+"New State = "+accContext.getState();
          if(accContext.isOverDrawnLimitHit()==true)
             searchResult = BankContext.ERR_OVER_LIMIT;
           setResult(searchResult);
         }
      }
  }
  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("State Pattern- Bank Account");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     ClientGUI newContentPane = new ClientGUI();
     newContentPane.setOpaque(true);
     frame.setContentPane(newContentPane);
     frame.pack();
     frame.setVisible(true);
  }
  static public void main(String argv[]) {
	 javax.swing.SwingUtilities.invokeLater(new Runnable(){
        public void run(){
     createAndShowGUI();
      }  });
  }
}

