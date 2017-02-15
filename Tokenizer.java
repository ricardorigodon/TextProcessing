import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;
import java.io.*;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;


public class Tokenizer{

	public static Tokenizer tokenizer = new Tokenizer();
	public static final int ZERO_COUNT = 0;
	public static final int SIZE_OF_GRAM = 3;
	public static int tokenCount = 0;
	public static int gramCount = 0;
	public static String fileContent = "";
	HashMap<String,Integer> tokenMap = new HashMap<String,Integer>();
	HashMap<String,Integer> gramMap = new HashMap<String,Integer>();
	String[] gramWords;


	//private constructor
	private Tokenizer(){

	}


	//returns instance of class
	public static Tokenizer getInstance(){

		return tokenizer;
	}



	/**
	 * This method will tokenize the file split it up into tokens and return a list of unique tokens by first using a hashset to hold them
	 * using equalsIgnoreCase to make sure there are no duplicates.
	 * @param  fileName name of the file (ex. tokens.txt)
	 * @return  tokens  list of the unique tokens.
	 */
	public ArrayList<String> tokenize(String fileName){

		String content = null;

		ArrayList<String> tokens = new ArrayList<String>();


		File file = new File(fileName);


		FileReader reader = null;

		try{

			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			fileContent = new String(chars);

			reader.close();

		}catch(IOException e){

			e.printStackTrace();
		}

		finally{

			// if reader is not null, close it.
			if(reader != null){

			try{

				reader.close();

			}catch(IOException e){

				e.printStackTrace();
			}

			}
		}


		//will hold unique tokens ONLY regardless of capitalization (ex. TCNJ vs tcnj)
		Set<String> tokenSet = new HashSet<String>();

		Scanner scanner = new Scanner(fileContent);



		boolean tokenExists = false;

		//list to hold tokens already entered
		ArrayList<String> tempTokens = new ArrayList<String>();
		ArrayList<String> allTokens = new ArrayList<String>();

		//reading the string of the textFile for whitespace seperated tokens
		while(scanner.hasNext()){

			//grab next token
			String token = scanner.next();
			
			for(String s : tempTokens){

				//'tcnj' vs TCNJ count as one token
				if(token.equalsIgnoreCase(s)){

					tokenExists = true;

				}

			}

			//if token doesn't exist, add to set and tempList
			if(!tokenExists){

				tokenSet.add(token);
				tempTokens.add(token);

			}
			else{

				tokenExists = false;
			}

		}

		for(String s : tokenSet){

			
			tokens.add(s);
		}

		
		return tokens;
		
	}

	/**
	 * Prints out the information to the user about the textfile they requested.
	 * Prints out the tokens and 3-grams along with their frequencies.
	 * @param tokens list of unique tokens
	 * @param grams  list of unique 3-grams
	 */

	public void print(ArrayList<String> tokens, ArrayList<String> grams){

		//prints tokens onto screen
		//orders by decreasing frequency
	
		System.out.println("TOKENS and frequencies in text file:");

		computeWordFrequencies(tokens);


		System.out.println("------------------------------------");





		/*
		 * This snippet of code used is from http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
		 * It uses Java 8 capability that allows you to cast the map to a stream and then sort on that using comparator from Map.Entry.
		 * forEach is then used to call System.out.println on that.
		 */
		 
		tokenMap.entrySet().stream()	
         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
         .forEach(System.out::println);


         System.out.println("------------------------------------");
         
         System.out.println("Number of tokens : " +getTokenCount());


         System.out.println("------------------------------------");

         System.out.println("3-grams and frequencies in text file.");

         System.out.println("------------------------------------");

         compute3GramFrequencies(grams);

           gramMap.entrySet().stream()	
         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
         .forEach(System.out::println);

         System.out.println("------------------------------------");

         System.out.println("Number of " + SIZE_OF_GRAM + "-grams : " +getGramCount());


         System.out.println("------------------------------------");




	}

	/**
	 * Computes the frequencies of the 3-grams in the file by use of equalsIgnoreCase and using a scanner
	 * on the fileContent- the characters of the file.
	 * @param tokens unique tokens to check against
	 */

	public void computeWordFrequencies(ArrayList<String> tokens){

		//takes a list of tokens as a parameter, 
		//counts the total number of words and their frequencies in the list, 
		//and create a list of pairs, each of which contains a token and its frequency.
		
		//instantiate hashmap for tokens
		instTokenMap(tokens);

		//set word count of how many tokens
		setTokenCount(tokens.size());


		//now we count the frequencies of the tokens that we created a map for
		
		Scanner scanner = new Scanner(fileContent);

		while(scanner.hasNext()){

			String token = scanner.next();
			
			for(String s : tokens){

		
				if(token.equalsIgnoreCase(s)){
				
					increaseFrequency(s);

				}

			}


		}

	}

	/**
	 * Computes the frequencies of the 3-gram and how many times it appeared in the file. Each time will call increase on the string
	 * which will go to the gram map grab that key and increment the value/frequency associated with it.
	 * @param grams  array list of unique grams
	*/
	public void compute3GramFrequencies(ArrayList<String> grams){

		//counts the total number of 3-grams and their frequencies in a token list. 
		//A 3-gram is three words that occur consecutively in a file. 
		//For example, in the sentence “April is the cruellest month”, 
		//there are three 3-grams and they are: “April is the”, “is the cruellest”, and “the cruellest month”.
		//
		
		
		instGramMap(grams);

		setGramCount(grams.size());

		ArrayList<String> tempGrams = new ArrayList<String>();

		for(int i = 0; i < gramWords.length - SIZE_OF_GRAM + 1; i++){

			String gram = concat(gramWords, i, i +  SIZE_OF_GRAM);

			tempGrams.add(gram);

	}

		for(String gram : tempGrams){

		for(String s : grams){


			if(gram.equalsIgnoreCase(s)){

				increaseGramFrequency(s);
			}
		}

	}

		

	}

	/**
	 * Creates the 3 grams from the file content by using string builder to break up words into the corresponding 3 grams.
	 * @return grams List of unique 3 grams.
	 */
	public ArrayList<String> construct3Grams(){

		//filter out end of line
		String[] tempWords = fileContent.replaceAll("(\\r|\\n)", " ").split(" ");
		ArrayList<String> tempList = new ArrayList<String>();


		//filter empty strings out of content array
		for(String s : tempWords){

			if(!s.isEmpty()){

				tempList.add(s);
			}
		}


		//create a string array from the list
		gramWords = tempList.toArray(new String[tempList.size()]);


		ArrayList<String> grams = new ArrayList<String>();
		ArrayList<String> tempGrams = new ArrayList<String>();
		boolean gramExists = false;

		//will hold unique 3-grams ONLY regardless of capitalization (ex. TCNJ vs tcnj)
		Set<String> gramSet = new HashSet<String>();

		for(int i = 0; i < gramWords.length - SIZE_OF_GRAM+ 1; i++ ){

			String gram = concat(gramWords, i, i + SIZE_OF_GRAM);

			for(String s : tempGrams){

				if(gram.equalsIgnoreCase(s)){

					gramExists = true;

				}

			}
				if(!gramExists){

				gramSet.add(gram);
				tempGrams.add(gram);

				}

				else{

				gramExists = false;

				}

			}



		for(String s : gramSet){

			
			grams.add(s);
		
		}




		return grams;

	}

	//Concatenates two strings together from a starting point and an end point.
	// Used to build the 3-grams which I learned by following here: http://stackoverflow.com/questions/3656762/n-gram-generation-from-a-sentence
	public String concat(String[] words, int start, int end){


		StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString()		;
	}

    


	//instantiates the token map with the proper tokens values (String, int)
	private void instTokenMap(ArrayList<String> tokens){


		//total number of words
		int tokenCount = tokens.size();

		HashMap<String,Integer> tempMap = new HashMap<String,Integer>();


		for(String s : tokens){

			tempMap.put(s, ZERO_COUNT);
		}

		tokenMap = tempMap;

	}

	/**
	 * instantiates the token map with the proper tokens values (String, int)
	 * @param grams list of unique grams
	 */
	private void instGramMap(ArrayList<String> grams){


		HashMap<String,Integer> tempMap = new HashMap<String,Integer>();


		for(String s : grams){

			tempMap.put(s, ZERO_COUNT);
		}

		gramMap = tempMap;

	}

	//increase frequency of tokenMap
	private void increaseFrequency(String token){

		//replace map instance with another instance with the frequency incremented.
		tokenMap.put(token, tokenMap.get(token) + 1);


	}

	//increase frequency of gram map
	private void increaseGramFrequency(String gram){

		//replace map instance with another instance with the frequency incremented.
		gramMap.put(gram, gramMap.get(gram) + 1);


	}

	//get amount of tokens
	private int getTokenCount(){

		return tokenCount;
	}

	//set amount of tokens
	private void setTokenCount(int n){

		tokenCount = n;
	}

	//get amount of 3-grams
	private int getGramCount(){

		return gramCount;
	}


	//set amount of grams
	private void setGramCount(int n){

		gramCount = n;
	}
	
}
