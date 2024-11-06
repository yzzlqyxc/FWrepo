package dev.coms4156.project;

import java.util.Date;
import java.util.Map;
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
  private static Department department;
  private static Employee employee1;
  private static Employee employee2;

  /**
   * Set up the test environment.
   */
  @BeforeAll
  public static void setUp() {
    employee1 = new Employee(1, "John", new Date());
    employee1.setPosition(Position.ProductManager);
    employee1.setSalary(100);
    employee2 = new Employee(2, "Jake", new Date());
    employee2.setPosition(Position.SoftwareEngineer);
    employee2.setSalary(50);
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

  @Test
  @Order(11)
  public void testGetEmployeePositionStatistic() {
    Map<Position, Integer> ac = department.getEmployeePositionStatisticMap();
    System.out.println(ac);
    for (Position p: Position.values()) {
      Assertions.assertTrue(ac.containsKey(p));
    }
  }

  @Test
  @Order(12)
  public void testGetEmployeePositionStatisticEmpty() {
    Department emptyDepartment = new Department(4, "Empty");
    Map<Position, Integer> ac = emptyDepartment.getEmployeePositionStatisticMap();
    for (Position p: Position.values()) {
      Assertions.assertTrue(ac.containsKey(p));
      Assertions.assertEquals(0, ac.get(p));
    }
  }

  @Test
  @Order(13)
  public void testGetEmployeeSalaryStatistic() {
    Map<String, Object> ac = department.getEmployeeSalaryStatisticMap();
    System.out.println(ac);
    Assertions.assertTrue(ac.containsKey("Total"));
    Assertions.assertEquals(50.0, ac.get("Total"));
    Assertions.assertTrue(ac.containsKey("Average"));
    Assertions.assertTrue(ac.containsKey("Highest"));
    Assertions.assertTrue(ac.containsKey("Lowest"));
    Assertions.assertTrue(ac.containsKey("HighestEmployee"));
    Assertions.assertEquals(employee2.getId(), ac.get("HighestEmployee"));
    Assertions.assertTrue(ac.containsKey("LowestEmployee"));
  }

}
