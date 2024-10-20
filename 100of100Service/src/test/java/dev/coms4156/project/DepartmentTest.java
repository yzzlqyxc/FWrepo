package dev.coms4156.project;

import dev.coms4156.project.stubs.DatabaseConnectionStub;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * A unit test class for the Department class.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentTest {
  private static HrDatabaseFacade dbf;
  private static Department department;
  private static Employee employee1;
  private static Employee employee2;

  /**
   * Set up the test environment.
   */
  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HrDatabaseFacade.setTestMode(dbConnectionStub);
    dbf = HrDatabaseFacade.getInstance(1);
    employee1 = new Employee(1, "John", new Date());
    employee2 = new Employee(2, "Jake", new Date());
  }

  @Test
  @Order(1)
  public void testCreateDepartment() {
    department = new Department(1, "Teaching");
    Assertions.assertNotNull(department);
  }

  @Test
  @Order(2)
  public void testAddEmployee() {
    boolean result1 = department.addEmployee(employee1);
    Assertions.assertTrue(result1);
    Assertions.assertEquals(1, department.getEmployees().size());
    boolean result2 = department.addEmployee(employee2);
    Assertions.assertTrue(result2);
    Assertions.assertEquals(2, department.getEmployees().size());
  }

  @Test
  @Order(3)
  public void testRemoveEmployee() {
    boolean result = department.removeEmployee(employee1);
    Assertions.assertTrue(result);
    Assertions.assertEquals(1, department.getEmployees().size());
  }

  @Test
  @Order(4)
  public void testRemoveEmployeeNotInDepartment() {
    boolean result = department.removeEmployee(employee1);
    Assertions.assertFalse(result);
    Assertions.assertEquals(1, department.getEmployees().size());
  }

  @Test
  @Order(5)
  public void testRemoveEmployeeFromEmptyDepartment() {
    Department emptyDepartment = new Department(2, "Empty");
    boolean result = emptyDepartment.removeEmployee(employee2);
    Assertions.assertFalse(result);
    Assertions.assertEquals(0, emptyDepartment.getEmployees().size());
  }

  @Test
  @Order(6)
  public void testSetHead() {
    boolean result = department.setHead(employee2);
    Assertions.assertTrue(result);
    Assertions.assertEquals(employee2, department.getHead());
  }

  @Test
  @Order(7)
  public void testSetHeadToNull() {
    boolean result = department.setHead(null);
    Assertions.assertTrue(result);
    Assertions.assertNull(department.getHead());
  }

  @Test
  @Order(8)
  public void testToString1() {
    String expected = "Department: Teaching (ID: 1)\n  Employees:\n    - Jake (ID: 2)";
    Assertions.assertEquals(expected, department.toString());
  }

  @Test
  @Order(9)
  public void testToString2() {
    Department emptyDepartment = new Department(3, "Empty");
    String expected = "Department: Empty (ID: 3)\n  No employees in this department.";
    Assertions.assertEquals(expected, emptyDepartment.toString());
  }

  @Test
  @Order(10)
  public void testToString3() {
    department.setHead(employee2);
    String expected = "Department: Teaching (ID: 1) Head: Jake\n  Employees:\n    - Jake (ID: 2)";
    Assertions.assertEquals(expected, department.toString());
  }

  @AfterAll
  public static void tearDown() {
    HrDatabaseFacade.setTestMode(null);
  }

}
