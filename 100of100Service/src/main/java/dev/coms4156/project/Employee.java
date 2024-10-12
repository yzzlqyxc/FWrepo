package dev.coms4156.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents an employee in the organization.
 * Designed under the Composite Design Pattern.
 */
public class Employee implements OrganizationComponent {
  private final int id;
  private final String name;
  private final Date hireDate;

  public Employee(int id, String name, Date hireDate) {
    this.id = id;
    this.name = name;
    this.hireDate = hireDate;
  }

  /**
   * Returns the ID of the employee.
   * @return the ID of the employee
   */
  public long getId() {
    return this.id;
  }

  /**
   * Returns the name of the employee.
   * @return the name of the employee
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the hire date of the employee.
   * @return the hire date of the employee
   */
  public Date getHireDate() {
    return this.hireDate;
  }

  /**
   * Returns the number of child structure of the employee.
   * @return null
   * @throws UnsupportedOperationException since employees cannot have children structure
   */
  public boolean add(OrganizationComponent organizationComponent) {
    throw new UnsupportedOperationException("Employee doesn't support adding children.");
  }

  /**
   * Returns the number of child structure of the employee.
   * @return null
   * @throws UnsupportedOperationException since employees cannot have children structure
   */
  public boolean remove(OrganizationComponent organizationComponent) {
    throw new UnsupportedOperationException("Employee doesn't support removing children.");
  }

  /**
   * Returns the number of child structure of the employee.
   * @return 0
   */
  public int getNumChildren() {
    return 0;
  }

  /**
   * Returns the child structure of the employee.
   * @return an empty list
   */
  public List<OrganizationComponent> getChildren() {
    return new ArrayList<OrganizationComponent>();
  }

  /**
   * Returns the basic information of the employee, including the name and ID.
   * @return the string representation of the employee
   */
  public String toString() {
    return "Employee: " + this.name + " (ID: " + this.id + ")";
  }
}
