package dev.coms4156.project;

import java.util.List;

/**
 * A singleton class of database connection.
 * This class is responsible for creating and managing the connection to the database.
 * Designed under the Singleton Design Pattern.
 */
public class DatabaseConnection {
  private volatile static DatabaseConnection instance;

  protected DatabaseConnection() {
    // TODO: Initialize the MySQL database connection, maybe ip, user name, and password here?
  }

  public List<Employee> getEmployees(int clientId) {
    // TODO: Maybe some basic SQL query?
    return null;
  }

  public List<Department> getDepartments(int clientId) {
    // TODO: Maybe some basic SQL query?
    return null;
  }

  public Organization getOrganization(int clientId) {
    // TODO: Maybe some basic SQL query?
    return null;
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
