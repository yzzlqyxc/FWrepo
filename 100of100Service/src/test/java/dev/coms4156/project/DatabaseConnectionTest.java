package dev.coms4156.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DatabaseConnectionTest {

  private DatabaseConnection dbConnection;

  @BeforeEach
  public void setup() {
    dbConnection = DatabaseConnection.getInstance();
  }

  @Test
  public void testGetOrganization() {
    Organization org = dbConnection.getOrganization(1);
    assertNotNull(org, "Organization should not be null");
    System.out.println("Successfully retrieved organization: " + org.getName());
  }

  @Test
  public void testGetEmployees() {
    List<Employee> employees = dbConnection.getEmployees(1);
    assertNotNull(employees, "Employees list should not be null");
    assertFalse(employees.isEmpty(), "Employees list should not be empty");
    System.out.println("Retrieved " + employees.size() + " employees");
  }

  @Test
  public void testGetDepartments() {
    List<Department> departments = dbConnection.getDepartments(1);
    assertNotNull(departments, "Departments list should not be null");
    assertFalse(departments.isEmpty(), "Departments list should not be empty");
    System.out.println("Retrieved " + departments.size() + " departments");
  }
}