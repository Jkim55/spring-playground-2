package com.jikim.unit_5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    LessonRepository repository;


    @Test
    @Transactional
    @Rollback
    public void itCanCreateANewEntry() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Spring Boot\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", equalTo("Spring Boot")));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanReadAnEntry() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring Showers");
        repository.save(lesson);
        String lessonId = lesson.getId().toString();;

        MockHttpServletRequestBuilder request = get("/lessons/" + lessonId)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", equalTo("Spring Showers")));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanUpdateAnEntry() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring Showers");
        repository.save(lesson);
        String lessonId = lesson.getId().toString();

        MockHttpServletRequestBuilder request = patch("/lessons/" + lessonId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Spring Flurries\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", equalTo("Spring Flurries")));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanDeleteAnEntry() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring Flurries");
        repository.save(lesson);
        String lessonId = lesson.getId().toString();

        MockHttpServletRequestBuilder request = delete("/lessons/" + lessonId)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanListAllEntries() throws Exception {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("JPA");
        repository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("notJPA");
        repository.save(lesson2);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id", instanceOf(Number.class)))
                .andExpect(jsonPath("$[1].id", equalTo(lesson2.getId().intValue())))
                .andExpect(jsonPath("$[1].title", equalTo(lesson2.getTitle())));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanFindAnEntryByTitle() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Spring Showers");
        repository.save(lesson);
        String lessonTitle = lesson.getTitle();;

        MockHttpServletRequestBuilder request = get("/lessons/find/" + lessonTitle)
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(lesson.getId().intValue())))
                .andExpect(jsonPath("$[0].title", equalTo("Spring Showers")));
    }

    @Test
    @Transactional
    @Rollback
    public void itCanFindAnEntryByDateRange() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("JPA");
        lesson1.setDeliveredOn(df.parse("2015-09-01"));
        repository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("notJPA");
        lesson2.setDeliveredOn(df.parse("2017-01-29"));
        repository.save(lesson2);

        MockHttpServletRequestBuilder request = get("/lessons/between?date1=2014-01-01&date2=2017-12-31")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id", equalTo(lesson2.getId().intValue())))
                .andExpect(jsonPath("$[1].title", equalTo("notJPA")))
                .andExpect(jsonPath("$[1].deliveredOn", equalTo("2017-01-29")));
    }

}
