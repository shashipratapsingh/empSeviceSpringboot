package EmployeeService.service;

import EmployeeService.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    public Book addBook(Book book);

    public List<Book> getAllBooks();

    public Optional<Book> getBookById(int id);
    public Book updateBook(Book book);
    public String deleteBook(int id);
}
