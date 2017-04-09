package com.jikim.Controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderCookieController {

    @GetMapping("/cookies")
    public String getCookieData (@CookieValue(name="SessionID") String cookie) {
        return cookie;
    }

    @GetMapping("/header")
    public String getHeaderData (@RequestHeader String host) {
        return host;
    }
}
