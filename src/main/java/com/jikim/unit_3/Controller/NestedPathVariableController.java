package com.jikim.unit_3.Controller;

import com.jikim.unit_3.Model.TaskIds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("task/{taskId}/comments")
public class NestedPathVariableController {
    @GetMapping("/") // <-- will match /tasks/34/comments/
    public String getComments(@PathVariable int taskId) {
        return String.format("taskId is %d", taskId);
    }

    @GetMapping("/individual/{commentId}") // <-- will match /tasks/34/comments/9
    public String getComment(@PathVariable int taskId, @PathVariable int commentId) {
        return String.format("taskId is %d & commentId is %d", taskId, commentId);
    }

    @GetMapping("/map/{commentId}")
    public String getPathVariablesAsAMap(@PathVariable Map pathVariables) {
        return pathVariables.toString(); // {taskId=46, commentId=35}
    }

    @GetMapping("/object/{commentId}")
    public String getPathVariablesFromObject(TaskIds ids) {
        return String.format("taskId is %d; commentId is %d", ids.getTaskId(), ids.getCommentId());
    }
}
