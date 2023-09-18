import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View Book by ID");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                    case 1:
                            System.out.print("Enter book name: ");
                            String name = scanner.next();
                            System.out.print("Enter ISBN: ");
                            String isbn = scanner.next();
                            Book newBook = new Book(-1, name, isbn); // -1 placeholder ID
                            bookManager.addBook(newBook);
                            System.out.println("Book added with ID: " + newBook.getBookID());
                            break;
                    case 2:
                            System.out.print("Enter book ID: ");
                            int bookID = scanner.nextInt();
                            Book retrievedBook = bookManager.getBookById(bookID);
                            if (retrievedBook != null) {
                                System.out.println("Book ID: " + retrievedBook.getBookID());
                                System.out.println("Book Name: " + retrievedBook.getBookName());
                                System.out.println("ISBN: " + retrievedBook.getISBN());
                            } else {
                                System.out.println("Book not found.");
                            }
                            break;
                    case 3:
                            System.out.print("Enter book ID to update: ");
                            int updateBookID = scanner.nextInt();
                            Book bookToUpdate = bookManager.getBookById(updateBookID);
                            if (bookToUpdate != null) {
                                System.out.print("Enter new book name: ");
                                String updatedName = scanner.next();
                                System.out.print("Enter new ISBN: ");
                                String updatedISBN = scanner.next();
                                bookToUpdate.setBookName(updatedName);
                                bookToUpdate.setISBN(updatedISBN);
                                bookManager.updateBook(bookToUpdate);
                                System.out.println("Book updated successfully.");
                            } else {
                                System.out.println("Book not found.");
                            }
                            break;
                    case 4:
                            System.out.print("Enter book ID to delete: ");
                            int deleteBookID = scanner.nextInt();
                            bookManager.deleteBook(deleteBookID);
                            System.out.println("Book deleted successfully.");
                            break;
                    case 5:
                            System.out.println("Exiting the program.");
                            scanner.close();
                            System.exit(0);
                    default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
    }
}
