package com.setquestions.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.setquestions.SetQuestions;
import com.setquestions.SetQuestionsForm;
import com.setquestions.dao.SetQuestionsRepository;

@Service
public class SetQuestionsService {

	@Autowired
	public SetQuestionsRepository setQuestionsRepository;

	public List<SetQuestionsForm> getAll() {
		List<SetQuestionsForm> setQuestionsFormList = new ArrayList<SetQuestionsForm>();
		Iterable<SetQuestions> setQuestions = setQuestionsRepository.findAll();
		int i = 0;

		for (SetQuestions setQuestions1 : setQuestions) {
			SetQuestionsForm setQuestionsForm = new SetQuestionsForm();
			setQuestionsForm.setQuestionid(i);
			i = i + 1;
			BeanUtils.copyProperties(setQuestions1, setQuestionsForm);
			setQuestionsFormList.add(setQuestionsForm);
		}
		return setQuestionsFormList;
	}

	public void uploadFileDetailsToDB(MultipartFile multipartFile) throws IOException {
		List<SetQuestions> setQuestionsList = getSetQuestions(multipartFile);
		
		setQuestionsRepository.deleteAll();
		
		for (SetQuestions temp : setQuestionsList) {
			setQuestionsRepository.save(temp);
		}

	}

	public List<SetQuestions> getSetQuestions(MultipartFile multipartFile) throws IOException {
		List<SetQuestions> setQuestionsList = new ArrayList<SetQuestions>();

		InputStream inputStream = multipartFile.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		String enterFile = "";
		String st;
		while ((st = br.readLine()) != null) {
			enterFile = enterFile + st + "\n";
		}
		System.out.println(enterFile);

		String[] questanswerList = enterFile.split("-------------------------------------------------------");
		for (String temp : questanswerList) {
			String[] questionanswer = temp.split("ans\\)");
			String question = questionanswer[0];
			String answer = questionanswer[1];
			SetQuestions setQuestions = new SetQuestions();
			setQuestions.setQuestionName(question);
			setQuestions.setCorrectAnswer(answer);
			setQuestionsList.add(setQuestions);
		}
		return setQuestionsList;
	}

}
