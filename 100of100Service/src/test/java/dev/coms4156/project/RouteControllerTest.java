package dev.coms4156.project;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  @Test
  public void testGetEmployeeInfo() throws Exception {
    MvcResult mvcResult1 = mockMvc.perform(get("/getEmpInfo")
        .param("cid", "1")
        .param("eid", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    String content1 = mvcResult1.getResponse().getContentAsString();
    System.out.println("Response Content: " + content1);
  }
}
