package com.langualaxy.Controller;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Request.QuestionCreationRequest;
import com.langualaxy.Request.QuestionUpdateRequest;
import com.langualaxy.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionCreationRequest request) {
        QuestionDTO createdQuestion = questionService.createQuestion(request);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("get/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long questionId) {
        QuestionDTO question = questionService.getQuestionById(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("edit/{questionId}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionUpdateRequest request) {
        QuestionDTO question = questionService.updateQuestion(questionId, request);
        return new ResponseEntity<>(question, HttpStatus.OK);

    }

    @GetMapping("delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
