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
    public void getIndividualParamsTest() throws Exception {
//        String query = "bull-doggies";
//        String from = "instagram";
//        RequestBuilder getRequest = MockMvcRequestBuilders.get(String.format("/individual-example/%d/%d", query, from));

        RequestBuilder getRequest = MockMvcRequestBuilders.get("/individual-example/bull-doggies/instagram");
        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("query term is bull-doggies from instagram"));

    }

}
