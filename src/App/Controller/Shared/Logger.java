package App.Controller.Shared;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Frederick A. Aaron
 */
public class Logger
{
    /**
     *
     * @param input
     */
    public static void log(String input)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + " " + input);
        try
        {
            try (BufferedWriter out = new BufferedWriter(
                    new FileWriter("log.txt", true)))
            {
                out.write(dtf.format(now) + " " + input + "\n");
            }
        }
        catch (IOException e)
        {
            System.out.println("exception occoured" + e);
        }
    }
}
