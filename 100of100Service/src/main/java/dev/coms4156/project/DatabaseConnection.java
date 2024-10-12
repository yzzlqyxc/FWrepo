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
    List<Employee> employees = List.of(
      new Employee(null, 1, "Alice", new Date()),
      new Employee(null, 2, "Bob", new Date())
    );
    // Create a fake department and assign it some employees
    Department engineering = new Department(null, 1L, "Engineering");
    Department hr = new Department(null, 2L, "HR");

    // Add departments to the organization
    Organization organization = new Organization(null, 1L, "Test Organization");
    organization.addDepartment(engineering);
    organization.addDepartment(hr);

    // Store employees, departments, and organizations in the fake database
    testEmployees.put(1, employees);
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
