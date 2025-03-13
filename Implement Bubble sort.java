import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSortProgram {
    
    // Generate an array with random integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101); // Random number between 0 and 100
        }
        return array;
    }
    
    // Write array to file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Read array from file
    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new int[0];
        }
    }
    
    // Bubble Sort Algorithm
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    // Main method to handle user input and execute functions
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the length of the array: ");
        int length = scanner.nextInt();
        int[] array = createRandomArray(length);
        
        System.out.print("Enter the filename to save the array: ");
        String filename = scanner.next();
        writeArrayToFile(array, filename);
        
        System.out.println("Array saved to " + filename);
        
        System.out.println("Reading array from file...");
        int[] readArray = readFileToArray(filename);
        
        System.out.println("Sorting array...");
        bubbleSort(readArray);
        
        System.out.print("Enter the filename to save the sorted array: ");
        String sortedFilename = scanner.next();
        writeArrayToFile(readArray, sortedFilename);
        
        System.out.println("Sorted array saved to " + sortedFilename);
        
        scanner.close();
    }
}
