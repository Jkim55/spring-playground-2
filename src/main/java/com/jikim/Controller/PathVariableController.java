package com.jikim.Controller;

import com.jikim.Service.TaskIds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathVariableController {

    @GetMapping("/example/{query}") // pattern matches: "/individual-example/gluten-free-cupcakes/pinterest"
    public String getIndividualParams(@PathVariable String query) {
        return String.format("query term is %s", query);
    }

    @GetMapping("/tasks/{tID}/comments/{cID}")
    public String getCommentsForTask(@PathVariable("tID") int taskId, @PathVariable("cID") int commentId) {
        return String.format("taskId is %d; commentId is %d", taskId, commentId);
    }

    @GetMapping("/tasks/{tID}/comments/{cID}")
    public String getPathVariablesAsAMap(@PathVariable Map pathVariables) {
        return pathVariables.toString(); // {tID=46, cID=35}
    }

    @GetMapping("/test/tasks/{taskId}/comments/{commentId}")
    public String getPathVariablesFromObject(TaskIds ids) {
        return String.format("taskId is %d; commentId is %s", ids.getTaskId(), ids.getCommentId());
    }
}
