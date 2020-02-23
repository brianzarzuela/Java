/**
 * <h1>Huffman Encoder Class</h1>
 * <p1>
 * Accepts a command line parameter for the file to be encoded.
 * Generates a binary representation for each character present in the input file.
 * Provides two output files:
 *  1. The first file contains all of the encoding information in csv format.
 *  2. The second file is the encoded version of the input file.
 *      This file includes the header needed for decoding.
 * </p1>
 *
 * @author Brian Zarzuela
 *
 * @version 0.6, 2019-02-28
 *
 * @since 2019-02-26
 */

import java.io.*;
import java.util.ArrayList;

public class Huffman{
    /**
     * frequencyTable[MAX_ASCII_VALUE] is the huffman frequency table for this class.
     * Each index represents an ascii code for any of the characters in the
     * extended 8-bit ascii table. The value at each position will be filled with
     * the frequency of which that specific ascii code appears within the file.
     */
    private final int MAX_ASCII_VALUE = 255;
    private int[] frequencyTable = new int[MAX_ASCII_VALUE];
    private FileInputStream input;
    private ArrayList<Node> huffman = new ArrayList<>();
    private Node head = null;
    private File output;
    private String inputPath;

    /**
     * Constructor for the Hoffman class
     * @param pathname Pathname for the file to be encoded
     */
    Huffman(String pathname) throws FileNotFoundException {
        inputPath = pathname;
        input = new FileInputStream(new File(pathname));
        fillFrequencyTable();
    }

    /**
     * Recursive function for finding the binary code for each node determined by the first node in the tree [head].
     * If the node was reached from the left a zero is added, if it was reached from the right a one is added.
     * @param node Current node
     * @param s String representing the binary code for this node
     */
    private void binaryTreeEncoding(Node node, String s){
        if (node.left == null && node.right == null){
            node.binaryCode = s;
            huffman.add(node);
            return;
        }

        binaryTreeEncoding(node.left, s + "0");
        binaryTreeEncoding(node.right, s + "1");
    }

    /**
     * Creates the huffman nodes each containing the ascii character values and their associated frequencies.
     * Sorts the huffman arraylist using CustomComparator and creates new nodes for the last two nodes
     * before removing them from the list. These last two nodes are then used to create a new internal node that
     * will contain left and right pointers to the previous two nodes and the sum of their frequencies for resorting.
     * Copies the last remaining internal node into the head node used for creating the binary tree
     */
    private Node algorithm(){
        for(int i = 0; i < MAX_ASCII_VALUE; i++)
            if (frequencyTable[i] > 0)
                huffman.add(new Node(i, frequencyTable[i]));

        while(huffman.size() > 1){
            huffman.sort(new CustomComparator());
            Node left = huffman.remove(huffman.size() - 1);
            Node right = huffman.remove(huffman.size() - 1);
            Node internal = new Node(MAX_ASCII_VALUE + 1, 0);
            internal.frequency = left.frequency + right.frequency;
            internal.left = left;
            internal.right = right;
            head = internal;
            huffman.add(internal);
        }

        return head;
    }

    /**
     * Fills the huffman frequency array table.
     * Reads the ascii code for every character in the input file stream
     * and increments the frequency value at that position in the hoffman array.
     */
    private void fillFrequencyTable(){
        try{
            int cur;
            while (input.available() > 0) {
                cur = input.read();
                frequencyTable[cur] += 1;
            }
            input.close();
            algorithm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the first file output file containing all of the encoding information as csv.
     * The file created will be named encodingInformation.csv and will be located in the project root directory.
     * Terminal will display whether the file has been created or already exists and has been updated.
     * The first line of the file is the header and will always contain character,binary code
     */
    public void createEncodingInfoFile(){
        String encodeInfoPathname = "encodingInformation.csv";
        File encodeInfo = new File(encodeInfoPathname);
        try {
            if(encodeInfo.createNewFile())
                System.out.println("File " + encodeInfoPathname + " created in project root.");
            else
                System.out.println("File " + encodeInfoPathname + " already exists in project root directory. File updated.");

            PrintWriter writer = new PrintWriter(encodeInfoPathname);

            StringBuilder csv = new StringBuilder();
            csv.append("character");
            csv.append(',');
            csv.append("binary code");
            csv.append('\n');
            binaryTreeEncoding(head, "");
            huffman.remove(0);
            for (Node node : huffman){
                csv.append((char)node.ascii);
                csv.append(',');
                csv.append(node.binaryCode);
                csv.append('\n');
            }

            writer.write(csv.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEncodedFile(){
        StringBuilder content = new StringBuilder();
        String encodedFilePathname = "encodedFile.txt";
        try{
            FileOutputStream outputStream = new FileOutputStream(encodedFilePathname);

//            for (Node node : huffman){
//                content.append(node.ascii);
//                content.append(' ');
//                content.append(node.binaryCode);
//                content.append('\n');
//            }
//            content.append("#endheader\n");

//            for (Node node : huffman){
//                outputStream.write(node.ascii);
//                outputStream.write('\n');
//                outputStream.write(node.binaryCode);
//                outputStream.write('\n');
//            }

            input = new FileInputStream(new File(inputPath));
            int cur;
            while (input.available() > 0) {
                cur = input.read();
                for (Node node : huffman)
                    if (cur == node.ascii)
                        content.append(node.binaryCode);
            }

            String text = content.toString();
            outputStream.write((text.length() % 8));
            int tempByte;
            while(text.length() >= 8)
            {
                tempByte = Integer.parseInt(text.substring(0, 7));
                text = text.substring(8);
                outputStream.write((char) tempByte);
            }
            if (text.length() > 0)
                outputStream.write((char) Integer.parseInt(text));

            outputStream.close();
            System.out.println("File " + encodedFilePathname + " created in project root.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDecodedFile(){
        String decodedFileName = "decodedFile.txt";
        ArrayList<Integer> key = new ArrayList<>();
        try {
            FileOutputStream outputStream = new FileOutputStream(decodedFileName);
            input = new FileInputStream(new File("encodedFile.txt"));
            int sizeOfLastByte = input.read();
            int cur;
            String intByte;
            String temp;
            while (input.available() > 0) {
                cur = input.read();
                // check if this if the last character
                if (input.available() <= 0) {

                } else {
                    temp = "";
                    intByte = Integer.toBinaryString(cur);
                    for (int i = 0; i < intByte.length(); i++) {
                        if (key.indexOf(Integer.parseInt(temp)) >= 0) {
                            outputStream.write(key.get(key.indexOf((Integer.parseInt(temp)) + 1)));
                            temp = "";
                        } else {
                            temp += intByte.substring(i, i + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
