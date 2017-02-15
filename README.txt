--------------------------------------------------------------------------------------------------
Ricardo Rigodon
----------------------------------------------------------------------------------------------------
A B O U T T H I S P R O J E C T 
----------------------------------------------------------------------------------------------------
Using my Java programming skills, I created these text-processing methods/functions to use on a
text file to break up the text file into the corresponding tokens and 3-grams.
----------------------------------------------------------------------------------------------------
W H A T Y O U N E E D
----------------------------------------------------------------------------------------------------
Java installed on your machine.
JDK to use javac/java to run source code.

Java must be at least Java 8, uses entrySet stream for Map which was introduced in Java 8.

To run program:

java Project1 [fileName where you have your sentences]

To compile program:

javac Project1.java

Please have your file in same directory as Project1.
----------------------------------------------------------------------------------------------------
I N P U T / O U T P U T
----------------------------------------------------------------------------------------------------
The user will create a file of sentences that they want to be processed ideally a text file 
such as tokens.txt that the user will feed to be tokenized and have the 3-grams generated.

This file will contain sentences such as:

This class is so great.
I like ketchup and mustard.
Read this backwards and cry.

Where my program will process that and deliver the tokens back to you.


The output will be a list of the tokens found along with the frequencies that they appeared in the
text file. Everytime a token appears regardless of capatilization- TCNJ vs tcnj is same token - this 
is counted only once. TCNJ and tcnj are not unique tokens, they are the same. 

Similarly for the 3-grams only unique 3-grams will be held and any capitalization changes will not be
counted as new 3-grams. The 3-grams I love cheese and I LOVE CHEESE although reading them out loud
will have different meanings my program will count these two 3-grams as the same and increment the
number of times it saw that 3-gram.

Example:

File:

"tokens.txt"
-------------------------------------
April is the cruellest month 
aPRIL IS THE CRUELLEST MONTH
April is the cruellest month 
aPRIL IS THE CRUELLEST MONTH
-------------------------------------


OUTPUT:

in Console
___________________________________________________________________________

Welcome to my Tokenizer!
Here are the tokens and 3-grams in your file along with their frequencies!

TOKENS and frequencies in text file:
------------------------------------
the=4
cruellest=4
month=4
is=4
April=4
------------------------------------
Number of tokens : 5
------------------------------------
3-grams and frequencies in text file.
------------------------------------
April is the=4
is the cruellest=4
the cruellest month=4
month aPRIL IS=3
cruellest month aPRIL=3
------------------------------------
Number of 3-grams : 5
------------------------------------

Thank you!
___________________________________________________________________________

----------------------------------------------------------------------------------------------------
L I M I T A T I O N S / B U G S 
----------------------------------------------------------------------------------------------------
I haven't observed any bugs in the final product, but in the products before this.
I came to your office hours to discuss other things but also this project which the method I was using
before to generate the 3-grams would not handle new-line characters well. I had to use a regex to
remove the new-line characters from the array of the fileContent before creating the 3-grams from it.

I tried my best to demonstrate error handling especially for the user entering in too many arguments
or too few along with a somewhat helpful error message to help the user understand what they might
have done wrong.
----------------------------------------------------------------------------------------------------
S P E C I A L  F E A T U R E S
----------------------------------------------------------------------------------------------------
My program says hello to you so that's unique I guess. Is anyone in our class doing that? NO

On a more serious note, I think I might be somewhat unique in using hashmaps along with the Java 8
entry set in order to sort the hashmaps by decreasing frequency (from greatest to least) instead
of instead possibly going through each map entry and sorting it that way. I could of kept track
of the keys in order in a list of some sorts and then just print the key with the value, but the entry
set method was much simpler and more efficient to implement. Also, using hashset to hold the unique
tokens/3-grams and using a boolean value to check if that token/3-gram existed already or not in a temp
list that would hold all the ones from the set.

Also using a private constructor, making this thread-safe was something I learned from my internship
and it doesn't hurt to use. And makes my program safer as well.

----------------------------------------------------------------------------------------------------
D E S I G N
----------------------------------------------------------------------------------------------------
I used the following data structures in my design:

String arrays
Hashmap
Hashset
Array lists

I also used:

String builder class - to build the 3 grams.
Scanner - to read the string read in from the file.
File Reader - to read file and create a string from the characters.

Creating tokenize and finding a way to seperate the unique tokens from everything else was key.
Using equalsIgnoreCase was a big help as this String method allows you to see if a String is equal
regardless of capalization changes. If the token was not already in the set, I would add it to the set.
Each time I would check a temporary list that held all the values of the set and check equalsIgnoreCase
on them. I would follow this same process for the 3grams instead I would first create the 3-grams which
was a different process from simply grabbing the next token via Scanner.next(). I used a string builder
to concat the string into chunks of 3 and create the shingles or 3-grams I needed. 
----------------------------------------------------------------------------------------------------
R E S O U R C E S U S E D 
----------------------------------------------------------------------------------------------------
stackoverflow.com (Used for entry set and creating 3-grams)
http://stackoverflow.com/questions/3656762/n-gram-generation-from-a-sentence
http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
----------------------------------------------------------------------------------------------------