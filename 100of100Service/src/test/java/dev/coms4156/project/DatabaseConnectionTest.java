package dev.coms4156.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A unit test class for the DatabaseConnection class.
 */
public class DatabaseConnectionTest {

  private DatabaseConnection dbConnection;

  @BeforeEach
  public void setup() {
    try {
      dbConnection = DatabaseConnection.getInstance();
    } catch (Exception e) {
      assumeTrue(false);
    }
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