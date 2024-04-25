package com.adil.universitymanagement.controller;

import com.adil.universitymanagement.bean.CourseBean;
import com.adil.universitymanagement.entity.Course;
import com.adil.universitymanagement.entity.CourseIdsRequest;
import com.adil.universitymanagement.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseBean>> getAllCourses(){
        List<CourseBean> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseBean> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            CourseBean courseBean = new CourseBean(course);
            return new ResponseEntity<>(courseBean, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping
    public ResponseEntity<CourseBean> addCourse(@RequestBody CourseBean courseBean){
       courseService.addCourse(courseBean);
       return new ResponseEntity<>(courseBean,HttpStatus.OK);
    }

    @PutMapping("/update-course")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }

    @PostMapping("/assign-teacher")
    public ResponseEntity<CourseBean> assignTeacherToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long teacherId){
        CourseBean assignedCourse = courseService.assignTeacherToCourse(courseIdsRequest.getCourseIds(),teacherId);
        return new ResponseEntity<>(assignedCourse,HttpStatus.OK);
    }

    @PostMapping("/enroll-student")
    public ResponseEntity<Course> enrollStudentToCourse(@RequestBody CourseIdsRequest courseIdsRequest, @RequestParam Long studentId){
        Course enrolledStudent = courseService.enrollStudentToCourse(courseIdsRequest.getCourseIds(),studentId);
        return new ResponseEntity<>(enrolledStudent,HttpStatus.OK);
    }

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long teacherId ){
        List<Course> courses = courseService.findCourseByTeacher(teacherId);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<Course>> getCoursesByStudents(@PathVariable Long studentId){
        List<Course> courses = courseService.findCoursesByStudent(studentId);
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }
}
