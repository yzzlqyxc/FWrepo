package dev.coms4156.project;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a department in the organization.
 * Designed under the Composite Design Pattern.
 */
public class Department extends OrganizationComposite {
  private Employee head;
  private final List<Employee> employees;

  /**
   * Constructs a department with the given ID and name.
   *
   * @param db   the HR database facade that manages the department
   * @param id   the ID of the department
   * @param name the name of the department
   */
  public Department(HRDatabaseFacade db, long id, String name, List<Employee> employees) {
    super(db, id, name);
    this.employees = new ArrayList<>();
    this.head = null;
  }

  /**
   * Adds an employee to the department.
   *
   * @param employee the employee to be added
   * @return true if the employee is added successfully, false otherwise
   */
  public boolean addEmployee(Employee employee) {
    this.employees.add(employee); // Add to employee list
    return this.add(employee);
  }

  /**
   * Removes an employee from the department.
   *
   * @param employee the employee to be removed
   * @return true if the employee is removed successfully, false otherwise
   */
  public boolean removeEmployee(Employee employee) {
    this.employees.remove(employee); // Remove from employee list
    return this.remove(employee);
  }

  /**
   * Returns the list of employees in the department.
   *
   * @return the list of employees
   */
  public List<Employee> getEmployees() {
    return new ArrayList<>(this.employees); // Return a copy of the employee list
  }

  /**
   * Returns the head of the department.
   *
   * @return the head of the department
   */
  public Employee getHead() {
    return this.head;
  }

  /**
   * Sets the head of the department.
   *
   * @param head the head of the department
   * @return true if the validation passes, false otherwise
   */
  public boolean setHead(Employee head) {
    // TODO: Any validation? Manager must in the department?
    // TODO: find a proper way to update database. since it's test database, it's set to null
    this.head = head;
    //this.db.updateDepartment(this); // Update the department information to the database
    return true;
  }

  /**
   * Returns the basic information of the Department, including the name, ID, list of employees, and head.
   *
   * @return the string representation of the department
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    // add employee infos in the toString()
    sb.append("Department: ").append(this.name)
      .append(" (ID: ").append(this.id).append(")");
    if (this.head != null) {
      sb.append(" Head: ").append(this.head.getName());
    }

    if (!this.employees.isEmpty()) {
      sb.append("\n  Employees:");  // Indent employees section
      for (Employee employee : this.employees) {
        sb.append("\n    - ").append(employee.getName())
          .append(" (ID: ").append(employee.getId()).append(")");
      }
    } else {
      sb.append("\n  No employees in this department.");
    }

    return sb.toString();
  }
}
