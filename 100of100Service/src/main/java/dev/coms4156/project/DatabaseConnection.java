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
  private volatile static DatabaseConnection instance;
  private Connection connection;

  protected DatabaseConnection() {
    try {
      String url = "jdbc:mysql://database-100-team.c7mqy28ys9uq.us-east-1.rds.amazonaws.com:3306/organization_management";
      String user = "admin";
      String password = "sxy6cJEmv6iLT61qs7DO";
      this.connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Employee> getEmployees(int organizationId) {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees WHERE organization_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Employee employee = new Employee(
                null, // HRDatabaseFacade instance, passing null for now
                rs.getInt("employee_id"),
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

  public List<Department> getDepartments(int organizationId) {
    List<Department> departments = new ArrayList<>();
    String query = "SELECT * FROM departments WHERE organization_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setInt(1, organizationId);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        Department department = new Department(
                null, // HRDatabaseFacade instance, passing null for now
                rs.getInt("department_id"),
                rs.getString("name"),
                getEmployeesForDepartment(rs.getLong("department_id"))
        );
        departments.add(department);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return departments;
  }

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

  private List<Employee> getEmployeesForDepartment(long departmentId) {
    List<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees WHERE department_id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
      pstmt.setLong(1, departmentId);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Employee employee = new Employee(
              null,  // HRDatabaseFacade instance, passing null for now
            rs.getInt("employee_id"),
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
