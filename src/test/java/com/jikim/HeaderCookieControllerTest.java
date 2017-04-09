package com.jikim;

import com.jikim.Controller.HeaderCookieController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HeaderCookieController.class)
public class HeaderCookieControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void canAccessCookieData () throws Exception {
        String sId = String.valueOf(new Random().nextInt());

        final Cookie sessionCookie = new Cookie("SessionID", sId);
        RequestBuilder getRequestForCookies = MockMvcRequestBuilders.get("/cookies").cookie(sessionCookie);

        this.mvc.perform(getRequestForCookies)
                .andExpect(status().isOk())
                .andExpect(content().string(sId));
    }

    @Test
    public void canAccessHeaderData () throws Exception {
        RequestBuilder getRequestForHeader = MockMvcRequestBuilders.get("/header").header("Host", "jikim.me");

        this.mvc.perform(getRequestForHeader)
                .andExpect(status().isOk())
                .andExpect(content().string("jikim.me"));
    }
}
