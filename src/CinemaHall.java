import java.util.*;

public class CinemaHall
{
    int hallNumber;
    private int lines;
    private int rows;
    private int price;
    private Seat[][] seats;
    public CinemaHall(int hallNumber, int lines, int rows, int price, Seat[][] seats)
    {
        this.hallNumber=hallNumber;
        this.lines=lines;
        this.rows = rows;
        this.price=price;
        this.seats=seats;
    }
    public CinemaHall(int hallNumber, int lines, int rows, int price)//рандомный непустой зал
    {
        this.hallNumber=hallNumber;
        this.lines=lines;
        this.rows = rows;
        this.price=price;
        seats = new Seat[lines][rows];
        for(int i=0; i<lines; i++)
        {
            Random random = new Random();
            for(int j=0; j<rows; j++)
                seats[i][j]=new Seat(i, j, random.nextBoolean());
        }
    }

    public CinemaHall()
    {
        this.hallNumber=1;
        this.lines=20; this.rows = 20;
        seats = new Seat[20][20];
        for(int i=0; i<20; i++)
        {
            for(int j=0; j<20; j++)
                seats[i][j]=new Seat(i, j, true);
        }
    }
    public Seat[][] getSeats()
    {
        return seats;
    }
    public void setSeats(Seat[][] seats)
    {
        this.seats=seats;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price=price;
    }
    public int getHallNumber()
    {
        return hallNumber;
    }
    public void setHallNumber(int hallNumber)
    {
        this.hallNumber=hallNumber;
    }
    public int getLines()
    {
        return lines;
    }
    public void setLine(int lines)
    {
        this.lines=lines;
    }
    public int getRows()
    {
        return rows;
    }
    public void setRows(int rows)
    {
        this.rows=rows;
    }
    public boolean hasFreeSeats()
    {
        for(int i=0; i<seats.length; i++)
        {
            for(int j=0; j<seats[0].length; i++)
            {
                if(seats[i][j].checkFree())
                    return true;
            }
        }
        return false;
    }
    public Vector<Seat> returnFreeSeats()
    {
        Vector<Seat> result =new Vector<Seat>();
        for(int i=0; i<seats.length; i++)
        {
            for(int j=0; j<seats[0].length; j++)
            {
                if(seats[i][j].checkFree())
                    result.addElement(seats[i][j]);
            }
        }
        return result;
    }
}
