package polling.treesheaps;
import java.io.FileReader;
import java.io.IOException;

public class HuffDecode {
    public static void main(String[] args){
        try {
            //imports the specified file into an arraylist of string arrays
            FileReader reader = new FileReader(args[0]);

            int charValue; //stores each char value from the reader

            String input = "";  //makes an a string to store the contents of the file

            //reads all the char values from the reader and adds them to the input string
            while ((charValue = reader.read()) != -1) {
                input += (char) charValue;
            }
            reader.close();

            HuffTree decodeTree = new HuffTree(); //creates a new tree with all the symbols and codes

            //decodes the input
            System.out.println(decodeTree.decode(input));

            //catches errors with import
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
