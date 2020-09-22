package Model;
public class AutoClipper
{
    private int numOfAutoClippers;
    private double autoClipperPrice;
    //private double nextAutoClipperPrice;
    
    public AutoClipper()
    {
        numOfAutoClippers = 0;
        autoClipperPrice = 11;
    }
    
   public int getNumOfAutoClippers()
   {
       return numOfAutoClippers;
   }
    
   public void addAutoClipper()
   {
       numOfAutoClippers++;
   }
    
   public void setNextUpgradePrice()
   {
       autoClipperPrice =  autoClipperPrice + 0.5;
   }
    
   public double getUpgradePrice()
   {
       return autoClipperPrice;
   }
}



