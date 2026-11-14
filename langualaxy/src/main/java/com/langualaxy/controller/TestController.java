package com.langualaxy.controller;

import com.langualaxy.dto.QuestionDTO;
import com.langualaxy.dto.TestDTO;
import com.langualaxy.dto.TestResult;
import com.langualaxy.request.TestCreationRequest;
import com.langualaxy.request.TestSubmitRequest;
import com.langualaxy.request.TestUpdateRequest;
import com.langualaxy.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("")
    public ResponseEntity<TestDTO> createTest(@RequestBody TestCreationRequest testCreationRequest) {
        TestDTO createdTest = testService.createTest(testCreationRequest);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<TestDTO>> getAllTests() {
        List<TestDTO> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("get/{testId}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long testId) {
        TestDTO test = testService.getTestById(testId);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("edit/{testId}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Long testId, @RequestBody TestUpdateRequest request) {
        TestDTO test = testService.updateTest(testId, request);
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
