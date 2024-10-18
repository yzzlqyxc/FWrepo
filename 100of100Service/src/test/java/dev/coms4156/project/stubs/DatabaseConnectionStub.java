package dev.coms4156.project.stubs;

import dev.coms4156.project.DatabaseConnection;
import dev.coms4156.project.Department;
import dev.coms4156.project.Employee;
import dev.coms4156.project.Organization;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnectionStub extends DatabaseConnection {
  private static volatile DatabaseConnection instance;

  private final Map<Integer, List<Employee>> testEmployees = new HashMap<>();
  private final Map<Integer, List<Department>> testDepartments = new HashMap<>();
  private final Map<Integer, Organization> testOrganizations = new HashMap<>();

  private DatabaseConnectionStub() {
    initializeTestData();
  }

  public Employee getEmployee(int organizationId, int externalEmployeeId) {
    List<Employee> employees = testEmployees.get(organizationId);
    if (employees != null) {
      for (Employee employee : employees) {
        if (employee.getId() == externalEmployeeId) {
          return employee;
        }
      }
    }
    return null;
  }

  public Department getDepartment(int organizationId, int externalDepartmentId) {
    List<Department> departments = testDepartments.get(organizationId);
    if (departments != null) {
      for (Department department : departments) {
        if (department.getId() == externalDepartmentId) {
          return department;
        }
      }
    }
    return null;
  }

  public List<Employee> getEmployees(int organizationId) {
    return testEmployees.getOrDefault(organizationId, new ArrayList<>());
  }

  public List<Department> getDepartments(int organizationId) {
    return testDepartments.getOrDefault(organizationId, new ArrayList<>());
  }

  public Organization getOrganization(int organizationId) {
    return testOrganizations.getOrDefault(organizationId, new Organization(null, organizationId, "Unknown"));
  }

  private void initializeTestData() {
    // Client 1 Data
    int clientId1 = 1;

    // Departments for Client 1
    Department engineering1 = new Department(null, 1, "Engineering", new ArrayList<>());
    Department marketing1 = new Department(null, 2, "Marketing", new ArrayList<>());

    // Employees for Client 1
    Employee johnDoe = new Employee(null, 1, "John Doe", new Date());
    Employee janeSmith = new Employee(null, 2, "Jane Smith", new Date());

    // Add employees to departments for Client 1
    engineering1.addEmployee(johnDoe);
    marketing1.addEmployee(janeSmith);

    // Organization for Client 1
    Organization organization1 = new Organization(null, clientId1, "Organization One");
    organization1.addDepartment(engineering1);
    organization1.addDepartment(marketing1);

    // Store data for Client 1
    testEmployees.put(clientId1, Arrays.asList(johnDoe, janeSmith));
    testDepartments.put(clientId1, Arrays.asList(engineering1, marketing1));
    testOrganizations.put(clientId1, organization1);

    // Client 2 Data
    int clientId2 = 2;

    // Departments for Client 2
    Department engineering2 = new Department(null, 1, "Engineering", new ArrayList<>());
    Department marketing2 = new Department(null, 2, "Marketing", new ArrayList<>());

    // Employees for Client 2
    Employee aliceJohnson = new Employee(null, 1, "Alice Johnson", new Date());
    Employee bobBrown = new Employee(null, 2, "Bob Brown", new Date());

    // Add employees to departments for Client 2
    engineering2.addEmployee(aliceJohnson);
    marketing2.addEmployee(bobBrown);

    // Organization for Client 2
    Organization organization2 = new Organization(null, clientId2, "Organization Two");
    organization2.addDepartment(engineering2);
    organization2.addDepartment(marketing2);

    // Store data for Client 2
    testEmployees.put(clientId2, Arrays.asList(aliceJohnson, bobBrown));
    testDepartments.put(clientId2, Arrays.asList(engineering2, marketing2));
    testOrganizations.put(clientId2, organization2);
  }

  public static DatabaseConnection getInstance() {
    if (instance == null) {
      synchronized (DatabaseConnectionStub.class) {
        if (instance == null) {
          instance = new DatabaseConnectionStub();
        }
      }
    }
    return instance;
  }
}
