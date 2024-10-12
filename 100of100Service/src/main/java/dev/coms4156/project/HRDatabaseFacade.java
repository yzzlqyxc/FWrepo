package dev.coms4156.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class of HR database facade.
 * This class is responsible for creating and managing the connection to the HR database.
 * Designed under the Singleton Design Pattern.
 */
public class HRDatabaseFacade {
  private final static Map<Integer, HRDatabaseFacade> instances = new HashMap<>();

  private final DatabaseConnection dbConnection;
  private final int clientId;
  // TODO: Do you want the cache to be List or Map?
  private final List<Employee> employees;
  private final List<Department> departments;
  private final Organization organization;

  /**
   * Constructs a HR database facade instance for a specific client.
   * @param cid the client id
   */
  private HRDatabaseFacade(int cid) {
    this.dbConnection = DatabaseConnection.getInstance();
    this.clientId = cid;
    // Initialize the in-memory cache
    this.employees = dbConnection.getEmployees(cid);
    this.departments = dbConnection.getDepartments(cid);
    this.organization = dbConnection.getOrganization(cid);
  }

  /**
   * Returns the employees of the client.
   * @return the employees
   */
  public Employee getEmployee(int employeeId) {
    // TODO: Check the in-memory cache
    Employee employee = employees
        .stream()
        .filter(e -> e.getId() == employeeId)
        .findFirst()
        .orElse(null)
        ;
    // TODO: If not find, do some query to get the employee from the database
    return employee;
  }

  /**
   * Updates the department information of the client.
   * @param department the department
   * @return true if the department is updated successfully, false otherwise
   */
  public boolean updateDepartment(Department department) {
    // TODO: Alter the on-disk database with this new information
    return true;
  }

  // TODO: Add more methods to interact with the database

  /**
   * Returns the unique instance of the HR database facade for a specific client.
   * Designed with "double-checked locking" mechanism to ensure thread safety.
   * @param clientId the client id
   * @return the HR database facade instance
   */
  public static HRDatabaseFacade getInstance(int clientId) {
    if (!instances.containsKey(clientId)) {
      synchronized (HRDatabaseFacade.class) {
        if (!instances.containsKey(clientId)) {
          instances.put(clientId, new HRDatabaseFacade(clientId));
        }
      }
    }
    return instances.get(clientId);
  }

}
