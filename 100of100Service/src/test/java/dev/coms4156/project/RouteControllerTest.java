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


@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  /**
   * Set up the test environment.
   * Set the database connection to the stub, and flag the TestMode.
   */
  @BeforeAll
  public static void setUp() {
    DatabaseConnection dbConnectionStub = DatabaseConnectionStub.getInstance();
    HRDatabaseFacade.setTestMode(dbConnectionStub);
  }

  @Test
  public void testGetEmployeeInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getEmpInfo")
        .param("cid", "1")
        .param("eid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

//    String expected = "Employee: Alice (ID: 1)";
    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testGetDeptInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getDeptInfo")
        .param("cid", "1")
        .param("did", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testGetOrganizationInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getOrgInfo")
        .param("cid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

//    String expected = "Department: Engineering (ID: 1)";
    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  @Test
  public void testSetDeptHead() throws Exception {
    // patch for test
    mockMvc.perform(patch("/setDeptHead")
        .param("cid", "1")
        .param("did", "1")
        .param("eid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    // get department to check if head is set
    MvcResult mvcResult1 = mockMvc.perform(get("/getDeptInfo")
        .param("cid", "1")
        .param("did", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    String content = mvcResult1.getResponse().getContentAsString();
    System.out.println(content);
  }

  /**
   * Tear down the test environment.
   * Reset the database connection to the real database.
   */
  @AfterAll
  public static void tearDown() {
    HRDatabaseFacade.setTestMode(null);
  }
}
