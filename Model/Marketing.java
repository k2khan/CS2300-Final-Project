package Model;
public class Marketing
{
   private int marketingLevel;
    private int marketLevelPrice;
    
    public Marketing()
    {
        marketingLevel = 1;
        marketLevelPrice = 10;
    }
    
   public int getMarketingLevel()
   {
       return marketingLevel;
   }
    
    public void incrementMarketingLevel()
    {
        marketingLevel++;
    }
    
   public int getMarketingLevelPrice()
   {
       return marketLevelPrice;
   }
    
    public void setNextLevelPrice()
    {
        marketLevelPrice = marketLevelPrice * 2;
    }
}




