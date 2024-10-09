package EmployeeService.controller;

import EmployeeService.model.Book;
import EmployeeService.model.Employee;
import EmployeeService.model.Project;
import EmployeeService.service.BookService;
import EmployeeService.service.EmployeeService;
import EmployeeService.service.ProjectService;
import EmployeeService.utility.ApiResponse;
import EmployeeService.utility.BookNotFoundException;
import EmployeeService.utility.GlobalExceptionHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity saveEmp(@RequestBody Employee emp){
        try {
            Employee savedEmployee = employeeService.saveEmp(emp);
            ApiResponse response = new ApiResponse("Employee saved successfully", savedEmployee, HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save employee: " + e.getMessage());
        }
    }

    @GetMapping("/getEmp/{id}")
    public ResponseEntity getEmployeeById(@PathVariable int id){
        int p=this.employeeService.getEmp(id).getId();
       if(p==-1){
           ApiResponse response = new ApiResponse("Employee not found", null, HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<>(this.employeeService.getEmp(id),HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

//book service for JUnit
    @PostMapping("/books")
    public ResponseEntity<Book> createBooks(@RequestBody @Valid Book book) {
        Book books = this.bookService.addBook(book);
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookById(int id) {
        try{
            Book book=this.bookService.getBookById(id).get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (BookNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping("update")
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
        try {
            // Call the service to update the book
            Book savedBook = this.bookService.updateBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Return a 500 status for any other unexpected errors
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id); // Call the service to delete the book
            return new ResponseEntity<>("Book with ID " + id + " has been deleted successfully.", HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>("Book with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while trying to delete the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








}
