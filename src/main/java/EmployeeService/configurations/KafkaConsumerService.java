package EmployeeService.configurations;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "employee-events", groupId = "employee-service-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        // Process the message as needed
    }
}
