package dev.coms4156.project;

import java.util.ArrayList;
import java.util.List;

public class Organization implements OrganizationComponent{
  private final long id;
  private final String name;
  private final List<OrganizationComponent> children;
  private final List<Employee> employees;

  /**
   * Constructs an organization with the given ID and name.
   * @param id the ID of the organization
   * @param name the name of the organization
   */
  public Organization(long id, String name) {
    this.id = id;
    this.name = name;
    this.children = new ArrayList<>();
    this.employees = new ArrayList<>();
  }

  /**
   * Returns the ID of the organization.
   * @return the ID of the organization
   */
  @Override
  public long getId() {
    return this.id;
  }

  /**
   * Returns the name of the organization.
   * @return the name of the organization
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Adds a child to the organization.
   * @param organizationComponent the child to add
   * @return true if the child is added successfully, false otherwise
   */
  @Override
  public boolean add(OrganizationComponent organizationComponent) {
    return this.children.add(organizationComponent);
  }

  /**
   * Removes a child from the organization.
   * @param organizationComponent the child to remove
   * @return true if the child is removed successfully, false otherwise
   */
  @Override
  public boolean remove(OrganizationComponent organizationComponent) {
    return this.children.remove(organizationComponent);
  }

  /**
   * Returns the departments of the organization.
   * @return the departments of the organization
   */
  @Override
  public List<OrganizationComponent> getChildren() {
    return this.children;
  }

  /**
   * Returns the number of children of the organization.
   * @return the number of children of the organization
   */
  @Override
  public int getNumChildren() {
    return this.children.size();
  }

  /**
   * Onboarding an employee to the organization.
   * @param employee the employee to be onboarded
   * @return true if the employee is onboarded, false otherwise
   */
  public boolean addEmployee(Employee employee) {
    return this.employees.add(employee);
  }

  /**
   * Offboarding an employee from the organization.
   * @param employee the employee to be offboarded
   * @return true if the employee is offboarded, false otherwise
   */
  public boolean removeEmployee(Employee employee) {
    return this.employees.remove(employee);
  }

  /**
   * Returns the number of employees in the organization.
   * @return the number of employees in the organization
   */
  public int getNumEmployees() {
    return this.employees.size();
  }

  /**
   * Return the string representation of the organization.
   * @return the string representation of the organization
   */
  @Override
  public String toString() {
    return "Organization: " + this.name + " (ID: " + this.id + ")";
  }

  /**
   * Display the hierarchical structure of the organization.
   * Class Static Method
   * @return a string representation of the hierarchical structure of the organization
   */
  public static String displayStructure(OrganizationComponent component, int depth) {
    // TODO: Need to test this method, I write it according to my mind
    StringBuilder sb = new StringBuilder();
    sb.append(" ".repeat(depth * 2)).append("- ").append(component.getName()).append("\n");
    for (OrganizationComponent child : component.getChildren()) {
      sb.append(displayStructure(child, depth + 1));
    }
    return sb.toString();
  }
}
