package com.minutes.springboot.firstrestapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public 	class SurveyResource {
	
	
	private SurveyService surveyService;
	
	
	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}




	//surveys => surveys
	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys(){
		return surveyService.retrieveAllSurveys();
	}
	
	@RequestMapping("/surveys/{surveyId}")
	public Survey retrieveAllSurveys(@PathVariable String surveyId){
		Survey survey = surveyService.retrieveSurveyById(surveyId);
		
		if(survey==null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;
	}
	
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveAllSurveysQuestions(@PathVariable String surveyId){
		List<Question> questions = surveyService.retrieveAllSurveysQuestions(surveyId);
		
		if(questions==null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return questions;
	}
	
	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSpecifictSurveysQuestions(@PathVariable String surveyId, @PathVariable String questionId){
		
		Question question = surveyService.retrieveQuestionById(surveyId, questionId);
		
		if(question==null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return question;
	}
	
	/*
	@RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
	public void addNewSurveysQuestions(@PathVariable String surveyId, @RequestBody Question question){
		surveyService.addNewSurveyQuestion(surveyId, question);
	}*/
	
	@RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
	public ResponseEntity<Object> addNewSurveysQuestions(@PathVariable String surveyId, 
			@RequestBody Question question){
		
		String questionId= surveyService.addNewSurveyQuestion(surveyId, question);
		// /survey/{surveyId}/questions/{questionId}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{questionId}").buildAndExpand(questionId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSurveysQuestions(@PathVariable String surveyId, @PathVariable String questionId){
		
		surveyService.deleteSurveyQuestion(surveyId, questionId);
		return ResponseEntity.noContent().build();
	}
	
}
