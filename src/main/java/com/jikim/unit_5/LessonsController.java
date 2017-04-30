package com.jikim.unit_5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public ResponseEntity readById(@PathVariable("id") Long id) {
        if (this.repository.findOne(id) != null) {
            Lesson lesson = this.repository.findOne(id);
            return new ResponseEntity(lesson, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{title}")
    public ResponseEntity readByTitle(@PathVariable("title") String title) {
        if (this.repository.findByTitle(title) != null) {
            List<Lesson> lesson = this.repository.findByTitle(title);
            return new ResponseEntity(lesson, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/between")
    public ResponseEntity readByDateRange(@RequestParam String date1, @RequestParam String date2) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Lesson> lessons = this.repository.findLessonBetweenDates(df.parse(date1), df.parse(date2));
        return new ResponseEntity(lessons, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Lesson lesson){
        if (this.repository.findOne(id) != null) {
            Lesson lessonToEdit = this.repository.findOne(id);
            lessonToEdit.setTitle(lesson.getTitle());
            lessonToEdit.setDeliveredOn(lesson.getDeliveredOn());
            this.repository.save(lessonToEdit);
            return new ResponseEntity(lessonToEdit, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (this.repository.findOne(id) != null) {
            this.repository.delete(id);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

}