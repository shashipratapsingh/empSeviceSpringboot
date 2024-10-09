package EmployeeService.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String projectName;
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    // Ensure tasks have project set before saving
    @PrePersist
    @PreUpdate
    private void assignProjectToTasks() {
        if (tasks != null) {
            for (Task task : tasks) {
                task.setProject(this);  // Set the project for each task
            }
        }
    }
}
