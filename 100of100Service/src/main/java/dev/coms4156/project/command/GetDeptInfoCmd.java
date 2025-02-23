package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.HrDatabaseFacade;
import dev.coms4156.project.exception.NotFoundException;

/**
 * A command to get the information of a department.
 */
public class GetDeptInfoCmd implements Command {
  private final int clientId;
  private final int departmentId;

  public GetDeptInfoCmd(int clientId, int departmentId) {
    this.clientId = clientId;
    this.departmentId = departmentId;
  }

  @Override
  public Object execute() {
    HrDatabaseFacade db = HrDatabaseFacade.getInstance(this.clientId);
    Department department = db.getDepartment(this.departmentId);
    if (department == null) {
      throw new NotFoundException("Department [" + this.departmentId + "] not found");
    }
    return department.toJson();
  }
}