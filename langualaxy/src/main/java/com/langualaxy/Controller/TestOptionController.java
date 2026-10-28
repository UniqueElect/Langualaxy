package com.langualaxy.Controller;

import com.langualaxy.Dtos.TestOptionDTO;
import com.langualaxy.Entity.TestOption;
import com.langualaxy.Service.TestOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-options")
public class TestOptionController {

    @Autowired
    private TestOptionService testOptionService;

    @PostMapping("")
    public ResponseEntity<TestOptionDTO> createTestOption(@RequestBody TestOption testOption) {
        TestOptionDTO createdTestOption = testOptionService.createTestOption(testOption);
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
    public ResponseEntity<TestOptionDTO> updateTestOption(@PathVariable Long testOptionId, @RequestBody TestOption updatedTestOption) {
        TestOptionDTO testOption = testOptionService.updateTestOption(testOptionId, updatedTestOption);
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
