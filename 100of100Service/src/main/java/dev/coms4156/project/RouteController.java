package dev.coms4156.project;

import dev.coms4156.project.command.Command;
import dev.coms4156.project.command.GetDeptInfoCommand;
import dev.coms4156.project.command.GetEmployeeInfoCommand;
import dev.coms4156.project.command.GetOrganizationInfoCommand;
import dev.coms4156.project.command.SetDeptHeadCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains all the API routes for the system.
 */
@RestController
public class RouteController {

  /**
   * Redirects to the homepage.
   */
  @GetMapping({"/", "/index", "/home"})
  public String index() {
    return "Welcome, in order to make an API call direct your browser or Postman to an endpoint.";
  }

  /**
   * Gets the information of an employee.
   * @param clientId the client ID
   * @param employeeId the employee ID
   * @return the information of the employee
   */
  @GetMapping(value = "/getEmpInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getEmployee(
      @RequestParam("cid") int clientId,
      @RequestParam("eid") int employeeId
  ) {
    try {
      Command command = new GetEmployeeInfoCommand(clientId, employeeId);
      return new ResponseEntity<>(command.execute(), HttpStatus.OK);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  /**
   * Gets the information of a department.
   * @param clientId the client ID
   * @param departmentId the department ID
   * @return the information of the department
   */
  @GetMapping(value = "/getDeptInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getDepartment(
      @RequestParam("cid") int clientId,
      @RequestParam("did") int departmentId
  ) {
    try {
      Command command = new GetDeptInfoCommand(clientId, departmentId);
      return new ResponseEntity<>(command.execute(), HttpStatus.OK);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  /**
   * Gets the information of an organization.
   * @param clientId the client ID
   * @return the information of the organization
   */
  @GetMapping(value = "/getOrgInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getOrganization(
      @RequestParam("cid") int clientId
  ) {
    try {
      Command command = new GetOrganizationInfoCommand(clientId);
      return new ResponseEntity<>(command.execute(), HttpStatus.OK);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  /**
   * Sets the head of a department.
   * @param clientId the client ID
   * @param departmentId the department ID
   * @param employeeId the employee ID
   * @return true if the head is set, false otherwise
   */
  @PatchMapping(value = "/setDeptHead", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> setDeptHead(
      @RequestParam("cid") int clientId,
      @RequestParam("did") int departmentId,
      @RequestParam("eid") int employeeId
  ) {
    try {
      Command command = new SetDeptHeadCommand(clientId, departmentId, employeeId);
      return new ResponseEntity<>(command.execute(), HttpStatus.OK);
    } catch (Exception e) {
      return handleException(e);
    }
  }


  /**
   * Handles any exceptions that occur in controller.
   */
  private ResponseEntity<?> handleException(Exception e) {
    System.out.println(e.toString());
    return new ResponseEntity<>("An Error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }


}