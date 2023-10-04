import java.util.*;

public class HashMarkov implements MarkovInterface {

    protected String[] myWords;		// Training text split into array of words 
	protected Random myRandom;		// Random number generator
	protected int myOrder;          // order of WordGram used
    protected static String END_OF_TEXT;
    protected HashMap<WordGram, List<String>> myMap = new HashMap<>();

    public HashMarkov() {
		this(3);
	}

    public HashMarkov(int order){
		myOrder = order;
        myRandom = new Random();
    }

    @Override
    public void setTraining(String text) {
        myWords = text.split("\\s+");
        myMap.clear();
        WordGram myWG = new WordGram(myWords, 0, myOrder);
        for (int k = myOrder; k < myWords.length; k++){
            String str = myWords[k];
            if (! myMap.containsKey(myWG)){
                ArrayList<String> myList = new ArrayList<>();
                myMap.put(myWG, myList);
            }
            myMap.get(myWG).add(myWords[k]);
            myWG = myWG.shiftAdd(str);

        }
    }

    @Override
    public List<String> getFollows(WordGram wgram) {
        if (myMap.containsKey(wgram))
            return myMap.get(wgram);
        return new ArrayList<String>();
    }

    private String getNextWord(WordGram wgram) {
		List<String> follows = getFollows(wgram);
		if (follows.size() == 0) {
			return END_OF_TEXT;
		}
		else {
			int randomIndex = myRandom.nextInt(follows.size());
			return follows.get(randomIndex);
		}
	}

    @Override
    public String getRandomText(int length) {
        ArrayList<String> randomWords = new ArrayList<>(length);
		int index = myRandom.nextInt(myWords.length - myOrder + 1);
		WordGram current = new WordGram(myWords,index,myOrder);
		randomWords.add(current.toString());

		for(int k=0; k < length-myOrder; k += 1) {
			String nextWord = getNextWord(current);
			if (nextWord.equals(END_OF_TEXT)) {
				break;
			}
			randomWords.add(nextWord);
			current = current.shiftAdd(nextWord);
		}
		return String.join(" ", randomWords);
    }

    @Override
    public int getOrder() {
        return myOrder;
    }

    @Override
    public void setSeed(long seed) {
        myRandom.setSeed(seed);
    }
    public static void main(String[] args) {
        
    }
}
