package polling.treesheaps;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffEncode {
    public static void main(String[] args){
        try {
            //imports the specified file into an arraylist of string arrays
            FileReader reader = new FileReader(args[0]);

            int charValue; //stores each char value

            String input = "";  //makes an a string to store the contents of the file

            StringBuilder output = new StringBuilder(); //stringbuilder to build the output text

            //reads all the char values from the reader and adds them to the input string
            while ((charValue = reader.read()) != -1) {
                input += (char) charValue;
            }
            reader.close();

            //creates a hashmap to store all the symbols and their corresponding codes
            HashMap<Character, String> encode = new HashMap<>();

            //adds all the given symbols and codes to the hashmap
            for(int i = 0; i < HuffTree.CODES.length;i++){
                encode.put(HuffTree.SYMBOLS[i],HuffTree.CODES[i]);
            }

            //translates all the characters in the input to the corresponding code in the hashmap
            for(int i = 0; i < input.length(); i++){

                //checks to make sure that the character exists in the hashmap
                if(encode.containsKey(input.charAt(i))){

                    //appends the character's code value to the output if the above is true
                    output.append(encode.get(input.charAt(i)));
                }
            }
            System.out.println(output.toString());

            //catches errors with import
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
