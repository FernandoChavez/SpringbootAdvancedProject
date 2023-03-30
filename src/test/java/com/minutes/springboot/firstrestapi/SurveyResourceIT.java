package com.minutes.springboot.firstrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
	
	//http://localhost:8080/surveys/Survey1/questions/Question1
	String str = """
	{
        "id": "Question1",
        "description": "Most Popular Cloud Platform Today",
        "options": [
            "AWS",
            "Azure",
            "Google Cloud",
            "Oracle Cloud"
        ],
        "correctAnswer": "AWS"
    }
			""";
	
	private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
	
	//surveys/survey1/questions/Question1
	@Autowired
	TestRestTemplate template;
	
	/*
	 {
		  "id" : "Question1",
		  "description" : "Most Popular Cloud Platform Today",
		  "options" : [ "AWS", "Azure", "Google Cloud", "Oracle Cloud" ],
		  "correctAnswer" : "AWS"
		}
		[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sun, 26 Mar 2023 10:47:03 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
		 
	 */
	
	@Test
	void RetrieveSpecificSurveyQuestion_basicScenario() {
		ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());
	}
}
