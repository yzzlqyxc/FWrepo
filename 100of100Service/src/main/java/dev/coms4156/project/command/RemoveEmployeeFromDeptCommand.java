package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.HRDatabaseFacade;

public class RemoveEmployeeFromDeptCommand implements Command {
  private final int clientId;
  private final int departmentId;
  private final int employeeId;

  public RemoveEmployeeFromDeptCommand(int clientId, int departmentId, int employeeId) {
    this.clientId = clientId;
    this.departmentId = departmentId;
    this.employeeId = employeeId;
  }

  @Override
  public Object execute() {
    HRDatabaseFacade dbFacade = HRDatabaseFacade.getInstance(clientId);

    // Get the department from the database
    Department department = dbFacade.getDepartment(departmentId);
    if (department == null) {
      throw new IllegalArgumentException("Department not found with ID: " + departmentId);
    }

    // Find the employee to remove
    Employee employeeToRemove = department.getEmployees().stream()
      .filter(e -> e.getId() == employeeId)
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));

    // Remove the employee and update the department
    department.removeEmployee(employeeToRemove);
    dbFacade.updateDepartment(department);  // Sync with the database

    return "Employee removed from department: " + department.getName();
  }
}
