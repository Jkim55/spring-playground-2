package com.jikim.Controller;

import com.jikim.Service.TaskIds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathVariableController {

    @GetMapping("/example/{q}/{from}")
    public String getIndividualParams(@PathVariable("q") String query, @PathVariable String from) {
        return String.format("query term is %s from %s", query, from);
    }

    @GetMapping("individual/tasks/{tID}/comment/{cID}")
    public String getCommentsForTask(@PathVariable("tID") int taskId, @PathVariable("cID") int commentId) {
        return String.format("taskId is %d; commentId is %d", taskId, commentId);
    }

    @GetMapping("/map/tasks/{tID}/comment/{cID}")
    public String getPathVariablesAsAMap(@PathVariable Map pathVariables) {
        return pathVariables.toString(); // {tID=46, cID=35}
    }

    @GetMapping("/object/tasks/{taskId}/comment/{commentId}")
    public String getPathVariablesFromObject(TaskIds ids) {
        return String.format("taskId is %d; commentId is %s", ids.getTaskId(), ids.getCommentId());
    }
}
