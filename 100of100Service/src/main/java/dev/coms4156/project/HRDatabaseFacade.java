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
  // This boolean is used to switch between the real database and the test database
  private static boolean isTestMode = false;
  private static DatabaseConnection dbConnectionStub = null;

  private final DatabaseConnection dbConnection;
  private final int organizationId;
  private List<Employee> employees;
  private List<Department> departments;
  private Organization organization;

  /**
   * Constructs a HR database facade instance for a specific organization.
   * @param organizationId the organization id
   */
  private HRDatabaseFacade(int organizationId) {
    this.dbConnection = isTestMode ? dbConnectionStub : DatabaseConnection.getInstance();
    this.organizationId = organizationId;
    // Initialize the in-memory cache
    this.employees = dbConnection.getEmployees(organizationId);
    this.departments = dbConnection.getDepartments(organizationId);
    this.organization = dbConnection.getOrganization(organizationId);
  }

  /**
   * Returns the employee with the specified ID.
   * @param employeeId the employee ID
   * @return the employee
   */
  public Employee getEmployee(int employeeId) {
    // Check the in-memory cache
    Employee employee = employees
            .stream()
            .filter(e -> e.getId() == employeeId)
            .findFirst()
            .orElse(null);

    if (employee == null) {
      // If not found in cache, query the database
      List<Employee> updatedEmployees = dbConnection.getEmployees(this.organizationId);
      employee = updatedEmployees
              .stream()
              .filter(e -> e.getId() == employeeId)
              .findFirst()
              .orElse(null);

      if (employee != null) {
        // Update the cache
        this.employees = updatedEmployees;
      }
    }

    return employee;
  }

  /**
   * Returns the department with the specified ID.
   * @param departmentId the department ID
   * @return the department
   */
  public Department getDepartment(int departmentId) {
    // Check the in-memory cache
    Department department = departments
            .stream()
            .filter(d -> d.getId() == departmentId)
            .findFirst()
            .orElse(null);

    if (department == null) {
      // If not found in cache, query the database
      List<Department> updatedDepartments = dbConnection.getDepartments(this.organizationId);
      department = updatedDepartments
              .stream()
              .filter(d -> d.getId() == departmentId)
              .findFirst()
              .orElse(null);

      if (department != null) {
        // Update the cache
        this.departments = updatedDepartments;
      }
    }

    return department;
  }

  /**
   * Returns the organization of the client.
   * @return the organization
   */
  public Organization getOrganization() {
    // Check the in-memory cache (this.organization was initialized in constructor)
    return organization;
  }

  /**
   * Updates the department information.
   * @param department the department
   * @return true if the department is updated successfully, false otherwise
   */
  public boolean updateDepartment(Department department) {
    // TODO: Implement database update logic
    return true;
  }

  /**
   * Returns the unique instance of the HR database facade for a specific organization.
   * Designed with "double-checked locking" mechanism to ensure thread safety.
   * @param organizationId the organization id
   * @return the HR database facade instance
   */
  public static HRDatabaseFacade getInstance(int organizationId) {
    if (!instances.containsKey(organizationId)) {
      synchronized (HRDatabaseFacade.class) {
        if (!instances.containsKey(organizationId)) {
          instances.put(organizationId, new HRDatabaseFacade(organizationId));
        }
      }
    }
    return instances.get(organizationId);
  }

  /**
   * Sets the test mode and test database for the HR database facade.
   * @param testDatabaseConnection the test database connection
   *                               (null to disable the test mode)
   */
  public static void setTestMode(DatabaseConnection testDatabaseConnection) {
    if (testDatabaseConnection != null) {
      isTestMode = true;
      HRDatabaseFacade.dbConnectionStub = testDatabaseConnection;
    }
    else {
      isTestMode = false;
      HRDatabaseFacade.dbConnectionStub = null;
    }
  }
}
