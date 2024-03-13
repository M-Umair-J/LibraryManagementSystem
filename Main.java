package LibrarySystem;

import java.util.Scanner;

public class Main {
    static public void menu() {
        Scanner sc = new Scanner(System.in);
        Library l = new Library();
        String answer = null;
        boolean flag2 = false;
        int input;
        
        while (true) {
            try{
            System.out.println("\t\t\t\t\t\t\tLibrary Management System");
            System.out.println("Please enter the operation that you want to perform!");
            System.out.println("1. Add new book!");
            System.out.println("2. Add new user/member!");
            System.out.println("3. Display book!");
            System.out.println("4. Borrow book!");
            System.out.println("5. Return book!");
            System.out.println("6. Display IDs of books that are available!");
            System.out.println("7. Display the IDs of users that are added to the storage");
            System.out.println("8. Search Book (by user id)");
            System.out.println("9. Exit");
            input = sc.nextInt();
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
            }else if(input == 7){
                Library.displayOnlyIdsOfUsers();
            }else if(input == 8){
                l.bookSearch();
            } else if (input == 9) {
                break;
            } else {
                System.out.println("Invalid operation!");
            }
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
            if (flag2 == true) {
                continue;
            } else {
                break;
            }
        }catch(Exception e){
            System.out.println("Invalid Input! Please enter again!");
            sc.next();
            continue;
        }
    }
    sc.close();
}

    public static void main(String[] args) {
        menu();
    }
}
