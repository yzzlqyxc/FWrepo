package dev.coms4156.project;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a department in the organization.
 * Designed under the Composite Design Pattern.
 */
public class Department implements OrganizationComponent{
  private final long id;
  private final String name;
  private Employee head;
  private final List<OrganizationComponent> children;

  /**
   * Constructs a department with the given ID and name.
   * @param id the ID of the department
   * @param name the name of the department
   */
  public Department(long id, String name) {
    this.id = id;
    this.name = name;
    this.children = new ArrayList<>();
  }

  /**
   * Returns the ID of the department.
   * @return the ID of the department
   */
  @Override
  public long getId() {
    return this.id;
  }

  /**
   * Returns the name of the department.
   * @return the name of the department
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the head of the department.
   * @return the head of the department
   */
  public Employee getHead() {
    return this.head;
  }

  /**
   * Sets the head of the department.
   * @param head the head of the department
   * @return true if the validation passes, false otherwise
   */
  public boolean setHead(Employee head) {
    // TODO: Any validation? Manager must in the department?
    this.head = head;
    return true;
  }

  /**
   * Add an organization component to the department.
   * @param organizationComponent the component to be added
   * @return true if the component is added, false otherwise
   */
  @Override
  public boolean add(OrganizationComponent organizationComponent) {
    return this.children.add(organizationComponent);
  }

  /**
   * Remove an organization component from the department.
   * @param organizationComponent the component to be removed
   * @return true if the component is removed, false otherwise
   */
  @Override
  public boolean remove(OrganizationComponent organizationComponent) {
    return this.children.remove(organizationComponent);
  }

  /**
   * Returns the number of child structure of the department.
   * @return the number of child structure of the department
   */
  @Override
  public int getNumChildren() {
    return this.children.size();
  }

  /**
   * Returns the children of the department.
   * @return the children of the department
   */
  @Override
  public List<OrganizationComponent> getChildren() {
    return this.children;
  }

  /**
   * Returns the basic information of the Department, including the name, ID, and head.
   * @return the string representation of the department
   */
  @Override
  public String toString() {
    if (this.head != null) {
      return "Department: " + this.name + " (ID: " + this.id + ") Head: " + this.head.getName();
    } else {
      return "Department: " + this.name + " (ID: " + this.id + ")";
    }
  }
}
