package com.jikim.Controller;

import com.jikim.Model.Search;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fd")
public class FormDataController {

    @PostMapping("/individual-example")
    public String getIndividualParams(@RequestParam("t") String term, @RequestParam String from){
        return String.format("%s searched from %s", term, from);
    }

    @PostMapping(value="map-example", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getMapParams(@RequestParam Map<String, String> formData){
        return formData.toString();
    }

    @PostMapping(value = "/object-example", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getObjectParams(Search search){
        return String.format("%s is available in %s", search.getStyle(), search.getSize());
    }

    @PostMapping("/string-example")
    public String getRawString(@RequestBody String rawBody) {
        return rawBody;
    }

}
