package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.HRDatabaseFacade;

public class SetDeptHeadCommand implements Command {
  private final int clientId;
  private final int departmentId;
  private final int employeeId;

  /**
   * Constructs a command to set the head of a department.
   * @param clientId the client ID
   * @param departmentId the department ID
   * @param employeeId the employee ID
   */
  public SetDeptHeadCommand(int clientId, int departmentId, int employeeId) {
    this.clientId = clientId;
    this.departmentId = departmentId;
    this.employeeId = employeeId;
  }

  @Override
  public Object execute() {
    HRDatabaseFacade db = HRDatabaseFacade.getInstance(this.clientId);
    Department department = db.getDepartment(this.departmentId);
    Employee employee = db.getEmployee(this.employeeId);
    // TODO: error checking on Dept and Emp, and how to return HTTP 404
    return department.setHead(employee);
  }
}