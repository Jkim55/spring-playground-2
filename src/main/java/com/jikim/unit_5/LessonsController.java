package com.jikim.unit_5;

import com.jikim.unit_4.Service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public ResponseEntity read(@PathVariable("id") Long id) {
        if (this.repository.findOne(id) != null) {
            Lesson found = this.repository.findOne(id);
            return new ResponseEntity(found, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-title/{title}")
    public ResponseEntity readByTitle(@PathVariable("title") String title) {
        if (this.repository.findByTitle(title) != null) {
            Lesson found = this.repository.findByTitle(title);
            return new ResponseEntity(found, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Lesson lesson){
        if (this.repository.findOne(id) != null) {
            Lesson lessonToEdit = this.repository.findOne(id);
            lessonToEdit.setTitle(lesson.getTitle());
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