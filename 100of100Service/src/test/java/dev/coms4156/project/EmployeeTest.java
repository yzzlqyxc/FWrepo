package dev.coms4156.project;

import dev.coms4156.project.stubs.DatabaseConnectionStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {
  private static Date date;
  private static HRDatabaseFacade dbf;
  private static Employee employee;

  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HRDatabaseFacade.setTestMode(dbConnectionStub);
    dbf = HRDatabaseFacade.getInstance(1);
    date = new Date();
    employee = new Employee(dbf, 1, "Test", date);
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
    Employee employeeNullDate = new Employee(dbf, 1, "TestND", null);
    String expected = "Employee: TestND (ID: 1) Hired at: " + current;
    Assertions.assertEquals(expected, employeeNullDate.toString());
  }

  @Test
  @Order(9)
  public void testPolymorphism() {
    Assertions.assertInstanceOf(OrganizationComponent.class, employee);
    Assertions.assertInstanceOf(Employee.class, employee);
  }

  @AfterAll
  public static void tearDown() {
    HRDatabaseFacade.setTestMode(null);
  }

}
