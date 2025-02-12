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
        library.addBook(book);


        Loan activeLoan = new Loan();
        activeLoan.setUser(user);
        activeLoan.setBook(book);
        activeLoan.setLoanDate(LocalDateTime.now());
        activeLoan.setStatus(LoanStatus.ACTIVE);
        library.getBooks().put(book, 0);
    }



    @Test
    void testUserCannotBorrowSameBookTwice() {
        Loan loan = library.loanABook("U001", "12345");
        assertNull(loan, "El usuario no debería poder tomar prestado el mismo libro dos veces.");
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
    void testGetTitle() {
        this.book = new Book("123", "Book1", "Juan");
        assertEquals("Book1", book.getTitle(), "El método getTitle() no devuelve el título esperado.");
    }

    @Test
    void testGetAuthor() {
        this.book = new Book("132", "Book2", "Allan");
        assertEquals("Allan", book.getAuthor(), "El método getAuthor() no devuelve el autor esperado.");
    }


    @Test
    void testLoanBook_UserDoesNotExist() {
        Loan loan = library.loanABook("12345", "123");
        assertNull(loan, "El préstamo debería ser nulo porque el usuario no existe.");
    }

    @Test
    void testLoanBook_NoAvailableCopies() {
        // Agregar usuario
        this.user = new User();
        user.setId("12345");
        user.setName("Juan");
        library.getUsers().add(user);

        // Agregar libro con 0 copias
        this.book = new Book("123", "Book", "Allan");
        library.getBooks().put(book, 0); 

        Loan loan = library.loanABook("12345", "123");
        assertNull(loan, "El préstamo debería ser nulo porque no hay copias disponibles.");
    }




    @Test
    public void testBooksWithSameIsbnAreEqual() {
        Book book1 = new Book("123", "Book1", "Juan");
        Book book2 = new Book("123", "Book2", "Allan"); 

        assertTrue(book1.equals(book2)); 
    }

    @Test
    public void testBooksWithDifferentIsbnAreNotEqual() {
        Book book1 = new Book("123", "Book1", "Juan");
        Book book2 = new Book("132", "Book2", "Allan"); 

        assertFalse(book1.equals(book2)); 
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

    @Test
    void testUsersWithSameIdAreEqual() {
        User user1 = new User();
        user1.setId("123");
        user1.setName("Juan");

        User user2 = new User();
        user2.setId("123");
        user2.setName("Allan"); 

        assertTrue(user1.equals(user2)); 
    }

    @Test
    void testGetName() {
        this.user = new User();
        user.setName("Juan");
        assertEquals("Juan", user.getName(), "El método getName() no devuelve el nombre esperado.");
    }

    @Test
    void testHashCode() {
        User user1 = new User();
        user1.setId("123");

        User user2 = new User();
        user2.setId("123");

        assertEquals(user1.hashCode(), user2.hashCode(), "El hashCode() debería ser igual para usuarios con el mismo ID.");
    }

    @Test
    void testHashCodeDifferentIds() {
        User user1 = new User();
        user1.setId("123");

        User user2 = new User();
        user2.setId("456");

        assertNotEquals(user1.hashCode(), user2.hashCode(), "El hashCode() debería ser diferente para IDs distintos.");
    }
}
