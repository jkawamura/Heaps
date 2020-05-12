package polling.treesheaps;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * creates a tree to store characters
 */
public class HuffTree {

    //contains the codes for all the symbols. Only used in constructing the tree to
    // preserve integrity of the rest of the huffman algorithm
    public static final String[] CODES = {"000", "110", "0010", "0011", "0100", "0110", "0111", "1001", "1010",
            "10000", "10111", "11111", "010100", "010101", "010110", "100010", "100011", "101101", "111000",
            "111001", "111011", "111100", "1011000", "1110100", "1110101", "11110110", "11110111", "010111101",
            "010111111", "101100100", "101100101", "111101000", "111101010", "0101110001", "0101110010", "0101110011",
            "0101110111", "0101111000", "0101111001", "0101111100", "0101111101", "1011001101", "1011001110",
            "1011001111", "1111010010", "1111010111", "01011100000", "01011100001", "01011101000", "01011101001",
            "01011101011", "10110011000", "11110100110", "11110100111", "11110101100", "11110101101", "010111010101",
            "010111011000", "010111011001", "010111011010", "101100110011", "0101110101000", "0101110101001",
            "0101110110111", "01011101101100", "01011101101101", "10110011001000", "10110011001011", "101100110010011",
            "1011001100100100", "1011001100100101", "1011001100101000", "1011001100101001", "10110011001010100",
            "10110011001010101", "10110011001010110", "101100110010101111", "10110011001010111011",
            "101100110010101110000", "101100110010101110001", "101100110010101110010", "101100110010101110011",
            "101100110010101110100", "101100110010101110101"};

    //alternative codes for extra inputs
    /**public static final String[] CODES = {"0000", "110", "0010", "0011", "0100", "0110", "0111", "1001", "1010",
            "10000", "10111", "11111", "010100", "010101", "010110", "100010", "100011", "101101", "111000", "111001",
            "111011", "111100", "1011000", "1110100", "1110101", "11110110", "11110111", "010111101", "010111111",
            "101100100", "101100101", "111101000", "111101010", "0101110001", "0101110010", "0101110011", "0101110111",
            "0101111000", "0101111001", "0101111100", "0101111101", "1011001101", "1011001110", "1011001111",
            "1111010010", "1111010111", "01011100000", "01011100001", "01011101000", "01011101001", "01011101011",
            "10110011000", "11110100110", "11110100111", "11110101100", "11110101101", "010111010101", "010111011000",
            "010111011001", "010111011010", "101100110011", "0101110101000", "0101110101001", "0101110110111",
            "01011101101100", "01011101101101", "10110011001000", "10110011001011", "101100110010011",
            "1011001100100100", "1011001100100101", "1011001100101000", "1011001100101001", "10110011001010100",
            "10110011001010101", "10110011001010110", "101100110010101111", "10110011001010111011",
            "101100110010101110000", "101100110010101110001", "101100110010101110010", "101100110010101110011",
            "101100110010101110100", "101100110010101110101", "000100", "000101", "0001100", "0001101", "00011100",
            "00011101", "0001111"};**/


    //contains code for all the possible characters.
    public static final char[] SYMBOLS = {'e', ' ', 's', 'h', 'i', 'n', 'o', 'a', 't', 'l', 'd', 'r', 'p', ',', 'y',
            'g', 'f', 'w', 'm', 'c', '\n', 'u', 'v', '.', 'b', '\"', 'k', '-', 'P', 'A', 'T', '\'', 'I', 'j', 'z',
            'q', 'W', 'S', 'R', '?', 'M', 'B', 'N', 'x', '!', 'H', 'V', ';', 'K', 'Y', 'G', 'O', 'F', 'D', 'C', 'E',
            '(', ')', 'X', 'L', ':', '*', 'J', '1', '2', '0', '8', 'U', 'Z', '7', '5', '6', '3', '/', '9', 'Q', '4',
            '[', '#', ']', '%', '=', '@', '$'};

    //alternative codes for extra inputs
    /**public static final char[] SYMBOLS = {'e', ' ', 's', 'h', 'i', 'n', 'o', 'a', 't', 'l', 'd', 'r', 'p', ',',
            'y', 'g', 'f', 'w', 'm', 'c', '\n', 'u', 'v', '.', 'b', '\"', 'k', '-', 'P', 'A', 'T', '\'', 'I', 'j', 'z',
            'q', 'W', 'S', 'R', '?', 'M', 'B', 'N', 'x', '!', 'H', 'V', ';', 'K', 'Y', 'G', 'O', 'F', 'D', 'C', 'E',
            '(', ')', 'X', 'L', ':', '*', 'J', '1', '2', '0', '8', 'U', 'Z', '7', '5', '6', '3', '/', '9', 'Q', '4',
            '[', '#', ']', '%', '=', '@', '$', '_', '~', '<', '\\', '>', '+', '`'};**/



    private HuffTree left; //the left child
    private HuffTree right; //the right child
    private char element; //stores the character
    private HashMap<String, Character> codeDict; //hashmap for storing the codes and
                                                 //characters for quick retrieval


    /**
     * default constructor for the tree. Takes no arguments and constructs the rest of the tree
     * in a for loop
     */
    public HuffTree() {
        left = null;  //left null by default
        right = null; //right is null by default
        element = '\0'; //automatically gives root a null value
        codeDict = new HashMap<>(); //used for decoding

        //loops through all the symbols and adds them to the tree using add node method
        for (int i = 0; i < CODES.length; i++) {
            addNode(SYMBOLS[i], CODES[i], 0);
        }
    }

    /**
     * second constructor used by the addNode method to place symbols in leafs
     * @param element the character stored
     *                nside the node
     */
    private HuffTree(char element){
        left = null;
        right = null;
        this.element = element;
    }

    /**
     * decode method, uses the tree to create a hashmap of codes and characters, then
     * uses the hashmap to translate the input file.
     * @param message
     * @return
     */
    public String decode(String message){
        String codeFindInput = "";
        StringBuilder output = new StringBuilder();

        findCodesDecode(this, codeFindInput);

        int j = 0;
        int i = 3;
        while( i <= message.length()){
            String temp = message.substring(j,i);
            for(String string : codeDict.keySet()){
                if(temp.compareTo(string) == 0){
                    output.append(codeDict.get(string));
                    j = i;
                    i += 2;
                }
            }
            i += 1;
        }
        return output.toString();
    }

    /**
     * finds all the characters and their codes
     * @param root the current node
     * @param code will store the path to each character
     * @return none. The method adds the characters and codes to an internally
     * stored hashmap.
     */
    private void findCodesDecode(HuffTree root, String code){
        if (root.left == null && root.right == null) {
            codeDict.put(code,root.element);
            return;
        }
        findCodesDecode(root.left, code + "0");
        findCodesDecode(root.right, code + "1");
    }

    /**
     * Adds a single character to the tree using the given code
     * @param symbol the character to be placed
     * @param code the corresponding huffman code for the character
     * @param index the index of the two previous parameters
     */
    private void addNode(char symbol, String code, int index){

        //case if the next character is left
        if(code.charAt(index) == '0'){

            //if the end of the code is reached, automatically adds node with symbol
            if(index == code.length() - 1){
                left = new HuffTree(symbol);
                return;

            //if there is no node yut, adds a node with null element
            } else if(left == null){
                left = new HuffTree('\0');
            }
            //recurses to left child
            left.addNode(symbol, code, index + 1);

        //case if the next character is right
        } else if(code.charAt(index) == '1'){

            //if the end of the code is reached, automatically adds node with symbol
            if(index == code.length() - 1){
                right = new HuffTree(symbol);
                return;

            //if there is no node yut, adds a node with null element
            } else if(right == null){
                right = new HuffTree('\0');
            }

            //recurses to right child
            right.addNode(symbol, code, index + 1);
        }
    }

    /**
     * allows user to print the contents of the tree
     * @return a string of all the elements in the tree
     */
    public String toString(){
        //creates a stringbuilder to store output from helper method
        StringBuilder output;
        output = inOrderHelper();

        //trims the string output to remove extra whitespacing
        return output.toString().trim();
    }

    /**
     * helper for the inordertoString that returns an output as a stringbuilder
     * @return all the elements in the binary tree in inorder
     */
    private StringBuilder inOrderHelper(){
        StringBuilder output = new StringBuilder();

        //cases for trees with two children, one child, or no child.
        if (right != null && left != null){

            //appends the elements in both subtrees to the stringbuilder
            output.append(left.inOrderHelper()).append(element + " ").append(right.inOrderHelper());
        } else if (left != null){

            //appends the elements in the left subtree to the stringbuilder if tree only has left child
            output.append(left.inOrderHelper()).append(element + " ");
        } else if(right != null){

            //appends the elements in the left subtree to the stringbuilder if tree only has right child
            output.append(element + " ").append(right.inOrderHelper());
        } else {

            //only appends element in current tree if tree has no children
            output.append(element + " ");
        } return output;
    }



}
