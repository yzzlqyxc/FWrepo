package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.HrDatabaseFacade;

/**
 * A command to get the information of a department.
 */
public class GetDeptInfoCommand implements Command {
  private final int clientId;
  private final int departmentId;

  public GetDeptInfoCommand(int clientId, int departmentId) {
    this.clientId = clientId;
    this.departmentId = departmentId;
  }

  @Override
  public Object execute() {
    HrDatabaseFacade db = HrDatabaseFacade.getInstance(this.clientId);
    Department department = db.getDepartment(this.departmentId);
    return department.toString();
  }
}