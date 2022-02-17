package org.apache.linkis.jobhistory.restful.api;

import org.apache.linkis.LinkisBaseServerApp;
import org.apache.linkis.jobhistory.dao.JobDetailMapper;
import org.apache.linkis.jobhistory.service.JobHistoryQueryService;
import org.apache.linkis.server.utils.LinkisMainHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@SpringBootTest
@AutoConfigureMockMvc
public class QueryRestfulApiTest2 {


    private static final Logger logger = LoggerFactory.getLogger(QueryRestfulApiTest2.class);

    @Autowired
    protected MockMvc mockMvc;


    @MockBean(name = "jobHistoryQueryService")
    private JobHistoryQueryService jobHistoryQueryService;

    @MockBean(name = "jobDetailMapper")
    private JobDetailMapper jobDetailMapper;

    @BeforeAll
    @DisplayName("Each unit test method is executed once before execution")
    protected static void beforeAll() throws Exception {
        System.getProperties().setProperty(LinkisMainHelper.SERVER_NAME_KEY(), "linkis-ps-publicservice");
        LinkisBaseServerApp.main(new String[]{});
        logger.info("start linkis-ps-publicservice servive");
    }

    @AfterAll
    @DisplayName("Each unit test method is executed once before execution")
    protected static void afterAll() throws Exception {
    }

    @Test
    @DisplayName("Method description: ...")
    public void testGovernanceStationAdmin() throws Exception {
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("processDefinitionCode", "40");
        paramsMap.add("schedule", "{'startTime':'2019-12-16 00:00:00','endTime':'2019-12-17 00:00:00','crontab':'0 0 6 * * ? *'}");
        paramsMap.add("warningGroupId", "1");
        paramsMap.add("receivers", "");
        paramsMap.add("receiversCc", "");
        paramsMap.add("workerGroupId", "1");

//        Mockito.when(jobDetailMapper.insertJobDetail(isA(JobDetail.class))).thenReturn(Long.valueOf(0));

        MvcResult mvcResult = mockMvc.perform(get("/jobhistory/governanceStationAdmin")
                .params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getResponse().getContentAsString();
        logger.info(mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Method description: ...")
    public void testGetTaskByID() throws Exception {
        //TODO: Test goes here... 
    }


    @Test
    @DisplayName("Method description: ...")
    public void testList() throws Exception {
        //TODO: Test goes here...
        System.out.println("hello world!");
//        for (;;)
//        {
//
//        }
    }


} 
