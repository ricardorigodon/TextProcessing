import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.Set;
import java.util.Arrays;


/** 
 *	Main class for tokenizer. Will read in user argument for the filename that they selected and call corresponding methods in the tokenizer
 *	class to tokenize and count the frequencies. After that, it will create the 3-grams, compute the frequencies, and at the end print
 *	the values of the tokens with their frequencies along with the 3-grams.
 *
 * @author  Ricardo Rigodon
 */

public class Project1{

	//constant variable to represent location of filename argument
	public static final int FILENAME_ARG = 0;

	public static void main(String[] args){


	/* Checks for incorrect user input */

	if(args.length == 0){

		System.err.println("Please enter the name of the file. Will be called via java Project1 [name of your file]");
		System.exit(1);
	}

	if(args.length > 1){

		System.err.println("Please enter just the name of the file. For example, java Project1 tokens.txt");
		System.exit(2);
	}

	//filename
	String filename = args[FILENAME_ARG];

	System.out.println("\nWelcome to my Tokenizer!");
	System.out.println("Here are the tokens and 3-grams in your file along with their frequencies!\n");

	//get instance of class Tokenizer
	Tokenizer tokenizer = Tokenizer.getInstance();

	//tokens
	ArrayList<String> tokens = new ArrayList<String>();

	tokens = tokenizer.tokenize(filename);

	//list to hold the grams
	ArrayList<String> grams = new ArrayList<String>();

	//create the 3 grams from the file
	grams = tokenizer.construct3Grams();

	//print the results of the tokens and grams
	tokenizer.print(tokens, grams);

	System.out.println("\nThank you!\n");	
		
}

}