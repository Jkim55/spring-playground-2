package com.jikim;

import com.jikim.Controller.CalculatorController;
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
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void canReturnValueofPi () throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void canCalculateAdditions () throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void canCalculateSubtractions () throws Exception {
        RequestBuilder subtractRequest = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");

        this.mvc.perform(subtractRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void canCalculateMultiplications () throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void canCalculateDivisions () throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void hasDefaultOperationOfAddition () throws Exception {
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/math/calculate?x=30&y=5");

        this.mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void canSumMultipleDigits () throws Exception {
        RequestBuilder postRequest = MockMvcRequestBuilders.post("/math/sum?n=4&n=5&n=6");

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void canfindVolumeWithPost () throws Exception {
        int l = 3;
        int w = 4;
        int h = 5;
        RequestBuilder postRequest = MockMvcRequestBuilders.post(String.format("/math/volume/%d/%d/%d", l, w, h));

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void canfindVolumeWithPatch () throws Exception {
        int l = 3;
        int w = 4;
        int h = 5;
        RequestBuilder patchRequest = MockMvcRequestBuilders.patch(String.format("/math/volume/%d/%d/%d", l, w, h));

        this.mvc.perform(patchRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }
}
