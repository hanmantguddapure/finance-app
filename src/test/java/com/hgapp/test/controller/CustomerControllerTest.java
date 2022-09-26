/*
 * package com.hgapp.test.controller;
 * 
 * import static org.junit.Assert.assertEquals;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.MvcResult; import
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 * 
 * import com.hgapp.config.AppStarter; import com.hgapp.utils.JwtTokenUtil;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(classes = AppStarter.class)
 * 
 * @AutoConfigureMockMvc public class CustomerControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Autowired private JwtTokenUtil jwtTokenUtil; String jwtToken = null;
 * 
 * @Before public void setUp() { jwtToken = "Bearer " +
 * jwtTokenUtil.generateToken("Hanmant"); }
 * 
 * @Test public void findAdd() throws Exception { MvcResult mvcResult =
 * mockMvc.perform(MockMvcRequestBuilders.get("/customer/find-all")
 * .header("Authorization", jwtToken).header("Origin", "*")).andReturn(); int
 * status = mvcResult.getResponse().getStatus(); assertEquals(status, 202);
 * 
 * }
 * 
 * }
 */