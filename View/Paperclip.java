package View;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.awt.Color;
import Model.*;

public class Paperclip {

  public Manufacturing modelManufacturing;
  public AutoClipper modelAutoClipper;
  public Wire modelWire;
  public Business modelBusiness;
  public Price modelPrice;
  public Marketing modelMarketing;

    private JFrame mainFrame;
  //Labels
  //private JLabel lifeTimeClips;
  private JLabel totalClips;
  private JLabel business;
  private JLabel availFunds;
  private JLabel avgRev;
  private JLabel inventory;
  private JLabel pricePerClip;
  private JLabel pubDemand;
  private JLabel marketing;
  private JLabel marketingCost;
  private JLabel manufacturing;
  private JLabel clipsPerSec;
  private JLabel wire;
  private JLabel wireCost;
  private JLabel numOfAutoClippers;
  private JLabel autoClipperCost;
  private JLabel timeElapsed;
    
    private JLabel blackBox;

  //textFields
  private JTextField nameField;

  //Buttons
  private JButton makeClipButton;
  private JButton raisePriceButton;
  private JButton lowerPriceButton;
  private JButton raiseMarketingLevelButton;
  private JButton wireButton;
  private JButton autoClipButton;

  DecimalFormat df = new DecimalFormat("#.00");



  public Paperclip(Marketing marketing, Manufacturing manufacturing, AutoClipper autoclipper,
		Wire wire, Business business, Price price) {
      mainFrame = new JFrame("Paperclip Game");
      this.modelPrice = price;
      this.modelMarketing = marketing;
      this.modelAutoClipper = autoclipper;
      this.modelWire = wire;
      this.modelBusiness = business;
      this.modelManufacturing = manufacturing;
}

public void createGame() {
    mainFrame.setVisible(true);
    mainFrame.setSize(500,700);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(null);
    mainFrame.add(mainPanel);

    blackBox = new JLabel("> You're goal is to manufacture 10,000 paperclips. Reach the goal as fast as you can.");
    blackBox.setBounds(0,0,500,60);
    blackBox.setOpaque(true); //this allows for the background color to show
    blackBox.setForeground(Color.RED);
    blackBox.setBackground(Color.BLACK);

    timeElapsed = new JLabel("Score: 0") ;
    timeElapsed.setBounds(300, 80, 500, 30);

    nameField = new JTextField("Enter Name :)", 14);
    nameField.setBounds(300, 110, 100, 30);
    
    
    totalClips = new JLabel("Paperclips: 0");
    totalClips.setBounds(10,80,500,30);

    makeClipButton = new JButton("Make Paperclip");
    makeClipButton.setBounds(10,110,200,30);

    business = new JLabel("Business");
    business.setBounds(10,140,500,30);
    business.setForeground(Color.GRAY);

    availFunds = new JLabel("Available Funds: $100.00");
    availFunds.setBounds(10,170,500,30);

    avgRev = new JLabel("Avg. Rev. per sec: $0.00");
    avgRev.setBounds(10,200,500,30);

    inventory = new JLabel("Unsold Inventory: 0");
    inventory.setBounds(10,230,500,30);

    raisePriceButton = new JButton("raise");
    raisePriceButton.setBounds(10,260,70,30);
    
    lowerPriceButton = new JButton("lower");
    lowerPriceButton.setBounds(85,260,70,30);

    pricePerClip = new JLabel("Price Per Clip: $0.25");
    pricePerClip.setBounds(160,260,200,30);

    pubDemand = new JLabel("Public Demand: 10%");
    pubDemand.setBounds(10,290,500,30);

    raiseMarketingLevelButton = new JButton("Marketing");
    raiseMarketingLevelButton.setBounds(10,320,100,30); 

    marketing = new JLabel("Marketing Level: 1");
    marketing.setBounds(115,320,500,30);

    marketingCost = new JLabel("Cost: $10");
    marketingCost.setBounds(10,350,500,30);

    manufacturing = new JLabel("Manufacturing");
    manufacturing.setBounds(10,400,500,30);
    manufacturing.setForeground(Color.GRAY);

    clipsPerSec = new JLabel("Passive Clips Per Second: 0");
    clipsPerSec.setBounds(10,430,500,30);

    wireButton = new JButton("Wire");
    wireButton.setBounds(10,460,60,30);

    wire = new JLabel("50 inches");
    wire.setBounds(75,460,200,30);

    wireCost = new JLabel("Cost: $3");
    wireCost.setBounds(10,490,200,30);
    
    numOfAutoClippers = new JLabel("0");
    numOfAutoClippers.setBounds(135,520,200,30);

    autoClipperCost = new JLabel("Cost: " + modelAutoClipper.getUpgradePrice());
    autoClipperCost.setBounds(10,550,200,30);


    autoClipButton = new JButton("Auto Clippers");
    autoClipButton.setBounds(10,520,120,30);


    //mainPanel
    mainPanel.add(blackBox);
    mainPanel.add(totalClips);
    mainPanel.add(makeClipButton);
    mainPanel.add(business);
    mainPanel.add(availFunds);
    mainPanel.add(avgRev);
    mainPanel.add(inventory);
    mainPanel.add(raisePriceButton);
    mainPanel.add(lowerPriceButton);
    mainPanel.add(pricePerClip);
    mainPanel.add(pubDemand);
    mainPanel.add(raiseMarketingLevelButton);
    mainPanel.add(marketing);
    mainPanel.add(marketingCost);
    mainPanel.add(nameField);
    mainPanel.add(manufacturing);
    mainPanel.add(clipsPerSec);
    mainPanel.add(wireButton);
    mainPanel.add(wire);
    mainPanel.add(wireCost);
    mainPanel.add(autoClipButton);
    mainPanel.add(numOfAutoClippers);
    mainPanel.add(autoClipperCost);
    mainPanel.add(timeElapsed);
  }
    
    public void closeFrame(){
        mainFrame.setVisible(false);
    }

  public void setOnMakePaperclip(ActionListener l) {
    makeClipButton.addActionListener(l);
  }

  public void displayTotalInventory() {
    this.totalClips.setText("Paperclips: " + modelManufacturing.getTotalPaperclips());
    this.inventory.setText("Unsold Inventory: " + modelManufacturing.getNumPaperClips());
  }

  //WIRE
  public void setOnGetWire(ActionListener l) {
    wireButton.addActionListener(l);
  }

  public void displayWire(){
    this.wire.setText(modelWire.getSupplyOfWire() + " inches");
  }

  public void displayWirePrice(){
    this.wireCost.setText("$" + modelWire.getPrice());
  }

  public void displayFunds(){
    this.availFunds.setText("Available Funds: $" + df.format(modelBusiness.getCapital()));
  }

  //Raise and lower price
  public void displayRaisePrice() {
    this.pricePerClip.setText("Price Per Clip: $" + df.format(modelPrice.getPrice()));
    this.pubDemand.setText("Public Demand: " + df.format(modelBusiness.getDemand()) + "%");
  }

  public void setOnRaisePrice(ActionListener l) {
    raisePriceButton.addActionListener(l);
  }

  public void displayLowerPrice() {
    this.pricePerClip.setText("Price Per Clip: $" + df.format(modelPrice.getPrice()));
    this.pubDemand.setText("Public Demand: " + df.format(modelBusiness.getDemand()) + "%");
  }


  public void setOnLowerPrice(ActionListener l) {
    lowerPriceButton.addActionListener(l);
  }
 
  public void setOnMarketinglevel(ActionListener l) {
    raiseMarketingLevelButton.addActionListener(l);
  }
  
  public void displayMarketinglevel() {
    this.marketing.setText("Marketing Level: " + modelMarketing.getMarketingLevel());
    this.marketingCost.setText("Cost: $" + modelMarketing.getMarketingLevelPrice());
  }
  
  public void setOnAutoclipper(ActionListener l) {
      autoClipButton.addActionListener(l);
  }
  
  public void displayAutoclipper() {
      this.numOfAutoClippers.setText("Purchased: " + modelAutoClipper.getNumOfAutoClippers() );
      this.autoClipperCost.setText("Cost: " + df.format(modelAutoClipper.getUpgradePrice()));
  }

  public void displayDemand() {
    this.pubDemand.setText("Public Demand: " + df.format(modelBusiness.getDemand()) + "%");
  }

  public void displayRevPerSecond(double rev) {
    this.avgRev.setText("Rev. per sec: $" + df.format(rev));   
  }

  public void displayClipsPerSec(int c) {
    this.clipsPerSec.setText("Passive Clips Per Second: " +  c);
  }

  public void disableMarketing() {
    raiseMarketingLevelButton.setEnabled(false);
  }

  public void disableAutoClipper() {
    autoClipButton.setEnabled(false);
  }

  public void disableWire() {
    wireButton.setEnabled(false);
  }

  public void disableMakePaperClip() {
    makeClipButton.setEnabled(false);
  }

  public void disableRaisePrice() {
    raisePriceButton.setEnabled(false);
  }

  public void disableLowerPrice() {
    lowerPriceButton.setEnabled(false);
  }


  public void enableMarketing() {
    raiseMarketingLevelButton.setEnabled(true);
  }

  public void enableAutoClipper() {
    autoClipButton.setEnabled(true);
  }

  public void enableWire() {
    wireButton.setEnabled(true);
  }

  public void enableMakePaperClip() {
    makeClipButton.setEnabled(true);
  }

  public void enableRaisePrice() {
    raisePriceButton.setEnabled(true);
  }

  public void enableLowerPrice() {
    lowerPriceButton.setEnabled(true);
  }
    
    public String getName()
  {
    return nameField.getText();
  }

    public void setBlackbox(String  s)
    {
        blackBox.setText(s);
    }
  
    public void setTimeElapsed(String s) {
      timeElapsed.setText(s);
    }
        
}












