package Model;
public class Price
{
    private double price;
    
    public Price()
    {
        price = 0.25;
    }
    
   public void raisePrice()
   {
       price = price + 0.01;
   }
    
   public void lowerPrice()
   {
       price = price - 0.01;
   }
   public double getPrice()
   {
       return this.price;
   }
}

