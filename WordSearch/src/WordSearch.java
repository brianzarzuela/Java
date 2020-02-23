/**
 * <p>
 *     The WordSearch class reads in a text file of a wordsearch puzzle and searches for the word in the puzzle
 *     WordSearch.java
 * </p>
 * @author Brian Zarzuela
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class WordSearch {
    int dimension;
    Scanner fileReader;
    File file;
    ArrayList<String> puzzleRows=new ArrayList<>(); //initialize the puzzle

    /**
     * generates a word such puzzle from a .txt file as an array of strings
     * preconditions: puzzle should be nxn (that is, a square)
     * postconditions: file, filereader, dimension and puzzleRows are initialized after calling this method
     */
    public void generatePuzzle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! this program takes a .txt file and solves a word search!");
        System.out.print("Enter the name of the file you wish to test: ");
        String name = scanner.next();
        file=new File(name);
        if (name.equals("exit")){
            System.exit(1);
        }
        try {
            fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                //System.out.println(Arrays.toString(row) + "length: " + row.length);
                row=row.replaceAll("\\s+","");
                puzzleRows.add(row);
            }
            dimension=puzzleRows.size();

        } catch (FileNotFoundException e) {
            System.out.println("No such file!");
            generatePuzzle();
        }
    }

    /**
     * print the word puzzle to the console
     * preconditions: puzzle must be initialized first with generatePuzzle()
     */
    public void printPuzzle(){
        System.out.println("\n-Puzzle-\n");
        for (int i=0; i<puzzleRows.size();i++){
            System.out.println(puzzleRows.get(i));
        }
        System.out.println();
    }

    /**
     * searches for a word forwards and backwards in the word search puzzle and returns true if found
     * @param userWord String that is the word to be searched for
     * @returntrue if the word is located, false if it is not
     * preconditions: Generate puzzle must be called prior to this method but that is assured in main in this file
     */

    public boolean searchHorizontal(String userWord){
        try {
            Pattern forward = Pattern.compile(userWord);
            StringBuilder revWordSB = new StringBuilder(userWord);
            revWordSB = revWordSB.reverse();
            String revWord = revWordSB.toString();
            Pattern backward = Pattern.compile(revWord);
            Scanner hScanner = new Scanner(file);
            int counter=0;
            while (hScanner.hasNextLine()) {
                String line=hScanner.nextLine();
                line=line.replaceAll("\\s+","");
                //System.out.println(line);
                Matcher lineF = forward.matcher(line);
                if (lineF.find()){
                    System.out.println(userWord+ " found forwards at row " + (counter+1) + ", index " + (lineF.start()+1));
                    return true;
                }
                Matcher lineB=backward.matcher(line);
                if (lineB.find()){
                    System.out.println(userWord+ " found backwards at row " + (counter+1) + ", index " + (lineB.start()+1));
                    return true;
                }

                counter++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * rotates the puzzle 90 degrees and functions similar to search horizontal to search for words vertically, both forwards and backward
     * @param userWord String that is the word to be searched for
     * @return boolean; true if the word is found, false if it is not
     */
    public boolean searchVertical(String userWord){
        //rotate the puzzle so you can search for vertical strings horizontally
        String[] columns=new String[dimension];
        for (int i=0; i<dimension; i++){
            String column="";
            for (int j=0; j<dimension; j++){
                column+=puzzleRows.get(j).substring(i, i+1);
            }
            columns[i]=column;
        }
        Pattern forward=Pattern.compile(userWord);
        StringBuilder revWordSB=new StringBuilder(userWord);
        revWordSB=revWordSB.reverse();
        String revWord=revWordSB.toString();
        Pattern backwards=Pattern.compile(revWord);
        int counter=0;
        for (String line : columns){
            Matcher lineF=forward.matcher(line);
            if (lineF.find()){
                System.out.println(userWord+ " found vertically forwards at column " + (counter+1) + ", index " + (lineF.start()+1));
                return true;
            }
            Matcher lineB=backwards.matcher(line);
            if (lineB.find()) {
                System.out.println(userWord + " found vertically backwards at column " + (counter + 1) + ", index " + (lineB.start() + 1));
                return true;
            }
            counter++;
        }
        return false;
    }

//    public boolean searchDiagonal(String userWord){
//        int count = 0;
//        while(count != userWord){
//            for (int i=0; i<puzzleRows.size();i++){
//                for (int j=0; j<puzzleRows.size();j++)
//                {
//                    if puzzleRows.get(i).charAt(i) ==
//                }
//            }
//        }
//
//        return false;
//    }

    /**
     * calls searchHorizonttal and searchVertical to search for the word int he puzzle
     * @param userWord String that is the word to be searched for
     * @return boolean; true if the word is found false if it is not
     */
    public boolean searchForWord(String userWord){
        if (searchHorizontal(userWord)){
            return true;
        }
        if (searchVertical(userWord)) {
            return true;
        }
//       if (searchDiagonal(userWord)){
//           return true;
//       }
        System.out.println(userWord + "not found.");
        return false;
    }

    public static void main(String[] args){
        WordSearch wordSearch=new WordSearch();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome! This program generates a new word ");
        wordSearch.generatePuzzle();
        wordSearch.printPuzzle();
        System.out.println("What word would you like to search for?");
        String userWord = scanner.next();
        boolean loop=true;
        while (!userWord.equalsIgnoreCase("exit")) {
            wordSearch.searchForWord(userWord);
            System.out.println("Enter another word to search for or type 'exit' to quit");
            userWord=scanner.next();
        }
        System.out.println("Thank you for using this program!");
        System.exit(2);
    }
}