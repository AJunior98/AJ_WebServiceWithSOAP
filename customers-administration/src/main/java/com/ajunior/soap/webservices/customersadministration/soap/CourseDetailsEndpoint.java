package com.ajunior.soap.webservices.customersadministration.soap;
import com.ajunior.courses.*;
import com.ajunior.soap.webservices.customersadministration.soap.bean.Course;
import com.ajunior.soap.webservices.customersadministration.soap.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    //method
    //input - GetCourseDetailsRequest
    //output - GetCourseDetailsResponse

    //"http://ajunior.com/courses"
    //GetCourseDetailsRequest

    @PayloadRoot(namespace = "http://ajunior.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

        Course course = service.findById(request.getId());

        return mapCourseDetails(course);
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course : courses) {
            CourseDetails mapCourse = mapCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());

        courseDetails.setName(course.getName());

        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    @PayloadRoot(namespace = "http://ajunior.com/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
            @RequestPayload GetAllCourseDetailsRequest request) {

        List<Course> courses = service.findAll();

        return mapAllCourseDetails(courses);
    }

    @PayloadRoot(namespace = "http://ajunior.com/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

        CourseDetailsService.Status status = service.deleteById(request.getId());

        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));

        return response;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if (status== CourseDetailsService.Status.FAILURE)
            return Status.FAILURE;
        return Status.SUCCESS;

    }

}