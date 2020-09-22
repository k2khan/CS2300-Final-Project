package Model;
import java.util.*;

public class Wire
{
    private int amountOfWire;
    private int wirePrice;

    public Wire()
    {
        amountOfWire=50; //you start with 50 inches of wire
        wirePrice=3; //cost of very first supply of wire
    }

    public int getSupplyOfWire()
    {
        return this.amountOfWire;
    }

    public void addSupplyOfWire(int inches)
    {
        this.amountOfWire+=inches;
    }

    public int getPrice()
    {
        return this.wirePrice; 
    }

    public void setNewPrice()
    {
       Random rand = new Random();  
       this.wirePrice=rand.nextInt(4)+1;
    }
    public void reduceWire() {
        this.amountOfWire -= 1;
    }



}