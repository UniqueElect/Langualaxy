package com.langualaxy.Controller;

import com.langualaxy.Dtos.QuestionDTO;
import com.langualaxy.Dtos.TestResult;
import com.langualaxy.Entity.Test;
import com.langualaxy.Request.TestCreationRequest;
import com.langualaxy.Request.TestSubmitRequest;
import com.langualaxy.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("")
    public ResponseEntity<Test> createTest(@RequestBody TestCreationRequest testCreationRequest) {
        Test createdTest = testService.createTest(testCreationRequest);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("get/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable Long testId) {
        Test test = testService.getTestById(testId);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("edit/{testId}")
    public ResponseEntity<Test> updateTest(@PathVariable Long testId, @RequestBody Test updatedTest) {
        Test test = testService.updateTest(testId, updatedTest);
        return new ResponseEntity<>(test, HttpStatus.OK);

    }

    @GetMapping("delete/{testId}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{testId}/start")
    public ResponseEntity<List<QuestionDTO>> startTest(@PathVariable Long testId) {
        List<QuestionDTO> selectedQuestions = testService.startTest(testId, 10);
        return new ResponseEntity<>(selectedQuestions, HttpStatus.OK);
    }

    @PostMapping("/{testId}/submit")
    public ResponseEntity<TestResult> submitTest(@PathVariable Long testId, @RequestBody TestSubmitRequest answers) {
        TestResult testResult = testService.submitTest(testId, answers);
        return new ResponseEntity<>(testResult, HttpStatus.OK);
    }
}
