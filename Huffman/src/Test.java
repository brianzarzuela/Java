/**
 * <h1>Hoffman Test File</h1>
 * This is a java class for testing the implementation of the Huffman encoding class.
 *
 * @author Brian Zarzuela
 *
 * @version 0.0, 2019-02-26
 *
 * @since 2019-02-26
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Test {

    public static void huffmanTest(String pathname) throws FileNotFoundException {
        Huffman huffy = new Huffman(pathname);
        huffy.createEncodingInfoFile();
        huffy.createEncodedFile();
    }

    public static void main(String[] args){
        System.out.print("Enter the pathname for the file to be encoded: ");
        Scanner scanner = new Scanner(System.in);
        String pathname = scanner.nextLine();
        /*
        File file = new File(pathname);
        if (!(file.isFile() && file.canRead())){
            System.out.println(file.getName() + " cannot be read from.");
        }
         */
        try {
            huffmanTest(pathname);
        } catch (IOException e) {
            System.out.println("File does not exist, check pathname ---->");
            e.printStackTrace();
        }
    }

}
