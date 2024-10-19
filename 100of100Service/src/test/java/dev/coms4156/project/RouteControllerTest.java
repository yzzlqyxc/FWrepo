package dev.coms4156.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.coms4156.project.stubs.DatabaseConnectionStub;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * An integration test class for the RouteController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private static final String CLIENT_ID_1 = "MQ";
  private static final String CLIENT_ID_2 = "Mg";
  private static final String CLIENT_ID_99 = "OTk";

  /**
   * Set up the test environment.
   * Set the database connection to the stub, and flag the TestMode.
   */
  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HrDatabaseFacade.setTestMode(dbConnectionStub);
  }

  @Test
  public void testGetEmployeeInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getEmpInfo")
        .param("cid", CLIENT_ID_1)
        .param("eid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    // String expected = "Employee: Alice (ID: 1)";
    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testGetDeptInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getDeptInfo")
        .param("cid", CLIENT_ID_1)
        .param("did", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testGetOrganizationInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getOrgInfo")
        .param("cid", CLIENT_ID_1)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    // String expected = "Department: Engineering (ID: 1)";
    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testSetDeptHead() throws Exception {
    // patch for test
    mockMvc.perform(patch("/setDeptHead")
        .param("cid", CLIENT_ID_1)
        .param("did", "1")
        .param("eid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    // get department to check if head is set
    MvcResult mvcResult1 = mockMvc.perform(get("/getDeptInfo")
        .param("cid", CLIENT_ID_1)
        .param("did", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  // Test: Client cannot access another client's employee
  @Test
  public void testClientCannotAccessAnotherClientsEmployee() throws Exception {
    // Client 1 tries to access Client 2's Employee ID 1
    MvcResult mvcResult = mockMvc.perform(get("/getEmpInfo")
                    .param("cid", CLIENT_ID_1)
                    .param("eid", "1")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    // Should contain "John Doe", not "Alice Johnson"
    assert (content.contains("John Doe"));
    assert (!content.contains("Alice Johnson"));
  }

  // Test: Accessing a non-existent employee returns an error
  @Test
  public void testAccessNonExistentEmployee() throws Exception {
    // Client 1 tries to access Employee ID 99 (does not exist)
    mockMvc.perform(get("/getEmpInfo")
                    .param("cid", CLIENT_ID_1)
                    .param("eid", "99")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  // Test: Invalid client ID returns an error
  @Test
  public void testInvalidClientId() throws Exception {
    // Client ID 99 does not exist
    mockMvc.perform(get("/getEmpInfo")
                    .param("cid", CLIENT_ID_99)
                    .param("eid", "1")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  // Test: Boundary case - negative employee ID
  @Test
  public void testNegativeEmployeeId() throws Exception {
    mockMvc.perform(get("/getEmpInfo")
                    .param("cid", CLIENT_ID_1)
                    .param("eid", "-1")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  /**
   * Tear down the test environment.
   * Reset the database connection to the real database.
   */
  @AfterAll
  public static void tearDown() {
    HrDatabaseFacade.setTestMode(null);
  }
}
