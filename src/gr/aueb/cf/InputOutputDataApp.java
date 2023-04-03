package gr.aueb.cf;
/**
 *  Το πρόγραμμα διαβάζει από ένα αρχείο τα ονοματεπώνυμα των μαθητών που εμπεριέχονται σε αυτό, καθώς και τους βαθμούς τους
 *  σε δύο μαθήματα, και στη συνέχεια εκτυπώνει σε ένα νέο αρχείο τα ονοματεπώνυμα αυτά καθώς και το μέσο όρο των βαθμών τους.
 *  Τέλος, δημιουργεί και ένα "logfile" για τα όποια τυχόν λάθη.
 *
 */

import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class InputOutputDataApp {

    public static void main(String[] args) throws FileNotFoundException {

        try (Scanner scanner = new Scanner(new File("C:\\Users\\viato\\OneDrive\\Υπολογιστής\\datasource.txt"));
             PrintStream ps = new PrintStream(new FileOutputStream("C:\\Users\\viato\\OneDrive\\Υπολογιστής\\primOut.txt", false));) {

            String token = null;
            int num1 = 0, num2 = 0, sum = 0, counter = 0;
            double average = 0.0d;

            while (scanner.hasNext()) {
                sum = 0;
                counter = 0;
                token = scanner.next();
                if (isInt(token)) {
                    num1 = Integer.parseInt(token);
                    counter++;

                    token = scanner.next();

                    if (isInt(token)) {
                        num2 = Integer.parseInt(token);
                        counter++;
                    }
                    sum += num1 + num2;
                    average = (double) sum / counter;
                    ps.printf("Μέσος όρος: " + average + "\n");
                } else {
                    ps.print(token + " ");
                }
            }
        } catch (IOException e) {
            getLogger(e);
        }
    }

    public static boolean isInt(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Logger getLogger(IOException ex) {
        Logger logger = Logger.getLogger(InputOutputDataApp.class.getName());
        File log = new File("C:/Users/");
        try {
            FileHandler fileHandler = new FileHandler("C:\\Users\\viato\\OneDrive\\Υπολογιστής\\log.txt");
            logger.addHandler(fileHandler);
            logger.getUseParentHandlers();
            return logger;
        } catch (IOException e) {
            logger.severe("Error in log file");
            return null;
        }
    }
}
