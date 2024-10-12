package dev.coms4156.project;

/**
 * A singleton class of database connection.
 * This class is responsible for creating and managing the connection to the database.
 * Designed under the Singleton Design Pattern,
 * with "double-checked locking" mechanism to ensure thread safety.
 */
public class DatabaseConnection {
  private volatile static DatabaseConnection instance;

  private DatabaseConnection() {
    // TODO: Initialize the database connection
  }

  /**
   * Returns the unique instance of the database connection.
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
