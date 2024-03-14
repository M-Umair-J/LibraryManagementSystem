package LibrarySystem;

import java.util.Scanner;

// Main.java file which contains the Main class and main function
public class Main {
    
    // Method to display the menu and handle user input
    static public void menu() {
        Scanner sc = new Scanner(System.in);
        Library l = new Library();
        String answer = null;
        boolean flag2 = false;
        int input;
        
        // Displaying the menu in a loop
        while (true) {
            try {
                // Displaying the menu options
                System.out.println("_____________________________Library Management System____________________________");
                System.out.println("Please enter the operation that you want to perform!");
                System.out.println("1. Add new book!");
                System.out.println("2. Add new user/member!");
                System.out.println("3. Display book!");
                System.out.println("4. Borrow book!");
                System.out.println("5. Return book!");
                System.out.println("6. Display IDs of books that are available in the current file!");
                System.out.println("7. Display the IDs of users that have been added to the storage!");
                System.out.println("8. Search Book (by user id)");
                System.out.println("9. Exit");
                
                // Getting user input
                input = sc.nextInt();
                
                // Performing actions based on user input
                if (input == 1) {
                    l.addNewBook();
                    Library.fileReadBook();
                } else if (input == 2) {
                    l.addNewUser();
                    Library.fileReadUser();
                } else if (input == 3) {
                    System.out.println("Please enter the id of the book!");
                    input = sc.nextInt();
                    Library.fileReadBook();
                    Library.displayBooks(input);
                } else if (input == 4) {
                    System.out.println("Please enter the user id:");
                    int uId = sc.nextInt();
                    System.out.println("Please enter the book Id that the user wants to borrow: ");
                    int bId = sc.nextInt();
                    l.borrowBook(uId, bId);
                } else if (input == 5) {
                    System.out.println("Please enter the user id:");
                    int uId = sc.nextInt();
                    System.out.println("Please enter the book Id that the user wants to return: ");
                    int bId = sc.nextInt();
                    l.bookReturn(uId, bId);
                } else if (input == 6) {
                    Library.displayOnlyIdsOfBooks();
                } else if (input == 7) {
                    Library.displayOnlyIdsOfUsers();
                } else if (input == 8) {
                    l.bookSearch();
                } else if (input == 9) {
                    break;
                } else {
                    System.out.println("Invalid operation!");
                }
                
                // Asking the user if they want to continue
                while (true) {
                    System.out.println("\n\nDo you want to continue?");
                    answer = sc.next();
                    if (answer.equals("Y") || answer.equals("y")) {
                        flag2 = true;
                        break;
                    } else if (answer.equals("N") || answer.equals("n")) {
                        flag2 = false;
                        break;
                    } else {
                        System.out.println("Invalid input!");
                        continue;
                    }
                }
                
                // Checking if the user wants to continue
                if (flag2 == true) {
                    continue;
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input! Please enter again!");
                sc.next();
                continue;
            }
        }
        sc.close();
    }

    // Main method to start the program
    public static void main(String[] args) {
        menu(); // Calling the menu method to display the menu
    }
}
