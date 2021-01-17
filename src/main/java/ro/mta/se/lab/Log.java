package ro.mta.se.lab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creeare clasa Log
 * Vom scrie in fisier fiecare Warning, Eroare etc, pentru o buna
 * gestionare a aplicatiei. Se va nota data si ora cand acestea au fost
 * accesate.
 *
 * se va rula in main : Log.getInstance().writeToFil("Warning : Fisierul nu a fost gasit");
 * Clasa va fi de tipul Singleton
 * @author MateiMunteanu
 *
 */

public class Log {
    private static PrintWriter out;
    private static final Log inst = new Log();



    private Log()
    {
        super();
    }

    /**
     * Vom scrie in fisier de forma dd-MM-yyyy HH:mm:ss urmat de stringul trimis ca si parametru
     * @param str - stringul ce va fi scris in fisier
     */
    public void writeToFile (String str){
        try {
            FileWriter file = new FileWriter("src/main/resources/log.txt", true);
            out = new PrintWriter(file);
            LocalDateTime myLocalTime;
            myLocalTime = LocalDateTime.now();
            DateTimeFormatter myFormateLocalTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            String dateTime = myLocalTime.format(myFormateLocalTime);
            out.println("[" + dateTime + "]  :  " +  str);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @return obiectul unic de tip singleton
     */
    public static Log getInstance(){
        return inst;
    }
}
