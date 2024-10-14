package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.HRDatabaseFacade;

public class GetDeptInfoCommand implements Command {
  private final int clientId;
  private final long departmentId;

  public GetDeptInfoCommand(int clientId, long departmentId) {
    this.clientId = clientId;
    this.departmentId = departmentId;
  }

  @Override
  public Object execute() {
    HRDatabaseFacade db = HRDatabaseFacade.getInstance(this.clientId);
    Department department = db.getDepartment(this.departmentId);
    return department.toString();
  }
}