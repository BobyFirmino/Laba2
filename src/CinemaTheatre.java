import java.util.*;
public class CinemaTheatre {
    private CinemaHall hall;
    private int capacity;
    private String name;
    private String address;
    private String[] formats;
    public CinemaTheatre()
    {
        name="Кинотеатр 1";
        address = "Улица Покровская";
        capacity = 400;
        formats = new String[]{"2D", "3D"};
        hall = new CinemaHall();
    }
    public CinemaTheatre(String name, String address, String[] formats, CinemaHall hall)
    {
        this.name = name;
        this.address = address;
        this.formats = formats;
        this.hall = hall;
        capacity=hall.getSeats().length*hall.getSeats()[0].length;
    }
    public CinemaHall getHall()
    {
        return hall;
    }
    public int getCapacity()
    {
        return capacity;
    }
    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public String[] getFormats()
    {
        return formats;
    }
    public void setHall(CinemaHall hall)
    {
        this.hall = hall;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public void setFormats(String[] formats)
    {
        this.formats=formats;
    }
    public String output()
    {
        return new String(name+", "+ address+ ", "+ hall.getHallNumber()+"-й зал, " +hall.getPrice()+"руб");
    }
}
