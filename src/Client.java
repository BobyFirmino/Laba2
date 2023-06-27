import java.util.*;
public class Client {
    private String name;
    private String phoneNumber;
    private String email;
    private int budget;
    public Client()
    {
        name = "Бабаджанян Бабаджан Бабаджанович";
        phoneNumber = "11235813213";
        email = "boss@mail.ru";
        budget = 777;
    }
    public Client(String name, String phoneNumber, String email, int budget)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.budget = budget;
    }
    public String getName()
    {
        return name;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }
    public int getBudget()
    {
        return budget;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setBudget(int budget)
    {
        this.budget = budget;
    }
     public Vector<pictureShow> chooseShow(Date wantedTime, pictureShow[] shows)
     {
         Vector<pictureShow> result = new Vector<pictureShow>();
        for(int i = 0; i < shows.length; i++)
        {
            if(wantedTime.compareTo(shows[i].getTime())==0)
                result.addElement(shows[i]);
        }
        return result;
     }
    public Vector<pictureShow> chooseShow(int wantedPrice, pictureShow[] shows)
    {
        Vector<pictureShow> result=new Vector<pictureShow>();
        for(int i = 0; i < shows.length; i++)
        {
            if(shows[i].getTheatre().getHall().getPrice() == wantedPrice)
                result.addElement(shows[i]);
        }
        return result;
    }
    public Vector<pictureShow> chooseShow(String wantedName, pictureShow[] shows)
    {
        Vector<pictureShow> result = new Vector<pictureShow>();
        for(int i = 0; i < shows.length; i++)
        {
            if(wantedName.compareTo(shows[i].getMovie().getName()) == 0)
                result.addElement(shows[i]);
        }
        return result;
    }
    public Vector<Seat> chooseSeat(CinemaHall hall)
    {
        return hall.returnFreeSeats();
    }
    public String output()
    {
        return new String("Клиент: "+ name +" "+phoneNumber+" "+ email +"; остаток средств "+ budget);
    }
}
