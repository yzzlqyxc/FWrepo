package dev.coms4156.project.command;

import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.HRDatabaseFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEmployeeToDeptCommand implements Command {
  private final int clientId;
  private final int departmentId;
  private final String name;
  private final String hireDate;

  public AddEmployeeToDeptCommand(int clientId, int departmentId, String name, String hireDate) {
    this.clientId = clientId;
    this.departmentId = departmentId;
    this.name = name;
    this.hireDate = hireDate;
  }

  @Override
  public Object execute() {
    HRDatabaseFacade dbFacade = HRDatabaseFacade.getInstance(clientId);

    // Fetch the department from the in-memory cache or DB
    Department department = dbFacade.getDepartment(departmentId);
    if (department == null) {
      throw new IllegalArgumentException("Department not found with ID: " + departmentId);
    }

    // we parse the hireDate string into a Date object
    Date parsedHireDate;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      parsedHireDate = sdf.parse(hireDate);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd.", e);
    }

    // Create the new employee
    Employee newEmployee = new Employee(dbFacade, department.getEmployees().size() + 1, name, parsedHireDate);

    // Add the employee to the department and sync with the database
    department.addEmployee(newEmployee);
    dbFacade.updateDepartment(department); // Sync with database

    return "Employee added to department: " + department.getName();
  }
}
