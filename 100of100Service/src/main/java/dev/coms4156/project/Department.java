package dev.coms4156.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   * @param id the ID of the department (external ID)
   * @param name the name of the department
   */
  public Department(int id, String name) {
    super(id, name);
    this.typeName = "Department";
    this.employees = new ArrayList<>();
    this.head = null;
  }

  /**
   * Constructs a department with the given ID, name, and list of employees.
   *
   * @param id   the ID of the department (external ID)
   * @param name the name of the department
   * @param employees the list of existing employees in the department
   */
  public Department(int id, String name, List<Employee> employees) {
    super(id, name);
    this.typeName = "Department";
    this.employees = employees;
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
    // We choose to return the list itself, not a copy, for performance reasons
    // caller can make the decision to copy the list if needed
    return this.employees;
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
    this.head = head;
    return true;
  }

  /**
   * Returns a statistic of the employees' positions in the department.
   *
   * @return a Map of the statistic that can be easily converted to JSON
   */
  public Map<Position, Integer> getEmployeePositionStatisticMap() {
    Map<Position, Integer> result = new HashMap<>();
    for (Position p : Position.values()) {
      int count = 0;
      for (Employee e : this.employees) {
        if (e.getPosition() == p) {
          count++;
        }
      }
      result.put(p, count);
    }
    return result;
  }

  /**
   * Returns a statistic of the employees' salaries in the department.
   * The statistic includes the total salary, average salary,
   * and the highest and lowest salaries and the corresponding employees.
   * If there are multiple employees with the same highest or lowest salary,
   * only the first one will be shown.
   *
   * @return a Map of the statistic that can be easily converted to JSON
   */
  public Map<String, Object> getEmployeeSalaryStatisticMap() {
    double totalSalary = 0;
    double highestSalary = Double.MIN_VALUE;
    double lowestSalary = Double.MAX_VALUE;
    Employee highestEmployee = null;
    Employee lowestEmployee = null;

    // Gather statistics
    for (Employee e : this.employees) {
      totalSalary += e.getSalary();
      if (e.getSalary() > highestSalary) {
        highestSalary = e.getSalary();
        highestEmployee = e;
      }
      if (e.getSalary() < lowestSalary) {
        lowestSalary = e.getSalary();
        lowestEmployee = e;
      }
    }
    double averageSalary = totalSalary / this.employees.size();

    // Construct the result map
    Map<String, Object> result = new HashMap<>();
    result.put("Total", totalSalary);
    result.put("Average", averageSalary);
    result.put("Highest", highestSalary);
    result.put("Lowest", lowestSalary);
    result.put("HighestEmployee", highestEmployee != null ? highestEmployee.getId() : null);
    result.put("LowestEmployee", lowestEmployee != null ? lowestEmployee.getId() : null);
    return result;
  }

  /**
   * Returns a statistic of the employees' performance in the department.
   * The statistic includes the highest, average, xth quartile, median, and lowest scores.
   * In addition, a list of employee id sorted by performance is also included.
   *
   * @return a Map of the statistic that can be easily converted to JSON
   */
  public Map<String, Object> getEmployeePerformanceStatisticMap() {
    List<Double> performances = new ArrayList<>();
    for (Employee e : this.employees) {
      performances.add(e.getPerformance());
    }
    performances.sort(Double::compareTo);

    // Construct the result map
    Map<String, Object> result = new HashMap<>();
    // Quartiles
    result.put("Highest", performances.get(performances.size() - 1));
    result.put("25thPercentile", performances.get(performances.size() / 4));
    result.put("Median", performances.get(performances.size() / 2));
    result.put("75thPercentile", performances.get(performances.size() * 3 / 4));
    result.put("Lowest", performances.get(0));
    // Average
    result.put(
        "Average",
        performances.stream().mapToDouble(Double::doubleValue).average().orElse(0)
    );
    // Sorted employee IDs
    int[] ids = this.employees.stream()
        .sorted((e1, e2) -> Double.compare(e2.getPerformance(), e1.getPerformance()))
        .mapToInt(Employee::getId)
        .toArray();
    result.put("SortedEmployeeIds", ids);
    return result;
  }

  /**
   * Returns the basic information of the Department,
   * including the name, ID, list of employees, and head.
   *
   * @return the string representation of the department
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    // add employee infos in the toString()
    sb.append("Department: ").append(this.name).append(" (ID: ").append(this.id).append(")");
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
