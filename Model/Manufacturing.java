package Model;
import java.lang.Math; 
public class Manufacturing
{
   private int paperclipInventory;
   private int totalPaperclips;
    
    public Manufacturing()
    {
        paperclipInventory = 0;
        totalPaperclips = 0;
    }
    
   public int getNumPaperClips()
   {
       return this.paperclipInventory;
   }
   
   public int getTotalPaperclips() {
       return this.totalPaperclips;
   }
    
   public void addPaperclips(int num)
   {
       paperclipInventory += num;
       if (num > 0) {
       totalPaperclips += num;
       }
   }
    
   public int getProductionRate(AutoClipper a)
   {
       int rate = a.getNumOfAutoClippers();
       return rate;
   }
    
    public boolean isGoalReached()
    {
        if (paperclipInventory < 10000)
            return false;
        else
            return true;
    }
    
}
