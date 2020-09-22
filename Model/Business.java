package Model;
import java.util.*;

public class Business 
{
    private double capital;
    //private double salesPerSecond;
    private double demandPercentage;

    public Business()
    {
        capital=100;
        //salesPerSecond=0;
        demandPercentage=0.10;
    }

    public void setCapital(double money)
    {
        this.capital = this.capital + money;
    }

    public double getCapital()
    {
        return this.capital;
    }

    public void setDemand(double p) //should work for positive and negative numbers
    {
        if(this.demandPercentage<1)
        {
            this.demandPercentage= this.demandPercentage + p; 
        }
        else
        {
            if(p<0)
            {
                this.demandPercentage= this.demandPercentage + p; 
            }
        }
    }

    public double getDemand()
    {
        return this.demandPercentage * 100;
    }

    public int sell(int currSupplyOfClips) //returns how many paperclips were sold everytime the function is called 
    {
        if(currSupplyOfClips>=20) //can sell a maximum of 20 paperclips per second
        {   
            return (int)Math.round(20 * this.demandPercentage); //rounds to nearest int
        }
        else
        {
            return (int)Math.round(currSupplyOfClips*this.demandPercentage); //rounds to nearest int
        }
    }

}