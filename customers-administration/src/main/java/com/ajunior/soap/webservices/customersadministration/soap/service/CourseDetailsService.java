package com.ajunior.soap.webservices.customersadministration.soap.service;

import com.ajunior.soap.webservices.customersadministration.soap.bean.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1,  "Spring", "10 Steps");
        courses.add(course1);

        Course course2 = new Course(2,  "Spring MVC", "10 Examples");
        courses.add(course2);

        Course course3 = new Course(3,  "Spring Boot", "6K Students");
        courses.add(course3);

        Course course4 = new Course(4,  "Spring Maven", "Most popular maven course");
        courses.add(course4);
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }

    public List<Course> findAll(){
        return courses;
    }

    public int deleteById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == id) {
                courses.remove(course);
                return 1;
            }
        }
        return 0;
    }
}
