package dev.coms4156.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class of database connection.
 * This class is responsible for creating and managing the connection to the database.
 * Designed under the Singleton Design Pattern.
 */
public class DatabaseConnection {
  private volatile static DatabaseConnection instance;
  // for testing purpose
  private final Map<Integer, List<Employee>> testEmployees = new HashMap<>();
  private final Map<Integer, List<Department>> testDepartments = new HashMap<>();
  private final Map<Integer, Organization> testOrganizations = new HashMap<>();

  private DatabaseConnection() {
    // TODO: Initialize the MySQL database connection, maybe ip, user name, and password here?
    initializeTestData(); // test
  }

  public List<Employee> getEmployees(int clientId) {
    // TODO: Maybe some basic SQL query?
    return testEmployees.getOrDefault(clientId, new ArrayList<>()); // test
    // return null;
  }

  public List<Department> getDepartments(int clientId) {
    // TODO: Maybe some basic SQL query?
    return testDepartments.getOrDefault(clientId, new ArrayList<>());
    // return null;
  }

  public Organization getOrganization(int clientId) {
    // TODO: Maybe some basic SQL query?
    return testOrganizations.getOrDefault(clientId, new Organization(null, clientId, "Unknown"));
    // return null;
  }

  private void initializeTestData() {

    // create test employees and departments
    Employee alice = new Employee(null, 1, "Alice", new Date());
    Employee bob = new Employee(null, 2, "Bob", new Date());
    Employee max = new Employee(null, 3, "Max", new Date());
    Employee lina = new Employee(null, 4, "Lina", new Date());
    Employee john = new Employee(null, 5, "John", new Date());
    Employee jane = new Employee(null, 6, "Jane", new Date());
    Employee emily = new Employee(null, 7, "Emily", new Date());

    Department engineering = new Department(null, 1L, "Engineering", new ArrayList<>());
    Department hr = new Department(null, 2L, "HR", new ArrayList<>());

    // Add departments to the organization
    Organization organization = new Organization(null, 1L, "Test Organization");
    organization.addDepartment(engineering);
    organization.addDepartment(hr);
    organization.addEmployee(max);

    engineering.addEmployee(alice);
    engineering.addEmployee(lina);
    engineering.addEmployee(john);
    hr.addEmployee(bob);
    hr.addEmployee(jane);
    hr.addEmployee(emily);

    // Store employees, departments, and organizations in the fake database
    testEmployees.put(1, List.of(alice, bob, max, lina, john, jane, emily));
    testDepartments.put(1, List.of(engineering, hr));
    testOrganizations.put(1, organization);
  }

  /**
   * Returns the unique instance of the database connection.
   * Designed with "double-checked locking" mechanism to ensure thread safety.
   * @return the database connection instance
   */
  public static DatabaseConnection getInstance() {
    if (instance == null) {
      synchronized (DatabaseConnection.class) {
        if (instance == null) {
          instance = new DatabaseConnection();
        }
      }
    }
    return instance;
  }

}
