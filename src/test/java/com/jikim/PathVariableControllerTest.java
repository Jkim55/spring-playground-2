package com.jikim;

import com.jikim.Controller.PathVariableController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PathVariableController.class)
public class PathVariableControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getParams() throws Exception {
        String query = "bull-doggies";
        String from = "instagram";
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/example/%s/%s", query, from));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("query term is bull-doggies from instagram"));
    }

    @Test
    public void getIndividualParams() throws Exception {
        int taskID = 16792;
        int commentID = 18;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/individual/tasks/%d/comment/%d", taskID , commentID));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 16792; commentId is 18"));
    }

    @Test
    public void getParamsAsMap() throws Exception {
        int taskID = 192;
        String commentID = "1";
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/map/tasks/%d/comment/%s", taskID , commentID));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("{tID=192, cID=1}"));
    }

    @Test
    public void getParamsAsObject() throws Exception {
        int taskID = 167;
        int commentID = 8;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/object/tasks/%d/comment/%d", taskID , commentID));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 167; commentId is 8"));
    }


}
