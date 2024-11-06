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
 * A unit test class for the Employee class.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {
  private static Date date;
  private static HrDatabaseFacade dbf;
  private static Employee employee;

  /**
   * Set up the test environment.
   */
  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HrDatabaseFacade.setTestMode(dbConnectionStub);
    dbf = HrDatabaseFacade.getInstance(1);
    date = new Date();
    employee = new Employee(1, "Test", date);
  }

  @Test
  @Order(1)
  public void testEmployeeConstructor() {
    Assertions.assertNotNull(employee);
  }

  @Test
  @Order(2)
  public void testGetId() {
    Assertions.assertEquals(1, employee.getId());
  }

  @Test
  @Order(3)
  public void testGetName() {
    Assertions.assertEquals("Test", employee.getName());
  }

  @Test
  @Order(4)
  public void testGetTypeName() {
    Assertions.assertEquals("Employee", employee.getTypeName());
  }

  @Test
  @Order(5)
  public void testGetChildStructure() {
    Assertions.assertTrue(employee.getChildren().isEmpty());
  }

  @Test
  @Order(6)
  public void testGetHireDate() {
    Assertions.assertEquals(date, employee.getHireDate());
  }

  @Test
  @Order(7)
  public void testGetEmployeeInfo() {
    String expected = "Employee: Test (ID: 1) Hired at: " + date.toString();
    Assertions.assertEquals(expected, employee.toString());
  }

  @Test
  @Order(8)
  public void testGetEmployeeInfoWithNullDate() {
    Date current = new Date();
    Employee employeeNullDate = new Employee(1, "TestND", null);
    String expected = "Employee: TestND (ID: 1) Hired at: " + current;
    Assertions.assertEquals(expected, employeeNullDate.toString());
  }

  @Test
  @Order(9)
  public void testPolymorphism() {
    Assertions.assertInstanceOf(OrganizationComponent.class, employee);
    Assertions.assertInstanceOf(Employee.class, employee);
  }

  @Test
  @Order(10)
  public void testSetPosition() {
    employee.setPosition(Position.SoftwareEngineer);
    Assertions.assertEquals(Position.SoftwareEngineer, employee.getPosition());
  }

  @Test
  @Order(11)
  public void testSetSalary() {
    employee.setSalary(100000);
    Assertions.assertEquals(100000, employee.getSalary());
  }

  @AfterAll
  public static void tearDown() {
    HrDatabaseFacade.setTestMode(null);
  }

}
