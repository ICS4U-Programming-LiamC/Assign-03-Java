import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program does option of the assigments
 * hence: 1
 * 121
 * 1213121
 * 121312141213121 ... line break at every number greater than 5 if at the end
 * of the line
 * does not go above 15 for formatting reasons
 *
 * @author Liam Csiffary
 * @version 1.0
 * @since 2022-04-11
 */

public class RecursionAssign {

  // generates the array of inputs from a text file
  public static String[] arrayGenerator(File file) {

    // defining vars
    List<String> originalListString = new ArrayList<String>();
    System.out.println(file);
    try (Scanner scStudent = new Scanner(file)) {
      // pass the path to the file as a parameter
      while (scStudent.hasNextLine()) {
        originalListString.add(scStudent.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // for testing purposes
    System.out.println(originalListString);

    // create array of length
    String[] array = new String[originalListString.size()];

    // fill the array
    for (int i = 0; i < array.length; i++) {
      array[i] = originalListString.get(i);
    }

    return array;
  }

  // also from https://www.w3schools.com/java/java_files_create.asp
  public static void writer(String[] arr) {
    try {
      // creates file writing object
      FileWriter myWriter = new FileWriter("numbered.txt");

      // my code
      // just writes to the file the same way you'd print it to the terminal
      // except with myWriter.write instead of System.out.print()
      for (int each = 0; each < arr.length; each++) {
        myWriter.write(arr[each] + "\n");
      }

      // closes the file
      myWriter.close();
      System.out.println("Successfully wrote to the file.");

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static String numbery(int numToGoToo, int currentNum, String finalString) {

    if (numToGoToo == currentNum) {
      return finalString;

    } else if (currentNum > 5) {
      return numbery(numToGoToo, currentNum + 1, finalString + currentNum + "\n" + finalString);
    } else {
      return numbery(numToGoToo, currentNum + 1, finalString + currentNum + finalString);
    }
  }

  public static void main(String[] args) {

    // creates the file object
    File file = new File(System.getProperty("user.dir") + "/testCases.txt");

    // gets the array of testCases from the ArrayGenerator
    String[] testCases = arrayGenerator(file);

    // goes to next line for beautifying reasons
    System.out.println("\n");

    // an array to be passed to the txt file creator
    String[] txtArray = new String[testCases.length];

    // prints the normal string and the reversed string for each index
    // of the array, also sends it down an extra line so it's easier to read
    for (int i = 0; i < testCases.length; i++) {
      System.out.println("Original number: " + testCases[i]);
      String numbery = ("Number thing: \n" + numbery(
          Integer.parseInt(testCases[i]) + 1, 1, "") + "\n");
      System.out.println(numbery);
      txtArray[i] = numbery;
    }
    writer(txtArray);
  }
}
