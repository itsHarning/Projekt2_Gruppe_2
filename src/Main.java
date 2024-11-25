import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public static void main(String[] args) {

    // Makes delpine from ascii textfile
    try {
        FileReader fil = new FileReader("ascii.txt");
        BufferedReader ind = new BufferedReader(fil);
        String line = ind.readLine(); // converts the read lines to string
        while (line != null) {
            System.out.println(line);
            line = ind.readLine();
        }
    } catch (IOException e) {
        System.out.println("fejl");
    }


}