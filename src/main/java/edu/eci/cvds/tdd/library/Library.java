package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (book == null) return false;

        int cantidad = books.getOrDefault(book, 0);
        books.put(book, cantidad + 1);

        System.out.println("Libro agregado: " + book.getIsbn() + " - Nueva cantidad: " + books.get(book));

        return true;
    }



    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        // Buscar el usuario
        User user = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println("Error: El usuario no existe.");
            return null;
        }

        // Buscar el libro en el Map usando ISBN
        Book book = books.keySet().stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Error: El libro con ISBN " + isbn + " no existe.");
            return null;
        }

        Integer availableCopies = books.get(book);
        if (availableCopies == null || availableCopies == 0) {
            System.out.println("Error: No hay copias disponibles del libro.");
            return null;
        }

        // Verificar si el usuario ya tiene un préstamo activo de este libro
        boolean hasActiveLoan = loans.stream()
                .anyMatch(loan -> loan.getUser().equals(user) &&
                        loan.getBook().equals(book) &&
                        loan.getStatus() == LoanStatus.ACTIVE);

        if (hasActiveLoan) {
            System.out.println("Error: El usuario ya tiene un préstamo activo de este libro.");
            return null;
        }

        // Crear y registrar el préstamo
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);

        loans.add(loan);
        books.put(book, availableCopies - 1); // Reducir copias disponibles

        System.out.println("Préstamo realizado con éxito.");
        return loan;
    }




    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null) {
            System.out.println("Error: El préstamo recibido es null.");
            return null;
        }

        System.out.println("Intentando devolver el préstamo: " + loan);

        Loan existingLoan = loans.stream()
                .filter(l -> l.getBook().equals(loan.getBook()) && l.getUser().equals(loan.getUser()) && l.getStatus() == LoanStatus.ACTIVE)
                .findFirst()
                .orElse(null);

        if (existingLoan == null) {
            System.out.println("Error: No se encontró un préstamo activo para este libro y usuario.");
            return null;
        }

        existingLoan.setStatus(LoanStatus.RETURNED);
        existingLoan.setReturnDate(LocalDateTime.now());
        books.put(existingLoan.getBook(), books.get(existingLoan.getBook()) + 1);

        System.out.println("Préstamo devuelto correctamente.");

        return existingLoan;
    }


    public Map<Book, Integer> getBooks() {
        return books;
    }


    public List<User> getUsers() {
        return users;
    }


    public boolean addUser(User user) {
        return users.add(user);
    }

}
