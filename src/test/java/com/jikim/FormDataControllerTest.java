package com.jikim;

import com.jikim.Controller.FormDataController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataController.class)
public class FormDataControllerTest {
    @Autowired
    private MockMvc mvc;

    public void postURLEncodedFormData () throws Exception {

    }
}
