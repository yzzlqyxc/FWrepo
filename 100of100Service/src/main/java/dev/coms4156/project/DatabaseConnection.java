package dev.coms4156.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class of database connection.
 * This class is responsible for creating and managing the connection to the database.
 * Designed under the Singleton Design Pattern.
 */
public class DatabaseConnection {
  private static volatile DatabaseConnection instance;
  private Connection connection;

  protected DatabaseConnection() {
    try {
      String url = "jdbc:mysql://database-100-team.c7mqy28ys9uq.us-east-1.rds.amazonaws.com:3306/"
              + "organization_management";
      String user = "admin";
      String password = "sxy6cJEmv6iLT61qs7DO";
      this.connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns an employee in a given organization by external ID.
   *
   * @param organizationId the organization id (clientId)
   * @param externalEmployeeId the external employee id
   * @return the employee if found, null otherwise
   */
  public Employee getEmployee(int organizationId, int externalEmployeeId) {
    int internalEmployeeId = organizationId * 10000 + externalEmployeeId;
    String query = "SELECT * FROM employees WHERE organization_id = ? AND employee_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      pstmt.setInt(2, internalEmployeeId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Employee(
                null, // HRDatabaseFacade instance, passing null for now
                externalEmployeeId,
                rs.getString("name"),
                rs.getDate("hire_date") // Assuming this field exists
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Returns a department in a given organization by external ID.
   *
   * @param organizationId the organization id (clientId)
   * @param externalDepartmentId the external department id
   * @return the department if found, null otherwise
   */
  public Department getDepartment(int organizationId, int externalDepartmentId) {
    int internalDepartmentId = organizationId * 10000 + externalDepartmentId;
    String query = "SELECT * FROM departments WHERE organization_id = ? AND department_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      pstmt.setInt(2, internalDepartmentId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        List<Employee> employees = getEmployeesForDepartment(internalDepartmentId);
        return new Department(
                null, // HRDatabaseFacade instance, passing null for now
                externalDepartmentId,
                rs.getString("name"),
                employees
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Returns a list of employees in a given organization.
   *
   * @param organizationId the organization id
   * @return a list of employees in the organization
   */
  public List<Employee> getEmployees(int organizationId) {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees WHERE organization_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        int internalId = rs.getInt("employee_id");
        int externalId = internalId % 10000;
        Employee employee = new Employee(
                null, // HRDatabaseFacade instance, passing null for now
                externalId,
                rs.getString("name"),
                rs.getDate("hire_date") // Assuming this field exists
        );
        employees.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employees;
  }

  /**
   * Returns a list of departments in a given organization.
   *
   * @param organizationId the organization id
   * @return a list of departments in the organization
   */
  public List<Department> getDepartments(int organizationId) {
    List<Department> departments = new ArrayList<>();
    String query = "SELECT * FROM departments WHERE organization_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        int internalId = rs.getInt("department_id");
        int externalId = internalId % 10000;
        List<Employee> employees = getEmployeesForDepartment(internalId);
        Department department = new Department(
                null, // HRDatabaseFacade instance, passing null for now
                externalId,
                rs.getString("name"),
                employees
        );
        departments.add(department);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return departments;
  }

  /**
   * Returns an organization with the given organization id.
   *
   * @param organizationId the organization id
   * @return the organization with the given organization id
   */
  public Organization getOrganization(int organizationId) {
    String query = "SELECT * FROM organizations WHERE organization_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return new Organization(
                null, // HRDatabaseFacade instance, passing null for now
                rs.getLong("organization_id"),
                rs.getString("name")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Returns a list of employees in a given department.
   *
   * @param internalDepartmentId the internal department id
   * @return a list of employees in the department
   */
  private List<Employee> getEmployeesForDepartment(int internalDepartmentId) {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees WHERE department_id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, internalDepartmentId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        int internalId = rs.getInt("employee_id");
        int externalId = internalId % 10000;
        Employee employee = new Employee(
                null,  // HRDatabaseFacade instance, passing null for now
                externalId,
                rs.getString("name"),
                rs.getDate("hire_date")
        );
        employees.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employees;
  }

  /**
   * Returns the unique instance of the database connection.
   * Designed with "double-checked locking" mechanism to ensure thread safety.
   *
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
