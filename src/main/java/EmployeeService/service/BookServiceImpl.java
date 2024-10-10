package EmployeeService.service;

import EmployeeService.model.Book;
import EmployeeService.repository.BookRepository;
import EmployeeService.utility.BookNotFoundException;
import jakarta.persistence.Id;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements BookService {
         @Autowired
         private BookRepository bookRepository;

   // private static final Logger log = (Logger) LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book addBook(Book book) {
        // Validate input data
       // validateBook(book);

        // Check for existing book (if necessary)


        try {
            // Save the book to the repository
            Book savedBook = bookRepository.save(book);
            // Log successful save
           // log.info("Book with ID " + savedBook.getId() + " has been successfully saved.");
            return savedBook;
        } catch (Exception e) {
            // Handle any unexpected database issues
           // log.error("Error while saving book: " + e.getMessage());
            throw new BookNotFoundException("An error occurred while saving the book. Please try again.");
        }
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList= bookRepository.findAll();
        if (bookList.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        return bookList;
    }

    @Override
    public Optional<Book> getBookByBookId(long bookId) {
        Optional<Book> book= bookRepository.findById((int) bookId);
        if (book.isEmpty()){
            throw new BookNotFoundException("This is not found"+ bookId+"in the database");
        }
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(Math.toIntExact(book.getBookId()));
        if (existingBook.isEmpty()) {
            throw new BookNotFoundException("Book not found with ID: " + book.getBookId());
        }
        return bookRepository.save(book);
    }

    @Override
    public String deleteBook(int id) {
        Optional<Book> book= bookRepository.findById(id);
        if (book.isEmpty()){
            throw new BookNotFoundException("This is not found"+ id+"in the database");
        }
        bookRepository.deleteById(id);
        return "Book deleted" + id + "succesfully";
    }


}
