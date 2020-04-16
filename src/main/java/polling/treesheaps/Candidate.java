
package polling.treesheaps;

/**
 * Candidate class to store a single row of a csv file
 *
 * @author Joseph Kawamura
 * @version 15 April 2020
 */
public class Candidate implements Comparable<Candidate>{
    private String surname; //holds the last name of the candidate
    private String name; //holds the candidate's full name
    private double poll; //holds the polling data for the candidate

    /**
     * Constructor for candidate class
     * @param candidate takes a string array containing candidate name, last name, and poll numbers
     */
    public Candidate(String[] candidate){
        this.surname = candidate[0];
        this.name = candidate[1];
        this.poll = Double.parseDouble(candidate[2]);
    }

    @Override
    /**
     * modifies the compareto method to only compare the polling numbers of the candidates
     * or the last names if the polling data is the same
     * @param input takes an instance of the candidate class
     */
    public int compareTo(Candidate input) {

        //checks if the polling data is different
        if(poll != input.poll){

            //compares the polling numbers
            return Double.compare(poll, input.poll);

        //if the polling data is the same, it compares the surname of candidates.
        } else{
            return surname.compareTo(input.surname);
        }
    }

    /**
     * tostring for the candidate class
     * @return a string with all of the candidate's information formatted
     */
    public String toString(){

        //creates a stringbuilder containing only the person's name and their poll numbers
        StringBuilder output = new StringBuilder(name);
        output.append(":" + poll);
        return output.toString();
    }
}

