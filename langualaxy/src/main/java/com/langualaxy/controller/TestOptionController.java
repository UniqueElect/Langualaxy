package com.langualaxy.controller;

import com.langualaxy.dto.TestOptionDTO;
import com.langualaxy.request.OptionCreationRequest;
import com.langualaxy.request.OptionUpdateRequest;
import com.langualaxy.service.TestOptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-options")
public class TestOptionController {

    private final TestOptionService testOptionService;

    public TestOptionController(TestOptionService testOptionService) {
        this.testOptionService = testOptionService;
    }

    @PostMapping("")
    public ResponseEntity<TestOptionDTO> createTestOption(@Valid @RequestBody OptionCreationRequest request) {
        TestOptionDTO createdTestOption = testOptionService.createTestOption(request);
        return new ResponseEntity<>(createdTestOption, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestOptionDTO>> getAllTestOptions() {
        List<TestOptionDTO> testOptions = testOptionService.getAllTestOptions();
        return new ResponseEntity<>(testOptions, HttpStatus.OK);
    }

    @GetMapping("get/{testOptionId}")
    public ResponseEntity<TestOptionDTO> getTestOptionById(@PathVariable Long testOptionId) {
        TestOptionDTO testOption = testOptionService.getTestOptionById(testOptionId);
        return new ResponseEntity<>(testOption, HttpStatus.OK);
    }

    @PostMapping("edit/{testOptionId}")
    public ResponseEntity<TestOptionDTO> updateTestOption(@PathVariable Long testOptionId
            , @Valid @RequestBody OptionUpdateRequest request) {
        TestOptionDTO testOption = testOptionService.updateTestOption(testOptionId, request);
        return new ResponseEntity<>(testOption, HttpStatus.OK);

    }

    @GetMapping("/delete/{testOptionId}")
    public ResponseEntity<Void> deleteTestOption(@PathVariable Long testOptionId) {
        testOptionService.deleteTestOption(testOptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<TestOptionDTO>> getAllTestOptionsByQuestionId(@PathVariable Long questionId) {
        List<TestOptionDTO> testOptions = testOptionService.getAllTestOptionsByQuestionId(questionId);
        return new ResponseEntity<>(testOptions, HttpStatus.OK);
    }
}
