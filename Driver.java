import Controller.PaperclipController;
import Model.*;
import View.*;

public class Driver {
   public static void main(String[] args) {
      AutoClipper modelAutoClipper = new AutoClipper();
      Manufacturing modelManufacturing = new Manufacturing();
      Business modelBusiness = new Business();
      Marketing modelMarketing = new Marketing();
      Price modelPrice = new Price();
      Wire modelWire = new Wire();
      ScoreBar viewScoreBar = new ScoreBar("./score.txt",6);

      Paperclip game = new Paperclip(modelMarketing, modelManufacturing, modelAutoClipper, modelWire, modelBusiness, modelPrice); //view 
      game.createGame();

      PaperclipController gameController = new PaperclipController(viewScoreBar, game, modelAutoClipper, modelManufacturing, modelWire, modelBusiness, modelPrice, modelMarketing);
      gameController.startGame();
       
       

   }
}
