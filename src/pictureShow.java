import java.util.Date;
import java.util.Vector;

public class pictureShow {
    private CinemaTheatre theatre;
    private Movie movie;
    private Date time;
    public pictureShow()
    {
        theatre=new CinemaTheatre();
        movie=new Movie();
        time=new Date(0, 1, 1, 0, 0, 0);
    }
    public pictureShow(CinemaTheatre theatre, Movie movie, Date time)
    {
        this.theatre=theatre;
        this.movie=movie;
        this.time=time;
    }
    public CinemaTheatre getTheatre()
    {
        return theatre;
    }
    public Movie getMovie()
    {
        return movie;
    }
    public Date getTime()
    {
        return time;
    }
    public void setTheatre(CinemaTheatre theatre)
    {
        this.theatre=theatre;
    }
    public void setMovie(Movie movie)
    {
        this.movie=movie;
    }
    public void setTime(Date time)
    {
        this.time=time;
    }
    public String output()
    {
        return new String(movie.output()+"; "+ theatre.output()+"; "+time);
    }
}
