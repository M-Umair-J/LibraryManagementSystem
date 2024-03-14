package LibrarySystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.BufferedWriter;
import java.util.Random;

public class Library {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    //Creating array lists to store the data when retrieved from the file
    static ArrayList<Book> bookCollection = new ArrayList<>();
    static ArrayList<User> userCollection = new ArrayList<>();

    void addNewBook() {//Method to add new book
        Book newBook = new Book();//Creating the book object to get the book data
        while(true){// looping along with the try catch to ensure that the invalid inputs are handled properly
            try{
        System.out.println("Please enter the id of the book!");
        int randomnum = random.nextInt(sc.nextInt());  // generating a random number for the book id against the seed entered by the user
        newBook.bookID = randomnum;
        System.out.println("Please enter the title of the book!");
        newBook.title = sc.next();
        System.out.println("Please enter the author name!");
        newBook.author = sc.next();
        System.out.println("Please enter the genre of the book!");
        newBook.genre = sc.next();
        newBook.availability_status = true;
        break;
        }catch(Exception e){
            System.out.println("Invalid Input! Try again");
            sc.next();//clearing buffer
            continue;
        }
    }
        bookCollection.add(newBook);//adding the book to the collection of books
        bookFileWrite("C:\\BookStorage", newBook);//writing the book with the attributes entered by user on to the file
    }

    void addNewUser() {//Method to add new user
        User newUser = new User();//Creating new user object to take the inputs from the user and adding them to the user storage
        int randomnum;
        while(true){//loopint through the inputs along with the try catch to ensure that the inputs are handled properly
            try{
        System.out.println("Please enter the id of the user!");
        randomnum = random.nextInt(sc.nextInt());//generating a random number for the user id against the seed entered by the user
        newUser.userID = randomnum;
        System.out.println("Please enter the name of the user!");
        newUser.name = sc.next();
        System.out.println("Please enter the contact information of the user!");
        newUser.contact_information = sc.next();
        newUser.borrowed_booksID = 0;
        break;
        }catch(Exception e){
            System.out.println("Invalid input! Try again");
            sc.next();
            continue;
        }
    }
        userCollection.add(newUser);//adding the user to the user collection (array list)
        userFileWrite("C:\\UserStorage", newUser);//adding the user to the file storage as well
    }

    static void displayBooks(final int a) {
        boolean flag = false;//mehtod to display the book based on the availability staus and the book id enterd by the user and then searching the collection (array lists) to display the book
        for (Book book : bookCollection) {
            if (book.bookID == a) {
                if (book.availability_status == true) {
                    System.out.println("Book Id\t\tBook Title\tBook Author\tBook Genre\tBook Availability Status");
                    System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%b", book.bookID, book.title, book.author, book.genre,
                            book.availability_status);
                    flag = true;
                    break;
                } else {
                    System.out.println("Sorry the book has already been borrowed!");
                    flag = true;
                }
            } else {
                continue;
            }
        }
        if (!flag) {
            System.out.println("Book not found!");
        }
    }


    void borrowBook(int uID,int bID){//method to borrow the book from the file by settin the availablility status false and assigning the book id to the borrowed user id
        //here we have used the RandomAccessFile class to read the file
        try(RandomAccessFile reader = new RandomAccessFile("C:\\BookStorage","r"))
        {
            String line;
            int i = 0;
            while((line = reader.readLine())!=null){
                if(i%6==0){// to ensure that the correct lines are being read from the file
                    if(bID == Integer.parseInt(line)){
                        try{
                            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\BookStorage", "rw");
                            randomAccessFile.seek(0);
                            for(int j = 0;j<=i+3;j++){
                                randomAccessFile.readLine();
                            }
                            randomAccessFile.writeBytes("0");
                            randomAccessFile.seek(0);
                            randomAccessFile.close();
                        }
                        catch(Exception e){
                            System.out.println("There might be a problem while borrowing the book! Check the current books available using main menu!");                            
                        }
                    }
                }
                i++;
            }
        
        }catch(Exception e){
            System.out.println(e);
        }
        try(RandomAccessFile reader = new RandomAccessFile("C:\\UserStorage","r")){
            String line;
            int i = 0;
            while((line = reader.readLine())!=null){
                if(i%5==0){
                    if(uID == Integer.parseInt(line)){
                        try{
                            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\UserStorage", "rw");
                            randomAccessFile.seek(0);
                            for(int j = 0;j<=2+i;j++){
                                randomAccessFile.readLine();
                            }
                            randomAccessFile.writeBytes(Integer.toString(bID));
                            randomAccessFile.seek(0);
                            randomAccessFile.close();

                        }
                        catch(Exception e){
                            System.out.println("There might be a problem while borrowing the book! Check the current books available using main menu!");
                        }
                    }
                }
                i++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
        
    void bookReturn(int uId,int bId){//method to return the book by setting the user borrowed id back to 0 and by setting the availablity status of the book to true
        try{
           RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\UserStorage","rw");
            String line;
            int i=0;
            while ((line = randomAccessFile.readLine())!=null) {
                if(i%5==0){//again using an iterator read and write to the correct line
                    if(uId == Integer.parseInt(line)){
                        randomAccessFile.seek(0);
                        for(int j=0;j<=2+i;j++){
                            randomAccessFile.readLine();
                        }
                        // int length = randomAccessFile.readLine().length();
                        // System.out.println(length);
                        // randomAccessFile.writeBytes(new String(new char[length]).replace('\0', ' '));
                        randomAccessFile.writeBytes("0");
                        randomAccessFile.seek(0);
                        randomAccessFile.close();
                    }
                }
                i++;
            }
        }catch(Exception e){
            System.out.println("There might be a problem while returing the book! Check the current books available using main menu!");
        }
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\BookStorage","rw");
            String line;
            int i=0;
            while((line = randomAccessFile.readLine())!=null){
                if(i%6==0){
                    if(bId == Integer.parseInt(line)){
                        randomAccessFile.seek(0);
                        for(int j = 0; j <= i+3; j++){
                            randomAccessFile.readLine();
                        }
                        randomAccessFile.writeBytes("1");
                        randomAccessFile.seek(0);
                        randomAccessFile.close();
                    }
                }
                i++;
            }
        }catch(Exception e){
            System.out.println("There might be a problem while returing the book! Check the current books available using main menu!");
        }
    }

    void bookSearch() {
        
        System.out.println("Please enter the User Id!");
        int a = sc.nextInt();
        //getting user id and then searching the user collection array list to display the book borrowed by the user
        userCollection.clear();
        fileReadUser();
        for (User user : userCollection) {
            if (user.userID == a) {
                if (user.borrowed_booksID != 0) {
                    for (Book book : bookCollection) {
                        if (book.bookID == user.borrowed_booksID) {
                            System.out.println("The book borrowed by this user is: ");
                            System.out.println();
                            System.out.println(
                                    "Book Id\t\tBook Title\tBook Author\tBook Genre\tBook Availability Status");
                            System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%b", book.bookID, book.title, book.author,
                                    book.genre, book.availability_status);
                                    break;
                        }
                    }
                    break;
                } else {
                    System.out.println("No book has been borrowed by this user!");
                }
            } else {
                System.out.println("Such a user does not exist in our database!");
            }
        }
    }

    static void fileCreation(String filename) {
        try {//file creationg method to create a new file
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("New file created!");
            } else {
                System.out.println("File already exists!");
            }

        } catch (Exception e) {
            System.out.println("Could not create the file");
        }
    }

    static void fileReadBook() {//method to read the file using the RandomAccessFile
        String a = null, y = null;
        String b = null, c = null, d = null;
        boolean x = false;

        try (RandomAccessFile reader = new RandomAccessFile("C:\\BookStorage","r")) {
            String line;
            int i = 1, n = 0;
            while ((line = reader.readLine()) != null) {
                if (i % 6 == 0) {
                    Book newBook = new Book();
                    newBook.bookID = Integer.valueOf(a);
                    newBook.title = b;
                    newBook.author = c;
                    newBook.genre = d;
                    if (y.equals("0")) {
                        x = false;
                    } else if (y.equals("1")) {
                        x = true;
                    }
                    newBook.availability_status = x;
                    bookCollection.add(newBook);
                    n += 6;
                } else if (i == 1 + n) {
                    a = line;
                } else if (i == 2 + n) {
                    b = line;
                } else if (i == 3 + n) {
                    c = line;
                } else if (i == 4 + n) {
                    d = line;
                } else if (i == 5 + n) {
                    y = line;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void bookFileWrite(String filename, Book object) {
        Library.fileCreation(filename);
        String a = null, y = null;
        String b = null, c = null, d = null;
        boolean x = false;
        if (object.getClass() == Book.class) {
            a = Integer.toString(object.bookID);
            b = object.title;
            c = object.author;
            d = object.genre;
            x = object.availability_status;
            if (x == true) {
                y = "1";
            } else {
                y = "0";
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(a);
            writer.newLine();
            writer.write(b);
            writer.newLine();
            writer.write(c);
            writer.newLine();
            writer.write(d);
            writer.newLine();
            writer.write(y);
            writer.newLine();
            writer.write("-");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Could not write to the file");
        }
    }

    static void displayAllBooks() {
        for (Book bookthis : bookCollection) {
            System.out.println(bookthis.bookID);
            System.out.println(bookthis.author);
            System.out.println(bookthis.title);
            System.out.println(bookthis.genre);
            System.out.println(bookthis.availability_status);
        }
    }

static void userFileWrite(String filename, User object) {
    Library.fileCreation(filename);
    String a = null, b = null, c = null, d = null;
    if (object.getClass() == User.class) {
        a = Integer.toString(object.userID);
        b = object.name;
        c = object.contact_information;
        d = Integer.toString(object.borrowed_booksID);
    }
    try {
        FileWriter fileWriter = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(a);
        writer.newLine();
        writer.write(b);
        writer.newLine();
        writer.write(c);
        writer.newLine();
        writer.write(d);
        writer.newLine();
        writer.write("-");
        writer.newLine();
        writer.close();
    } catch (Exception e) {
        System.out.println("Could not write to the file");
        }
    }



    
    static void fileReadUser() {
        String a = null, b = null, c = null, d = null;
        try (RandomAccessFile reader = new RandomAccessFile("C:\\UserStorage","r")) {
            String line;
            int i = 1, n = 0;
            while ((line = reader.readLine()) != null) {
                if (i % 5 == 0) {
                    User newUser = new User();
                    newUser.userID = Integer.valueOf(a);
                    newUser.name = b;
                    newUser.contact_information = c;
                    newUser.borrowed_booksID = Integer.valueOf(d);
                    userCollection.add(newUser);
                    n += 5;
                } else if (i == 1 + n) {
                    a = line;
                } else if (i == 2 + n) {
                    b = line;
                } else if (i == 3 + n) {
                    c = line;
                } else if (i == 4 + n) {
                    d = line;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
}
    static void displayOnlyIdsOfBooks(){
        bookCollection.clear();
        Library.fileReadBook();
        System.out.println("The books that are available are: ");
        for (Book book : bookCollection) {
            if(book.availability_status!=false){
            System.out.println(book.bookID);
            }
        }
    }
    static void displayOnlyIdsOfUsers(){
        userCollection.clear();
        Library.fileReadUser();
        System.out.println("The users that are registered are: ");
        for (User user : userCollection) {
            System.out.println(user.userID);
        }
    }
}