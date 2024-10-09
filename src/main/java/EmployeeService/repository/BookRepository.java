package EmployeeService.repository;

import EmployeeService.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
