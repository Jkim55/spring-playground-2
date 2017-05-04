package com.jikim.unit_5;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List <Lesson> findByTitle(String title);

    String sql = "SELECT * FROM lessons WHERE delivered_on BETWEEN :date1 AND :date2";
    @Query(value = sql, nativeQuery = true)
    public List<Lesson> findLessonBetweenDates(@Param("date1") Date date1, @Param("date2") Date date2);}