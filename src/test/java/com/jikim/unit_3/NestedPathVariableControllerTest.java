package com.jikim.unit_3;

import com.jikim.unit_3.Controller.NestedPathVariableController;
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
@WebMvcTest(NestedPathVariableController.class)
public class NestedPathVariableControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getIndex() throws Exception {
        int pId = 54;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/task/%d/comments/", pId));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 54"));
    }

    @Test
    public void getNestedPathVariableAsInd() throws Exception {
        int pId = 54;
        int cId = 2;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/task/%d/comments/individual/%d", pId, cId));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 54 & commentId is 2"));
    }

    @Test
    public void getNestedPathVariableAsMap() throws Exception {
        int pId = 5;
        int cId = 123;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/task/%d/comments/map/%d", pId, cId));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("{taskId=5, commentId=123}"));
    }

    @Test
    public void getNestedPathVariablesAsObject() throws Exception {
        int pId = 731;
        int cId = 41;
        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/task/%d/comments/object/%d", pId, cId));

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 731; commentId is 41"));
    }
}
