package com.jikim.unit_3;

import com.jikim.unit_3.Controller.CalculatorController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        RequestBuilder postRequest = post("/math/sum?n=4&n=5&n=6");

        this.mvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void canfindVolumeWithPost () throws Exception {
        int l = 3;
        int w = 4;
        int h = 5;
        RequestBuilder postRequest = post(String.format("/math/volume/%d/%d/%d", l, w, h));

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

    @Test
    public void canCalculateAreaForCircle () throws Exception {
        String type = "circle";
        String radius = "4";
        String area = "50.26";

        MockHttpServletRequestBuilder requestCircle = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", type)
                .param("radius", radius);

        this.mvc.perform(requestCircle)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("A circle with a radius of %s has an area of %s", radius, area)));
    }

    @Test
    public void canCalculateAreaForRectangle () throws Exception {
        String type = "rectangle";
        String width = "4";
        String height = "5";
        String area = "20";

        MockHttpServletRequestBuilder requestCircle = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", type)
                .param("width", width)
                .param("height", height);

        this.mvc.perform(requestCircle)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("A rectangle with a dimension of %s x %s has an area of %s", width, height, area)));
    }

    @Test
    public void canThrowAnInvalidForMismatches () throws Exception {
        String type = "rectangle";
        String radius = "4";

        MockHttpServletRequestBuilder requestCircle = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", type)
                .param("radius", radius);

        this.mvc.perform(requestCircle)
                .andExpect(status().isOk())
                .andExpect(content().string("invalid"));
    }
}
