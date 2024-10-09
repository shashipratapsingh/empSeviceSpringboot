package EmployeeService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Book_records")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String BookName;
    private String description;
}
