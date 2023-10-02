import java.util.HashSet;
import java.util.Set;

/**
 * A WordGram object represents an immutable
 * sequence of words.
 * For use in Compsci 201, Duke University, Fall 2022
 * Add yourself as an author when you make edits
 * @author Brandon Fain
 * @author Owen Astrachan, revised Fall 2023
 * @author Frannie Goodman
 */

public class WordGram {
	private String[] myWords; 	// Stores WordGram words
	private String myToString;	// Stores space separated words as one string
	private int myHash;			// Stores hash value of WordGram

	
	/**
	 * Constructor should generate a WordGram with size words
	 * beginning at the start index value of source array.
	 * Each element of source array should be a single word.
	 * @param source Source array, each element should be a single word
	 * @param start Index of first word for WordGram object
	 * @param size Number of elements in WordGram object
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		myToString = "";
		myHash = 0;
		for (int k = 0; k < size; k++){
			myWords[k] = source[start + k];
		}
	}

	/**
	 * Returns number of words in this WordGram
	 * @return order of wordgram, number of words
	 */
	public int length() {
		return myWords.length;
	}


	/** 
	 * Returns true if o is also a wordgram with the
	 * same words, otherwise returns false 
	*/
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram other = (WordGram) o;
		if (other.myWords.length != this.myWords.length){
			return false;
		}
		for (int k = 0; k < this.myWords.length; k++){
			if (! (this.myWords[k]).equals(other.myWords[k])){
				return false;
			}
	}
	return true;
	}


	/**
	 * Returns a hashCode for WordGram object equal to 
	 * the hashCode of the space separated words stored in 
	 * the WordGram.
	 */
	@Override
	public int hashCode() {
		if (myHash == 0){
			for (int i = 0; i < this.toString().length(); i++){
				myHash += (this.toString().charAt(i))*31^(length() - (i + 1));
			}
		}
		return myHash;
	}


	/**
	 * Return a new WordGram of the same length as this WordGram 
	 * consisting of words 1 through length-1 of this WordGram
	 * followed by last. Does NOT mutate this WordGram.
	 * @param last added as last string of returned WordGram
	 * Should be a single word
	 * @return new WordGram
	 */
	public WordGram shiftAdd(String last) {
		String[] myNewWords = new String[length()];
		for (int k = 1; k < length(); k++) myNewWords[k-1] = myWords[k];
		myNewWords[length() - 1] = last;
		WordGram wg = new WordGram(myNewWords, 0, length());
		return wg;
	}

	/**
	 * Returns space separated words stored in the WordGram
	 * as a single String.
	 */
	@Override
	public String toString() {
		if (myToString.equals("")){
			for (int k = 0; k < myWords.length -1; k ++){
				myToString += " " + this.myWords[k];
			}
			myToString += myWords[myWords.length -1];
		}
		return myToString;
	}
public static void main(String[] args) {
	String[] words = "Computer science lecture is right now".split(" ");
	 Set<Integer> set = new HashSet<Integer>();
	 WordGram[] myGrams = new WordGram[3];
	 myGrams[0] = new WordGram(words, 0, 5);
	 myGrams[1] = new WordGram(words, 1, 4);
	 myGrams[2] = new WordGram(words, 2, 3);
	 for(WordGram w : myGrams) {
            System.out.print(w.hashCode());
			System.out.print(" ");
        }
}
}