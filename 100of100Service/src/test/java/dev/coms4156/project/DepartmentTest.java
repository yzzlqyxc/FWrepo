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

import java.util.ArrayList;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentTest {
  private static HRDatabaseFacade dbf;
  private static Department department;

  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HRDatabaseFacade.setTestMode(dbConnectionStub);
    dbf = HRDatabaseFacade.getInstance(1);
  }

  @Test
  @Order(1)
  public void testCreateDepartment() {
    // TODO: solve conflicts first
  }


  @AfterAll
  public static void tearDown() {
    HRDatabaseFacade.setTestMode(null);
  }

}
