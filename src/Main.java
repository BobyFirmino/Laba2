import java.util.*;
import java.text.*;
import java.io.*;
import java.nio.file.Paths;
public class Main {
    public static String password = "qwerty";
    public static void chooseShow(pictureShow[] shows)
    {
        boolean showsFound = false; Vector<pictureShow> foundShows = new Vector<pictureShow>();
        System.out.println("Введите ФИО, номер телефона, почту и бюджет:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String phone = sc.nextLine();
        String email = sc.nextLine();
        int budget = sc.nextInt();
        Client c = new Client(name, phone, email, budget);
        System.out.println("Введите параметр для выбора фильма:\n1 - время сеанса, 2 - стоимость билета, 3 - название фильма или 0 - чтобы выйти в меню");
        int parameter = sc.nextInt();
        switch(parameter)
        {
            case 1:
                System.out.println("Введите дату и время сеанса в формате дд-мм-гггг чч:мм:");
                sc.nextLine();
                String strDate = sc.nextLine();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                try {
                    Date date = dateformat.parse(strDate);
                    System.out.println(date);
                    foundShows = c.chooseShow(date, shows);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                if(foundShows.size()!=0)
                    showsFound = true;
                break;
            case 2:
                System.out.println("Введите стоимость билета:");
                int price = sc.nextInt();
                foundShows=c.chooseShow(price, shows);
                if(foundShows.size()!=0)
                    showsFound = true;
                break;
            case 3:
                System.out.println("Введите название фильма:");
                String movieName;
                sc.nextLine();
                movieName = sc.nextLine();
                foundShows=c.chooseShow(movieName, shows);
                if(foundShows.size()!=0)
                    showsFound = true;
                break;
            case 0:
                return;
            default:
                System.out.println("Ошибка: Вы ввели несуществующий параметр");
                return;
        }
        if(showsFound)
        {
            System.out.println("Вам подойдут следующие сеансы:");
            for(int i = 0; i< foundShows.size(); i++)
            {
                System.out.println((i+1)+": "+foundShows.get(i).output());
            }
            while(true) {
                System.out.println("Выберите подходящий Вам сеанс (или введите 0, чтобы отменить выбор):");
                int ind = sc.nextInt() - 1;
                if (ind == -1)
                {
                    System.out.println("Выбор сброшен");
                    break;
                }
                {
                    if (ind >= 0 && ind < foundShows.size()) {
                        if(c.getBudget() >= foundShows.get(ind).getTheatre().getHall().getPrice())
                        {
                            pictureShow foundShow=foundShows.get(ind);
                            System.out.println("Далее представлены свободные места на выбранном сеансе: ");
                            Vector<Seat> freeSeats=c.chooseSeat(foundShow.getTheatre().getHall());
                            for(int i = 0; i<freeSeats.size(); i++)
                            {
                                System.out.println(freeSeats.get(i).output());
                            }
                            while(true)
                            {
                                System.out.println("Введите номера ряда и места (в одну строку):");
                                int line = sc.nextInt()-1;
                                int row = sc.nextInt()-1;
                                if(line == -1 && row == -1)
                                    break;
                                try {
                                    if (foundShow.getTheatre().getHall().getSeats()[line][row].checkFree()) {
                                        c.setBudget(c.getBudget() - foundShow.getTheatre().getHall().getPrice());
                                        foundShow.getTheatre().getHall().getSeats()[line][row].setFree(false); //место отмечается занятым
                                        printToFile("Tickets.txt", true, c.output()+"\nСеанс: "+foundShow.output()+", "+line+ "-й ряд, "+row+"-е место\n\n");

                                        int rev = Integer.parseInt(readFromFile("TotalRevenue.txt"));
                                        rev += foundShow.getTheatre().getHall().getPrice();
                                        printToFile("TotalRevenue.txt", false, Integer.toString(rev));

                                        int sold = Integer.parseInt(readFromFile(("TotalSold.txt")));
                                        sold++;
                                        printToFile("TotalSold.txt", false, Integer.toString(sold));

                                        System.out.println("Запись успешно завершена!\nСеанс: "+foundShow.output()+", "+line+ "-й ряд, "+row+"-е место");

                                        break;
                                    } else {
                                        System.out.println("Увы, это место занято. Попробуйте снова или введите \"0 0\", чтобы выйти");
                                    }
                                }
                                catch(Exception e)
                                {
                                    System.out.println("Кажется, вы ввели несуществующие места. Попробуйте снова или введите \"0 0\", чтобы выйти");
                                }
                            }
                            break;
                        }
                        else
                        {
                            System.out.println("Недостаточно средств");
                        }
                    }
                    else
                    {
                        System.out.println("Неверный номер сеанса");
                    }
                }
            }
        }
        else
        {
            System.out.println("Не удалось найти такой сеанс");
        }
    }
    public static String readFromFile(String fileName)
    {
        String strRes = "";
        try(FileReader reader = new FileReader(fileName))
        {
            int a;
            while((a = reader.read()) != -1){

                strRes += (char)a;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return strRes;
    }
    public static void printToFile(String fileName, boolean append, String text)
    {
        try(FileWriter writer = new FileWriter(fileName, append))
        {
            writer.write(text);
            writer.flush();
            writer.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void adminAccess()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите пароль:");
        String pass = sc.nextLine();
        if (pass.compareTo(password)==0) {
            System.out.println("Вы в режиме администратора");
            while(true)
            {
                System.out.println("Введите 1, чтобы увидеть общую выручку, 2 - увидеть общее число проданных билетов, 3 - увидеть все проданные билеты или 0, чтобы вернуться в изначальное меню");
                int a = sc.nextInt();
                switch (a) {
                    case 1:
                        System.out.println("Общая выручка составила " + readFromFile("TotalRevenue.txt") + "руб");//допилить
                        break;
                    case 2:
                        System.out.println("Общее число купленных билетов составило " + readFromFile("TotalSold.txt") + " билетов");
                        break;
                    case 3:
                        String allTickets = readFromFile("Tickets.txt");
                        if(allTickets.compareTo((""))==0)
                            System.out.println("К сожалению, список пуст");
                        else
                            System.out.print("Далее представлен список всех купленных билетов:\n"+allTickets);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Введено неверное значение");
                }
            }
        }
        else {
                System.out.println("Неверный пароль");
        }
    }
    public static void main(String[] args) {
        Movie Schindler = new Movie("Джон Уик 4", 1993, "драма, военный", 195, "2D");
        Movie Avatar2 = new Movie("Аватар 2: Путь воды", 2022, "фантастика, блокбастер", 192, "3D");
        Movie EEaO = new Movie("Все везде и сразу", 2021, "фантастика, комедия", 39, "2D");
        CinemaTheatre KaroOktyabr1 = new CinemaTheatre("Каро 1 Октябрьская", "Октябрьская, 36", new String[]{"2D", "3D"}, new CinemaHall(1, 10, 20, 300));
        CinemaTheatre CinemaStar = new CinemaTheatre("Синема Стар", "Большая Черемушкинская, 1", new String[]{"2D", "3D"}, new CinemaHall(4, 15, 25, 500));
        CinemaTheatre PushkaBrateevo = new CinemaTheatre("Мега", "Дачная, 24", new String[]{"2D", "3D"}, new CinemaHall(2, 12, 15, 500));
        pictureShow[] shows=new pictureShow[]{
                new pictureShow(KaroOktyabr1, Schindler, new Date(123, 0, 1, 18, 0)),
                new pictureShow(CinemaStar, Schindler, new Date(123, 0, 2, 15, 0)),
                new pictureShow(KaroOktyabr1, Avatar2, new Date(123, 0, 1, 12, 0)),
                new pictureShow(PushkaBrateevo, Avatar2, new Date(123, 0, 1, 18, 0)),
                new pictureShow(KaroOktyabr1, EEaO, new Date(123, 0, 2, 15, 0)),
                new pictureShow(CinemaStar, EEaO, new Date(123, 0, 1, 18, 0)),
                new pictureShow(PushkaBrateevo, EEaO, new Date(123, 0, 2, 13, 0))};
        while (true)
        {
            System.out.println("Введите client, чтобы войти как клиент или admin, чтобы войти как администратор.");
            Scanner sc = new Scanner(System.in);
            String identifier = sc.nextLine();
            if(identifier.compareTo("client") == 0) {
                chooseShow(shows);
            }
            else
            {
                if(identifier.compareTo("admin") == 0)
                {
                    adminAccess();
                }
                else
                    System.out.println("Введен неизвестный идентификатор, Попробуйте снова");
            }
            System.out.println("\n");
        }
    }
}