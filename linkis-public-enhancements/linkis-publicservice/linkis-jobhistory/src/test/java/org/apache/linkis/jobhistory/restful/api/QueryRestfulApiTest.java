package org.apache.linkis.jobhistory.restful.api;

import org.apache.linkis.LinkisBaseServerApp;
import org.apache.linkis.common.utils.JsonUtils;
import org.apache.linkis.jobhistory.Scan;
import org.apache.linkis.jobhistory.WebApplicationServer;
import org.apache.linkis.jobhistory.dao.JobDetailMapper;
import org.apache.linkis.jobhistory.service.JobHistoryQueryService;
import org.apache.linkis.server.Message;
import org.apache.linkis.server.MessageStatus;
import org.apache.linkis.server.utils.LinkisMainHelper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * QueryRestfulApi Tester
 */
@ActiveProfiles(value = {"h22"})
@SpringBootTest(classes = {WebApplicationServer.class,Scan.class})
@AutoConfigureMockMvc
public class QueryRestfulApiTest {


    private static final Logger logger = LoggerFactory.getLogger(QueryRestfulApiTest.class);

    @Autowired
    protected MockMvc mockMvc;

    @MockBean(name = "jobHistoryQueryService")
    private JobHistoryQueryService jobHistoryQueryService;

    @MockBean(name = "jobDetailMapper")
    private JobDetailMapper jobDetailMapper;

    @BeforeAll
    @DisplayName("Each unit test method is executed once before execution")
    protected static void beforeAll() throws Exception {
//        System.getProperties().setProperty(LinkisMainHelper.SERVER_NAME_KEY(), "linkis-ps-publicservice");
//        LinkisBaseServerApp.main(new String[]{});
        //new SpringApplicationBuilder(QueryRestfulApiTest.class).run("--server.port=2222");
        logger.info("start linkis-ps-publicservice servive");
    }

    @AfterAll
    @DisplayName("Each unit test method is executed once before execution")
    protected static void afterAll() throws Exception {
    }

    @Test
    @DisplayName("")
    public void testGovernanceStationAdmin() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/jobhistory/governanceStationAdmin"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        Message res=JsonUtils.jackson().readValue(mvcResult.getResponse().getContentAsString(), Message.class);
        Assert.assertEquals(res.getStatus(), MessageStatus.SUCCESS());

        logger.info(mvcResult.getResponse().getContentAsString());

    }


    @Test
    public void testGetTaskByID() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/jobhistory/{id}/get",123))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Message res=JsonUtils.jackson().readValue(mvcResult.getResponse().getContentAsString(), Message.class);
        Assert.assertEquals(res.getStatus(), MessageStatus.ERROR());
        logger.info(mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void testList() throws Exception {
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("startDate",String.valueOf(System.currentTimeMillis()));
        paramsMap.add("endDate",String.valueOf(System.currentTimeMillis()));
        paramsMap.add("status","1");
        paramsMap.add("pageNow","1");
        paramsMap.add("pageSize","15");
        paramsMap.add("taskID","123");
        paramsMap.add("executeApplicationName","test_name");
        paramsMap.add("proxyUser",null);

        MvcResult mvcResult = mockMvc.perform(get("/jobhistory/list")
                .params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        logger.info(mvcResult.getResponse().getContentAsString());
    }


} 
