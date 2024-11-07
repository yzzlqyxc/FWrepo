package dev.coms4156.project;

import java.util.Date;
import java.util.List;
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
    employee1.setPerformance(80);
    employee2 = new Employee(2, "Jake", new Date());
    employee2.setPosition(Position.SoftwareEngineer);
    employee2.setSalary(50);
    employee2.setPerformance(90);
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
    for (Position p : Position.values()) {
      Assertions.assertTrue(ac.containsKey(p));
    }
  }

  @Test
  @Order(12)
  public void testGetEmployeePositionStatisticEmpty() {
    Department emptyDepartment = new Department(4, "Empty");
    Map<Position, Integer> ac = emptyDepartment.getEmployeePositionStatisticMap();
    for (Position p : Position.values()) {
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

  @Test
  @Order(14)
  public void testGetEmployeePerformanceStatistic() {
    Employee e1 = new Employee(1, "A", new Date(), Position.DataScientist, 10.5, 100);
    Employee e2 = new Employee(2, "B", new Date(), Position.SalesManager, 20.5, 90);
    Employee e3 = new Employee(3, "C", new Date(), Position.ProductManager, 30.5, 80);
    Employee e4 = new Employee(4, "D", new Date(), Position.SoftwareEngineer, 40.5, 70);
    Employee e5 = new Employee(5, "E", new Date());
    Department d1 = new Department(1, "D1", List.of(e1, e2, e3, e4, e5));

    Map<String, Object> ac = d1.getEmployeePerformanceStatisticMap();
    System.out.println(ac);
    Assertions.assertTrue(ac.containsKey("Average"));
    Assertions.assertTrue(ac.containsKey("Highest"));
    Assertions.assertEquals(100.0, ac.get("Highest"));
    Assertions.assertTrue(ac.containsKey("25thPercentile"));
    Assertions.assertTrue(ac.containsKey("Median"));
    Assertions.assertTrue(ac.containsKey("75thPercentile"));
    Assertions.assertTrue(ac.containsKey("Lowest"));
    Assertions.assertTrue(ac.containsKey("SortedEmployeeIds"));
    Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, (int[]) ac.get("SortedEmployeeIds"));
  }

}
