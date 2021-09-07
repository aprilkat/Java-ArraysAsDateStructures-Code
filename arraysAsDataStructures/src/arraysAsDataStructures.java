
/*
// Author: April Bollinger
// Program: Arrays as Data Structures
// Purpose: to learn how to sort and add to arrays
// Date: 2/6/2021
*/

import java.util.Scanner;



public class arraysAsDataStructures {

    public static void main(String[] args) {
        // Setting up the array
        // This is programmed so that if you change the 1000 here and on line 39 the program will still run
        int[] numbers = new int[1000];
        // Setting up a temporary variable to hold the random numbers
        int tempnum = 0;
        // For loop to put random numbers into the numbers array
        for(int i = 0; i < numbers.length; i++){
            // Multiplying the random number by 1000, so there will be 1, 2, and 3 digit numbers
            tempnum = (int)(Math.random() * 1000);
            numbers[i] = tempnum;
        }

        // Calling the array that will print out the values in the array
        System.out.println("Here is the unsorted array. \n");
        printingArray(numbers);
        System.out.println("");
        System.out.println("Here is the sorted array. \n");
        mergeSort(numbers);
        printingArray(numbers);

        // Setting up the variables needed to ask for input and change the array
        int m = 1;
        int[] tempArray = new int[1000 + m];
        String control = "y";
        int result = 0;
        Scanner sc = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        int finish = numbers.length - 1;
        int start = 0;
        
        while(control.equalsIgnoreCase("y")){
            int key = 0;
            System.out.println("\nPlease enter the number you would like to add to the array. ");
            key = sc.nextInt();

            BinarySearch(numbers, start, finish, key);

            // Calling the binary Search method
            result = BinarySearch(numbers, 0, finish, key);

            // First block decides it is not in the array and adds it to the array
            if (result == -1){
                System.out.println("The value you entered was not in the array. ");
                // Copying and adding more spaces to the array
                System.arraycopy(numbers, 0, tempArray, 0, numbers.length);
                int n = tempArray.length;
                tempArray[n - 1] = key;
                numbers = tempArray;
                System.out.println("I have added it to the array for you. ");
                // Calling both the sort and the print methods
                mergeSort(numbers);
                printingArray(numbers);
                // Where the control for the while loop could change
                System.out.println("\nWould you like to enter another number? (y/n) ");
                control = scan.nextLine();

            }
            // Second block tells the user that the number is already present and tells them to try again
            if (result > -1){
                System.out.println("The number you entered was already in the array. ");
                System.out.println("Please try again. ");
            }
    
       
    }
        // Say goodbye
        System.out.println("Thank you for using this application! ");
        // Closing the scanners
        // If you try to use the same scanner for multiple inputs, the program will not run right
        sc.close();
        scan.close();
    }

    // The method that prints out the array
    public static void printingArray(int[] numbers) {
        // For loop that goes through all the elements in the list
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }

    }

    public static int[] mergeSort(int[] numbers) {
        // I am using the merge sort algorithm
        if (numbers.length > 1) {
            // Sorting the first half of the array
            int[] half1 = new int[numbers.length / 2];
            System.arraycopy(numbers, 0, half1, 0, numbers.length / 2);
            mergeSort(half1);

            // Sorting the second half of the array
            int half2Length = numbers.length - numbers.length / 2;
            int[] half2 = new int[half2Length];
            System.arraycopy(numbers, numbers.length / 2, half2, 0, half2Length);
            mergeSort(half2);

            // Combining the halves together again
            merge(half1, half2, numbers);

        }
        return numbers;
    }

    public static int[] merge(int[] half1, int[] half2, int[] numbers) {
        // Variable for the index of array1
        int currentindex1 = 0;
        // Variable for the index of array2
        int currentindex2 = 0;
        // Variable for the index of tempArray
        int currentindex3 = 0;

        // && means and this is also true

        // Recursively going through the numbers until they are all sorted and it can't
        // switch any more numbers around
        while (currentindex1 < half1.length && currentindex2 < half2.length) {
            // Comparing the two sides and combining them back together
            if (half1[currentindex1] < half2[currentindex2])
                numbers[currentindex3++] = half1[currentindex1++];

            else
                numbers[currentindex3++] = half2[currentindex2++];
        }
        // moving the numbers around until they are sorted
        while (currentindex1 < half1.length)
            numbers[currentindex3++] = half1[currentindex1++];

        while (currentindex2 < half2.length)
            numbers[currentindex3++] = half2[currentindex2++];
        return numbers;

    }


    // The Binary Search method
    public static int BinarySearch(int[] numbers, int start, int finish, int key) {
        
        
        if (finish >= start){
            int middle = start + (finish - start) / 2;
            if (numbers[middle] == key){
                return middle;
            }
            // Searching one half of the array for the value the user entered
            if (numbers[middle] > key){
                return BinarySearch(numbers, start, middle - 1, key);
            }
            // Searching the other half
            else{
                return BinarySearch(numbers, middle + 1, finish, key);
            }

        }
        // Value that determines whether it enters the first block or the second block of the if statement in the main method
        return -1;

    }
    
}
