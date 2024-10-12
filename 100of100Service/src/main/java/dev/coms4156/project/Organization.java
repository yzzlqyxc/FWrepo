package dev.coms4156.project;

import java.util.ArrayList;
import java.util.List;

public class Organization extends OrganizationComposite {
  private final List<Employee> employees;

  /**
   * Constructs an organization with the given ID and name.
   * @param id the ID of the organization
   * @param name the name of the organization
   */
  public Organization(long id, String name) {
    super(id, name);
    this.employees = new ArrayList<>();
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
