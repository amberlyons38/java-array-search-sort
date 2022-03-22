import java.util.InputMismatchException;
import java.util.Scanner;
class ArrayOperations{
    //Array data class member
    private int[] a;
    
    //Constructor
    public ArrayOperations(int[] array){
        a = array;
    }
    //get and set methods for data members
    public int[] getA(){
	return a;
    }
    public void setA(int[] array){
	a = array;
    }
    
    //Print array method
    public void printA(){
        for(int i = 0; i < a.length; i++){
            if(i == a.length - 1){
                System.out.print(a[i]);
            }
            else{
                System.out.print(a[i] + ", ");
            }
        }
        System.out.println();
    }
    
    //Sort methods
    //Merge sort
    public void merge_sort(int[] array, int l, int r){
        //If right index less than or equal to the left index then stop sorting
        if(r <= l){
            return;
        }
        //Midpoint is equal to the left index plus the right index divided by 2
        int m = (l+r)/2;
        //Split it in half and merge sort the left part recursively
        merge_sort(array, l, m);
        //Spilt it in half and merge sort the right part recursively
        merge_sort(array, m+1, r);
        //Merge both subarrays back together
        merge(array, l, m, r);
    }
    //Merge helper for merge sort method
    private void merge(int[] array, int l, int m, int r){
        //Temp arrays
        int leftArray[] = new int[m - l + 1];
        int rightArray[] = new int[r - m];
        
        //Index of subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        //Put subarrays into temp arrays
        for (int i = 0; i < leftArray.length; i++){
            leftArray[i] = array[l + i];
        }
        for (int i = 0; i < rightArray.length; i++){
            rightArray[i] = array[m + i + 1];
        }

        //Put left array and right array back into array
        for(int i = l; i < r + 1; i++){
            //Copy minimum of the left and right array if there are still uncopied elements
            if(leftIndex < leftArray.length && rightIndex < rightArray.length){
                if(leftArray[leftIndex] < rightArray[rightIndex]){
                   array[i] = leftArray[leftIndex];
                   leftIndex++;
                }
                else{
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            //If all of rightArray has been copied then copy the rest of leftArray
            else if(leftIndex < leftArray.length){
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            //If all of leftArray has been copied then copy the rest of rightArray
            else if (rightIndex < rightArray.length){
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
    
    //Quick sort
    public void quick(int[] array, int l, int r){
        //If left index is less than right index continue
        if (l < r){
            //Find pivot point and declare it as a variable
            int i = pivot(array, l, r);
            //Quick sort the array from the left point to the pivot point recursively
            quick(array, l, i-1);
            //Quick sort the array from the pivot point + 1 to the right point recursively
            quick(array, i+1, r);
	}
    }
    //Pivot helper for quick sort method
    private int pivot(int[] array, int l, int r){
        //See if left point is still smaller than right point 
        boolean small = true;
        //While left point is still smaller than right point continue
        while(l < r){
            //If the element at the left point is greater than the element at the right point then swap the values
            if(array[l] > array[r]){
                int temp = array[r];
                array[r] = array[l];
                array[l] = temp;
                //Left point is no longer smaller than right point
                small = false;
            }
            //If left point is smaller than right point then decrease position of right point by 1
            if(small){
                r--;
            }
            //If left point is larger than right point then increase position of left point by 1
            else{
                l++;
            }
        }
        //Return the left point
        return l;
    }
    
    //Shell sort
    public void shell(int[] array, int n, int k){
        //While the segments are greater than 0
        while(k > 0){
            //For each segment do an insertion sort
            for(int i = 0; i < k; i++){
                insertion(array, n);
            }
            //Decrease number of segments by dividing it by 2
            k = k/2;
        }
    }
    //Insertion sort helper for shell sort method
    private void insertion(int[] array, int n){
        //If the size of the array segment is less than or equal to 1 then stop sorting
        if(n <= 1){
            return;
        }
        //Insertion sort the array minus 1 element recursively
        insertion(array, n - 1);
        
        //End index is equal to the element at the end of the array
        int end = array[n - 1];
        //I is equal to the index before the end index
        int i = n - 2;
        
        //While i is greater than or equal to 0 and the element at i is greater than end then the element keeps getting swapped
        while(i >= 0 && array[i] > end){
            array[i+1] = array[i];
            i--;
        }
        //The end element is swapped
        array[i+1] = end;
    }
    
    //Search methods
    //Binary search
    public int binary(int[] array, int n, int key){
        //Left point is the beginning of the array, right is the end of the array
        int left = 0, right = n-1;
        
        //While the left point is less than or equal to the right point
	while (left <= right){
            //Mid point is equal to the left point plus the right point divided by 2
            int m = (left+right)/2;
            //If the element at the mid point is equal to the search key then return the mid point
            if (array[m] == key){
                    return m;
            }
            //If the element at the midpoint is less than the search key then the left point is the mid point plus 1
            if (array[m] < key){
                left = m + 1;
            }
            //If the element at the midpoint is greater than the search key then the right point is the mid point minus 1
            else{
                right = m - 1;
            }
	}
        //If the key cannot be found then return -1
	return -1; 
    }
    //Interpolation search
    public int interpolation(int[] array, int key, int l, int r){
        //Set the index to be -1 automatically since it will be changed if the search key is found
        int m = -1;
        
        //If the left point is less than or equal to the right point then continue
        if(l <= r){
            //Set the index to be equal to the interpolation equation
            m = (int)(l + (((int)(r - l) / (array[r] - array[l])) * (key - array[l])));
            
            //If the element at the index is equal to the search key then return the index
            if (array[m] == key){
                return m;
            }
            else{
                //If the element at the index is less than the search key then the left point is equal to the index plus 1
                if(array[m] < key){
                    l = m + 1;
                }
                //If the element at the indez is greater than the search key then the right point is equal to the index minus 1
                else{
                    r = m - 1;
                }
            }
            //Interpolation search the array using the key, and the left and right points recursively
            return interpolation(array, key, l, r);
        }
        //Return the value of the index
        return m;
    }
}
public class ArraySearchSort {
    public static void main(String[] args) {
        Scanner userVariables = new Scanner(System.in);
        
        //Set userMenuInput as -1 until user makes a selection
        int userMenuInput = -1, arraySize = 0;
        //Set continueInput as true until user entered data is correct
        boolean continueInput = true;
        
        //Declare empty array with 0 size
        int[] userArray = new int[0];
        
        //Create new ArrayOperations object from blank array
        ArrayOperations op = new ArrayOperations(userArray);
        
        //Menu loop
        do{
            do{
                try{
                    //Prompt user with menu options
                    System.out.println("Choose from the options below (0 - 4): ");
                    System.out.println("1. Read: ");
                    System.out.println("2. Print: ");
                    System.out.println("3. Sort: ");
                    System.out.println("4. Search: ");
                    System.out.println("0. Quit");
                    
                    //Get menu input from user
                    userMenuInput = userVariables.nextInt();
                    
                    //See if menu input is valid
                    while(userMenuInput < 0 || userMenuInput > 4){
                        //If menu input is invalid keep prompting user until it is valid
                        System.out.println("Please only choose an option from 0 - 4: ");
                        userMenuInput = userVariables.nextInt();
                    }
                    //Input is now correct
                    continueInput = false;
                //If user enters a non-integer display mismatch exception   
                }catch(InputMismatchException ex){
                    //Prompt user about incorrect input
                    System.out.println("Try again. Incorrect input: an integer is required.");
                    userVariables.nextInt();
                }
            }while(continueInput);
            continueInput = false;
            
            //Menu options
            switch(userMenuInput){
                //Read option
                case 1:
                    try{
                        //Prompt user for array size
                        System.out.println("Please enter the array size as an integer: ");
                        //Get array size from user
                        arraySize = userVariables.nextInt();
                        //Array size needs to be greater than 0
                        while(arraySize <= 0){
                            //If menu input is invalid keep prompting user until it is valid
                            System.out.println("Please enter an integer greater than 0: ");
                            arraySize = userVariables.nextInt();
                        }
                        userArray = new int[arraySize];
                        op = new ArrayOperations(userArray);
                        //Get each element from user and read them into blank array
                        for(int i = 0; i < arraySize; i++){
                            System.out.println("Enter an integer for element " + i + ":");
                            int element = userVariables.nextInt();
                            userArray[i] = element;
                        }
                    }catch(InputMismatchException ex){
                        //Prompt user about incorrect input
                        System.out.println("Try again. Incorrect input: an integer is required.");
                        userVariables.nextInt();
                    }
                    //Spacing
                    System.out.println();
                    //Stop switch statement and go back to menu options
                    break;
                 
                //Print option
                case 2:
                    //Calls printA method
                    op.printA();
                    //Spacing
                    System.out.println();
                    //Stop switch statement and go back to menu options
                    break;
                
                //Sort option
                case 3:
                    //Set sortMenuInput as -1 until user makes a selection
                    int sortMenuInput = -1;
                    //Set sortcontinueInput as true until user entered data is correct
                    boolean sortContinueInput = true;
                    
                    //Sort menu loop
                    do{
                        do{
                            try{
                                //Prompt user with sort menu options
                                System.out.println("Choose from the options below (0 - 3): ");
                                System.out.println("1. Merge Sort: ");
                                System.out.println("2. Quick Sort: ");
                                System.out.println("3. Shell Sort: ");
                                System.out.println("0. Quit");
                                
                                //Get menu input from user
                                sortMenuInput = userVariables.nextInt();
                    
                                //See if menu input is valid
                                while(sortMenuInput < 0 || sortMenuInput > 3){
                                    //If menu input is invalid keep prompting user until it is valid
                                    System.out.println("Please only choose an option from 0 - 3: ");
                                    sortMenuInput = userVariables.nextInt();
                                }
                                //Input is now correct
                                sortContinueInput = false;
                            //If user enters a non-integer display mismatch exception
                            }catch(InputMismatchException ex){
                                //Prompt user about incorrect input
                                System.out.println("Try again. Incorrect input: an integer is required.");
                                userVariables.nextInt();
                            }
                        }while(sortContinueInput);
                        
                        //Sort menu options
                        switch(sortMenuInput){
                            //Merge sort option
                            case 1:
                                //Call merge sort method
                                op.merge_sort(userArray, 0, userArray.length-1);
                                //Spacing
                                op.printA();
                                //Stop switch statement and go back to menu options
                                break;
                            
                            //Quick sort option
                            case 2:
                                //Call quick sort method
                                op.quick(userArray, 0, userArray.length-1);
                                //Spacing
                                op.printA();
                                //Stop switch statement and go back to menu options
                                break;
                            
                            //Shell sort option
                            case 3:
                                //Call shell sort method
                                op.shell(userArray, userArray.length, userArray.length/2);
                                //Spacing
                                op.printA();
                                //Stop switch statement and go back to menu options
                                break;
                            
                            //Quit menu option
                            case 0:
                                //Spacing
                                System.out.println();
                                //Changes user input to 0 with no break so program stops when entered
                                sortMenuInput = 0;
                            
                            //Default option
                            default:
                                break;
                        }
                    //If user enters 0 it goes back to the main menu
                    }while(sortMenuInput != 0);
                    //Stop switch statement and go back to menu options
                    break;
                
                //Search option
                case 4:
                    //Set searchMenuInput to -1 until user makes a selection
                    int searchMenuInput = -1;
                    //Set searchContinueInput to true until user entered data is correct
                    boolean searchContinueInput = true;
                    //Set search to 0
                    int key = 0;
                    
                    do{
                        do{
                            try{
                                //Prompt user with sort menu options
                                System.out.println("Choose from the options below (0 - 2): ");
                                System.out.println("1. Binary Search: ");
                                System.out.println("2. Interpolation Search: ");
                                System.out.println("0. Quit");
                                
                                //Get menu input from user
                                searchMenuInput = userVariables.nextInt();
                    
                                //See if menu input is valid
                                while(searchMenuInput < 0 || searchMenuInput > 2){
                                    //If menu input is invalid keep prompting user until it is valid
                                    System.out.println("Please only choose an option from 0 - 3: ");
                                    searchMenuInput = userVariables.nextInt();
                                }
                                //Input is now correct
                                searchContinueInput = false;
                            //If user enters a non-integer display mismatch exception
                            }catch(InputMismatchException ex){
                                //Prompt user about incorrect input
                                System.out.println("Try again. Incorrect input: an integer is required.");
                                userVariables.nextInt();
                            }
                        }while(searchContinueInput);
                        
                        //Search menu options
                        switch(searchMenuInput){
                            //Binary search option
                            case 1:
                                try{
                                    //Get search key from user
                                    System.out.println("Enter a search key: ");
                                    //Set key to user entered integer
                                    key = userVariables.nextInt();
                                //If user enters a non-integer display mismatch exception
                                }catch(InputMismatchException ex){
                                    //Prompt user about incorrect input
                                    System.out.println("Try again. Incorrect input: an integer is required.");
                                    userVariables.nextInt();
                                }
                                //If search key is not found inform user
                                if(op.binary(userArray, userArray.length, key) == -1){
                                    System.out.println("Key not found in array.");
                                }
                                //If search key is found inform user and display position
                                else{
                                    System.out.println("Key found at position " + op.binary(userArray, userArray.length, key) + " in the array.");
                                }
                                //Spacing
                                System.out.println();
                                //Stop switch statement and go back to menu options
                                break;
                            
                            //Interpolation search option
                            case 2:
                                try{
                                    //Get search key from user
                                    System.out.println("Enter a search key: ");
                                    //Set key to user entered integer
                                    key = userVariables.nextInt();
                                //If user enters non-integer display mismatch exception
                                }catch(InputMismatchException ex){
                                    //Prompt user about incorrect input
                                    System.out.println("Try again. Incorrect input: an integer is required.");
                                    userVariables.nextInt();
                                }
                                //If search key is not found inform user
                                if(op.interpolation(userArray, key, 0, userArray.length-1) == -1){
                                    System.out.println("Key not found in array.");
                                }
                                //If search key is found inform user and display position
                                else{
                                    System.out.println("Key found at position " + op.interpolation(userArray, key, 0, userArray.length-1) + " in the array.");
                                }
                                //Spacing
                                System.out.println();
                                //Stop switch statement and go back to menu options
                                break;
                                
                            //Quit menu option    
                            case 0:
                                //Spacing
                                System.out.println();
                                //Changes user input to 0 with no break so program stops when entered
                                searchMenuInput = 0;
                            //Default option
                            default:
                                break;
                        }
                    //If user enters 0 it goes back to the main menu
                    }while(searchMenuInput != 0);
                    //Stop switch statement and go back to menu options
                    break;
                
                //Quit program option
                case 0:
                    //Changes user input to 0 with no break so program stops when entered
                    userMenuInput = 0;
                
                //Default option
                default:
                    break;
            }
        //If user enters 0 the program stops
        }while(userMenuInput != 0); 
    }
}
