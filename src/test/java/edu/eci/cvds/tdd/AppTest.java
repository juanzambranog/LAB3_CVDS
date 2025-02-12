package edu.eci.cvds.tdd;

import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private Library library;
    private Book book;
    private User user;

    @BeforeEach

    void setUp() {
        library = new Library();
        book = new Book("12345", "JUnit Testing", "Autor Prueba"); // Asegúrate de pasar los 3 parámetros requeridos
        user = new User();
        user.setId("U001");
        user.setName("Test User");
        library.addUser(user);
    }


    @Test
    void testAddBook() {
        assertTrue(library.addBook(book), "El libro debería agregarse correctamente.");
    }

    @Test
    void testLoanBookSuccessfully() {
        // Crear biblioteca
        Library library = new Library();

        // Crear usuario
        User user = new User();
        user.setId("U001");
        user.setName("Juan Pérez");

        // Crear libro con ISBN correcto
        Book book = new Book("12345", "El Principito", "Antoine de Saint-Exupéry");

        // Agregar usuario y libro a la biblioteca
        assertTrue(library.addUser(user), "El usuario no se agregó correctamente.");
        assertTrue(library.addBook(book), "El libro no se agregó correctamente.");

        // Intentar hacer un préstamo
        Loan loan = library.loanABook("U001", "12345");

        assertNotNull(loan, "El préstamo no debería ser nulo.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El préstamo debe estar activo.");

        // Verificar que la cantidad de libros se redujo correctamente
        int availableCopies = library.getBooks().get(book);
        assertEquals(0, availableCopies, "La cantidad de libros disponibles debería reducirse a 0.");
    }




    @Test
    void testLoanBookFailsWhenBookNotAvailable() {
        Loan loan = library.loanABook("U001", "99999");
        assertNull(loan, "El préstamo debería fallar si el libro no está disponible.");
    }

    @Test
    void testReturnLoanSuccessfully() {
        // Crear una instancia de Library
        Library library = new Library();

        // Crear usuario
        User user = new User();
        user.setId("U001");
        user.setName("Juan Pérez");

        // Crear libro con ISBN correcto
        Book book = new Book("12345", "El Principito", "Antoine de Saint-Exupéry");

        // Agregar usuario y libro
        assertTrue(library.addUser(user), "El usuario no se agregó correctamente.");
        assertTrue(library.addBook(book), "El libro no se agregó correctamente.");

        // Crear préstamo
        Loan loan = library.loanABook("U001", "12345");
        assertNotNull(loan, "El préstamo no debería ser nulo.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El préstamo debe estar activo.");

        // Devolver préstamo
        Loan returnedLoan = library.returnLoan(loan);
        assertNotNull(returnedLoan, "El préstamo debería poder devolverse.");
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus(), "El estado del préstamo debe ser RETURNED.");

        // Verificar que la cantidad de libros se actualizó
        int availableCopies = library.getBooks().get(book);
        assertEquals(1, availableCopies, "La cantidad de libros disponibles debería incrementarse.");
    }







    @Test
    void testAddUser() {
        User newUser = new User();
        newUser.setId("U002");
        newUser.setName("New User");
        assertTrue(library.addUser(newUser), "El usuario debería agregarse correctamente.");
    }
}
