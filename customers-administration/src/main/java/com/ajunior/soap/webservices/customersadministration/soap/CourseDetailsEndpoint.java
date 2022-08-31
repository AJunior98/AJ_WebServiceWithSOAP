package com.ajunior.soap.webservices.customersadministration.soap;

import com.ajunior.courses.CourseDetails;
import com.ajunior.courses.GetCourseDetailsReponse;
import com.ajunior.courses.GetCourseDetailsRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    //method
    //input - GetCourseDetailsRequest
    //output - GetCourseDetailsResponse

    //"http://ajunior.com/courses"
    //GetCourseDetailsRequest

    @PayloadRoot(namespace = "http://ajunior.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsReponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsReponse response = new GetCourseDetailsReponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("That would be a wonderful course!");

        response.setCourseDetails(courseDetails);

        return response;
    }

}
