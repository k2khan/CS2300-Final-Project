package Controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
import View.*;


public class PaperclipController {
   protected Paperclip viewPaperclip;
   //protected Leaderboard viewLeaderboard;
   protected AutoClipper modelAutoClipper;
   protected Manufacturing modelManufacturing;
   protected Business modelBusiness;
   protected Marketing modelMarketing;
   protected Price modelPrice;
   protected Wire modelWire;
    
   protected ScoreBar viewScoreBar;
    
    
   private int timeElapsed;

   //Listeners
   protected ActionListener makePaperclipListener;
   protected ActionListener raisePriceButtonListener;
   protected ActionListener lowerPriceButtonListener;
   protected ActionListener raiseMarketingLevelButtonListener;
   protected ActionListener wireButtonListener;
   protected ActionListener autoClipButtonListener;


   public PaperclipController(ScoreBar scorebar, Paperclip paperclip, AutoClipper autoclipper, Manufacturing manufacturing, Wire wire, Business business, Price price, Marketing marketing) //constructor
   { 
      this.modelAutoClipper = autoclipper;
       this.viewScoreBar = scorebar;
      this.modelManufacturing = manufacturing;
      this.viewPaperclip = paperclip;
      this.modelWire = wire;
      this.modelBusiness= business;
      this.modelPrice = price;
      this.modelMarketing = marketing;


      makePaperclipListener = new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            makePaperclip();
         }
      };

      lowerPriceButtonListener = new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            lowerPrice();
         }
      };

      raisePriceButtonListener = new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            raisePrice();
         }
      };

     wireButtonListener = new ActionListener()
     {
         public void actionPerformed(ActionEvent e)
         {
            addWire();
         }
      };

      raiseMarketingLevelButtonListener = new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             upgradeMarketLevel();
         }
      };
      
      autoClipButtonListener = new ActionListener(){
         public void actionPerformed(ActionEvent e) {
             addAutoclipper();
         }
      };


      this.viewPaperclip.setOnRaisePrice(raisePriceButtonListener);
      this.viewPaperclip.setOnLowerPrice(lowerPriceButtonListener);
      this.viewPaperclip.setOnMakePaperclip(makePaperclipListener);
      this.viewPaperclip.setOnGetWire(wireButtonListener);
      this.viewPaperclip.setOnMarketinglevel(raiseMarketingLevelButtonListener);
      this.viewPaperclip.setOnAutoclipper(autoClipButtonListener);

   }




   public void startGame() 
   {
      int count = 0;
      int count2 = 0;
      timeElapsed = 0;
      int score = 0;

      int now_clip = 0;
      int temp_clip = 0;
      int clipsPerSec = 0;

      while (modelManufacturing.getTotalPaperclips() < 10000) 
      {
          if (modelManufacturing.getTotalPaperclips()  == 5000)
          {
              this.viewPaperclip.setBlackbox("Go! You are halfway to your goal!");
          }
          else if (modelManufacturing.getTotalPaperclips()  == 3000)
          {
              this.viewPaperclip.setBlackbox("Never give up! You have maufactured 3000 paperclips.");
          }
          else if (modelManufacturing.getTotalPaperclips()  == 9000)
          {
              this.viewPaperclip.setBlackbox("You have maufactured 9000 paperclips. You can do it!");
          }
          else if (modelManufacturing.getTotalPaperclips()  == 1000)
          {
              this.viewPaperclip.setBlackbox("Start a busniess! You have maufactured 1000 paperclips.");
          }
          else if (modelManufacturing.getTotalPaperclips()  == 100)
          {
              this.viewPaperclip.setBlackbox("You are cool. You start a busniess!\n You have maufactured 100 paperclips.");
          }
         timeElapsed++;
         try {
            Thread.sleep(1);
         } catch(InterruptedException e) {
            System.out.println("Thread interuppted");
         }

         if (modelBusiness.getCapital() < modelMarketing.getMarketingLevelPrice()) {
            this.viewPaperclip.disableMarketing();
         } else {
            this.viewPaperclip.enableMarketing();
         }
         
         if (modelBusiness.getCapital() < modelWire.getPrice()) {
            this.viewPaperclip.disableWire();
         } else {
            this.viewPaperclip.enableWire();
         }
         
         if (modelBusiness.getCapital() < modelAutoClipper.getUpgradePrice()) {
            this.viewPaperclip.disableAutoClipper();
         } else {
            this.viewPaperclip.enableAutoClipper();
         }

         if (modelWire.getSupplyOfWire() <= 0) {
            this.viewPaperclip.disableMakePaperClip();
         } else {
            this.viewPaperclip.enableMakePaperClip();
         }

         if (modelPrice.getPrice() <= 0.01) {
            this.viewPaperclip.disableLowerPrice();
         } else {
            this.viewPaperclip.enableLowerPrice();
         }

         if (modelBusiness.getDemand() <= 2.50) {
            this.viewPaperclip.disableRaisePrice();
         } else {
            this.viewPaperclip.enableRaisePrice();
         }

         temp_clip = modelManufacturing.getTotalPaperclips();
         if (count2 == 1000) {
            if (this.modelManufacturing.getTotalPaperclips() > 0) {
               this.viewPaperclip.setTimeElapsed("Score: " + score);
               score++;
            }
            if(count == 5) //every 5 seconds the price of buying more wire is updated
            {
               count = 0;
               this.modelWire.setNewPrice();
            }
            else
            {
               count += 1;
            }
            int sold=this.modelBusiness.sell(this.modelManufacturing.getNumPaperClips())*-1; //how many that were sold in the current second
            this.modelManufacturing.addPaperclips(sold);
            this.modelBusiness.setCapital(sold*this.modelPrice.getPrice()*-1);

            if (modelWire.getSupplyOfWire() > 0) {
               automaticAutoClipper();
            }
            this.viewPaperclip.displayRevPerSecond(sold*this.modelPrice.getPrice()*-1);
            now_clip = modelManufacturing.getTotalPaperclips();
            clipsPerSec = now_clip - temp_clip ;
            this.viewPaperclip.displayClipsPerSec(clipsPerSec);

            count2 = 0;
         }
         count2 += 1;
          
         this.viewPaperclip.displayFunds();
         this.viewPaperclip.displayTotalInventory();
         this.viewPaperclip.displayWirePrice();
          
      }
       this.viewPaperclip.closeFrame();
       this.viewScoreBar.addRecord(this.viewPaperclip.getName(),Math.round(timeElapsed/1000));
       this.viewScoreBar.showRecord();
   }

    
    
    public int getTimeElapsed()
    {
        return timeElapsed;
    }
   


   private void automaticAutoClipper() {
      for (int i = 0; i < this.modelAutoClipper.getNumOfAutoClippers(); i++) {
         makePaperclip();
         if (modelWire.getSupplyOfWire() <= 0) {
            break;
         }
      }
   }
   private void makePaperclip() {
      this.modelManufacturing.addPaperclips(1);
      this.modelWire.reduceWire();
      this.viewPaperclip.displayTotalInventory();
      this.viewPaperclip.displayWire();
   }

   private void addWire() {
      this.modelWire.addSupplyOfWire(50); 
      this.viewPaperclip.displayWire();   
      this.modelBusiness.setCapital(-1*this.modelWire.getPrice());
      this.viewPaperclip.displayFunds();
      
   }
   private void lowerPrice() {
      this.modelPrice.lowerPrice();
      this.modelBusiness.setDemand(0.025);
      this.viewPaperclip.displayLowerPrice();
   }

   private void raisePrice() {
      this.modelPrice.raisePrice();
      this.modelBusiness.setDemand(-0.025);
      this.viewPaperclip.displayRaisePrice();
   }

    private void upgradeMarketLevel() {
       this.modelMarketing.incrementMarketingLevel();
       this.modelBusiness.setCapital(-modelMarketing.getMarketingLevelPrice());
       this.viewPaperclip.displayFunds();
       this.modelMarketing.setNextLevelPrice();
       this.viewPaperclip.displayMarketinglevel();
       this.modelBusiness.setDemand(0.10);
       this.viewPaperclip.displayDemand();
    }
    
    private void addAutoclipper() {
       this.modelAutoClipper.addAutoClipper();
       this.modelBusiness.setCapital(-this.modelAutoClipper.getUpgradePrice());
       this.viewPaperclip.displayFunds();
       this.modelAutoClipper.setNextUpgradePrice();
       this.viewPaperclip.displayAutoclipper();
    }


}
