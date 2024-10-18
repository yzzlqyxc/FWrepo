package dev.coms4156.project.stubs;

import dev.coms4156.project.DatabaseConnection;
import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.Organization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A test stub for the DatabaseConnection class.
 * This stub provides in-memory data for testing purposes without connecting to a real database.
 */
public class DatabaseConnectionStub extends DatabaseConnection {
  private static volatile DatabaseConnection instance;

  private final Map<Integer, List<Employee>> testEmployees = new HashMap<>();
  private final Map<Integer, List<Department>> testDepartments = new HashMap<>();
  private final Map<Integer, Organization> testOrganizations = new HashMap<>();

  /** Constructs a DatabaseConnectionStub and initializes test data. */
  private DatabaseConnectionStub() {
    initializeTestData();
  }

  /**
   * Retrieves an employee for a given organization by external employee ID.
   *
   * @param organizationId the organization ID (client ID)
   * @param externalEmployeeId the external employee ID
   * @return the Employee object if found, null otherwise
   */
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

  /**
   * Retrieves a department for a given organization by external department ID.
   *
   * @param organizationId the organization ID (client ID)
   * @param externalDepartmentId the external department ID
   * @return the Department object if found, null otherwise
   */
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

  /**
   * Retrieves a list of employees for a given organization.
   *
   * @param organizationId the organization ID (client ID)
   * @return a list of Employee objects
   */
  public List<Employee> getEmployees(int organizationId) {
    return testEmployees.getOrDefault(organizationId, new ArrayList<>());
  }

  /**
   * Retrieves a list of departments for a given organization.
   *
   * @param organizationId the organization ID (client ID)
   * @return a list of Department objects
   */
  public List<Department> getDepartments(int organizationId) {
    return testDepartments.getOrDefault(organizationId, new ArrayList<>());
  }

  /**
   * Retrieves the organization for a given organization ID.
   *
   * @param organizationId the organization ID (client ID)
   * @return the Organization object
   */
  public Organization getOrganization(int organizationId) {
    return testOrganizations.getOrDefault(
            organizationId, new Organization(null, organizationId, "Unknown"));
  }

  /** Initializes the test data for the stub. */
  private void initializeTestData() {
    // Client 1 Data
    int clientId1 = 1;

    // Departments for Client 1
    Department engineering1 = new Department(null, 1, "Engineering", new ArrayList<>());
    Department marketing1 = new Department(null, 2, "Marketing", new ArrayList<>());

    // Employees for Client 1
    Employee johnDoe = new Employee(null, 1, "John Doe", new Date());
    Employee janeSmith = new Employee(null, 2, "Jane Smith", new Date());

    // Add employees to departments for Client 1
    engineering1.addEmployee(johnDoe);
    marketing1.addEmployee(janeSmith);

    // Organization for Client 1
    Organization organization1 = new Organization(null, clientId1, "Organization One");
    organization1.addDepartment(engineering1);
    organization1.addDepartment(marketing1);

    // Store data for Client 1
    testEmployees.put(clientId1, Arrays.asList(johnDoe, janeSmith));
    testDepartments.put(clientId1, Arrays.asList(engineering1, marketing1));
    testOrganizations.put(clientId1, organization1);

    // Client 2 Data
    int clientId2 = 2;

    // Departments for Client 2
    Department engineering2 = new Department(null, 1, "Engineering", new ArrayList<>());
    Department marketing2 = new Department(null, 2, "Marketing", new ArrayList<>());

    // Employees for Client 2
    Employee aliceJohnson = new Employee(null, 1, "Alice Johnson", new Date());
    Employee bobBrown = new Employee(null, 2, "Bob Brown", new Date());

    // Add employees to departments for Client 2
    engineering2.addEmployee(aliceJohnson);
    marketing2.addEmployee(bobBrown);

    // Organization for Client 2
    Organization organization2 = new Organization(null, clientId2, "Organization Two");
    organization2.addDepartment(engineering2);
    organization2.addDepartment(marketing2);

    // Store data for Client 2
    testEmployees.put(clientId2, Arrays.asList(aliceJohnson, bobBrown));
    testDepartments.put(clientId2, Arrays.asList(engineering2, marketing2));
    testOrganizations.put(clientId2, organization2);
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
