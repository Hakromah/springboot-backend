package hsk.javaspring.springboot;

import hsk.javaspring.springboot.model.Employee;
import hsk.javaspring.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApplication.class, args);
        Employee employee = new Employee();
        employee.onCreate();
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
//        Employee employee = new Employee();
//        employee.setFirstName("Hadja");
//        employee.setLastName("Conneh");
//        employee.setEmailId("hconneh@gmail.com");
//        employeeRepository.save(employee);
    }
}
