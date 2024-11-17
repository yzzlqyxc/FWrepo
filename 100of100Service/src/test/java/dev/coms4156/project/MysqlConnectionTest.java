package dev.coms4156.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A unit test class for the real MysqlConnection class.
 */
public class MysqlConnectionTest {

  private DatabaseConnection realConnection;

  /**
   * Sets up the test environment by initializing the (real) DatabaseConnection instance.
   */
  @BeforeEach
  public void setup() {
    try {
      realConnection = MysqlConnection.getInstance();
    } catch (Exception e) {
      assumeTrue(false);
    }
  }

  @Test
  public void testGetOrganization() {
    Organization org = realConnection.getOrganization(1);
    assertNotNull(org, "Organization should not be null");
    System.out.println("Successfully retrieved organization: " + org.getName());
  }

  @Test
  public void testGetEmployees() {
    List<Employee> employees = realConnection.getEmployees(1);
    assertNotNull(employees, "Employees list should not be null");
    assertFalse(employees.isEmpty(), "Employees list should not be empty");
    System.out.println("Retrieved " + employees.size() + " employees");
  }

  @Test
  public void testGetDepartments() {
    List<Department> departments = realConnection.getDepartments(1);
    assertNotNull(departments, "Departments list should not be null");
    assertFalse(departments.isEmpty(), "Departments list should not be empty");
    System.out.println("Retrieved " + departments.size() + " departments");
  }
}