package dev.coms4156.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents an employee in the organization.
 * Designed under the Composite Design Pattern.
 */
public class Employee implements OrganizationComponent {
  private final HRDatabaseFacade db;
  private final int id;
  private final String name;
  private final Date hireDate;

  /**
   * Constructs an employee with the given ID, name, and hire date.
   * @param db the HR database facade that manages the employee
   * @param id the ID of the employee
   * @param name the name of the employee
   * @param hireDate the hire date of the employee
   */
  public Employee(HRDatabaseFacade db, int id, String name, Date hireDate) {
    this.db = db;
    this.id = id;
    this.name = name;
    this.hireDate = new Date(hireDate.getTime());
  }

  /**
   * Returns the ID of the employee.
   * @return the ID of the employee
   */
  @Override
  public long getId() {
    return this.id;
  }

  /**
   * Returns the name of the employee.
   * @return the name of the employee
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the child structure of the employee.
   * @return an empty list
   */
  @Override
  public List<OrganizationComponent> getChildren() {
    return new ArrayList<>();
  }

  /**
   * Returns the hire date of the employee.
   * @return the hire date of the employee
   */
  public Date getHireDate() {
    return new Date(this.hireDate.getTime());
  }


  /**
   * Returns the basic information of the employee, including the name and ID.
   * @return the string representation of the employee
   */
  @Override
  public String toString() {
    return "Employee: " + this.name + " (ID: " + this.id + ")";
  }
}
