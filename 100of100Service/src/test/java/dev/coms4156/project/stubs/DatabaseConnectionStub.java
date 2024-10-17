package dev.coms4156.project.stubs;

import dev.coms4156.project.DatabaseConnection;
import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.Organization;
import java.util.*;

/**
 * A test-stub of the database connection class, using in-memory data only.
 */
public class DatabaseConnectionStub extends DatabaseConnection {
  private static volatile DatabaseConnection instance;

  private final Map<Integer, List<Employee>> testEmployees = new HashMap<>();
  private final Map<Integer, List<Department>> testDepartments = new HashMap<>();
  private final Map<Integer, Organization> testOrganizations = new HashMap<>();

  private DatabaseConnectionStub() {
    initializeTestData(); // test
  }

  public Employee getEmployee(int organizationId, int externalEmployeeId) {
    List<Employee> employees = testEmployees.get(organizationId);
    if (employees != null) {
      for (Employee employee : employees) {
        if (employee.getId() == externalEmployeeId) {
          return employee;
        }
      }
    }
    return null;
  }

  public Department getDepartment(int organizationId, int externalDepartmentId) {
    List<Department> departments = testDepartments.get(organizationId);
    if (departments != null) {
      for (Department department : departments) {
        if (department.getId() == externalDepartmentId) {
          return department;
        }
      }
    }
    return null;
  }

  public List<Employee> getEmployees(int organizationId) {
    return testEmployees.getOrDefault(organizationId, new ArrayList<>()); // test
  }

  public List<Department> getDepartments(int organizationId) {
    return testDepartments.getOrDefault(organizationId, new ArrayList<>());
  }

  public Organization getOrganization(int organizationId) {
    return testOrganizations.getOrDefault(organizationId, new Organization(null, organizationId, "Unknown"));
  }

  private void initializeTestData() {
    // create test employees and departments
    // For clientId = 1
    int clientId = 1;
    Department engineering = new Department(null, 1, "Engineering", new ArrayList<>());
    Employee alice = new Employee(null, 1, "Alice", new Date());
    Employee bob = new Employee(null, 2, "Bob", new Date());
    engineering.addEmployee(alice);
    engineering.addEmployee(bob);

    Department hr = new Department(null, 2, "HR", new ArrayList<>());
    Employee charlie = new Employee(null, 3, "Charlie", new Date());
    hr.addEmployee(charlie);

    // Add departments to the organization
    Organization organization = new Organization(null, clientId, "Test Organization");
    organization.addDepartment(engineering);
    organization.addDepartment(hr);

    // Store employees, departments, and organizations in the fake database
    testEmployees.put(clientId, Arrays.asList(alice, bob, charlie));
    testDepartments.put(clientId, Arrays.asList(engineering, hr));
    testOrganizations.put(clientId, organization);
  }

  @Override
  public String toString() {
    return "Test-stub of DatabaseConnection, using in-memory data only.";
  }

  /**
   * Returns the unique instance of the database connection.
   * Designed with "double-checked locking" mechanism to ensure thread safety.
   *
   * @return the database connection instance
   */
  public static DatabaseConnection getInstance() {
    if (instance == null) {
      synchronized (DatabaseConnectionStub.class) {
        if (instance == null) {
          instance = new DatabaseConnectionStub();
        }
      }
    }
    return instance;
  }
}
