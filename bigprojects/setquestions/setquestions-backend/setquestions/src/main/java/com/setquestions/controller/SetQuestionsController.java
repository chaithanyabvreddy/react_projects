package com.setquestions.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.setquestions.SetQuestionsForm;
import com.setquestions.service.SetQuestionsService;

@RestController
public class SetQuestionsController {

	@Autowired
	public SetQuestionsService setQuestionsService;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getAllSetQuestions", method = RequestMethod.GET)
	public List<SetQuestionsForm> getAll() {
		List<SetQuestionsForm> setQuestionList =	setQuestionsService.getAll();
		return setQuestionList;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadNewFile(@NotNull @RequestParam("file") MultipartFile multipartFile) throws IOException {
		setQuestionsService.uploadFileDetailsToDB(multipartFile);
	}
}
