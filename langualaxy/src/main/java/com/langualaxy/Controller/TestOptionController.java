package com.langualaxy.Controller;

import com.langualaxy.Entity.TestOption;
import com.langualaxy.Service.TestOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-options")
public class TestOptionController {

    @Autowired
    private TestOptionService testOptionService;
    @PostMapping("")
    public ResponseEntity<TestOption> createTestOption(@RequestBody TestOption testOption) {
        TestOption createdTestOption = testOptionService.createTestOption(testOption);
        return new ResponseEntity<>(createdTestOption, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TestOption>> getAllTestOptions() {
        List<TestOption> testOptions = testOptionService.getAllTestOptions();
        return new ResponseEntity<>(testOptions, HttpStatus.OK);
    }
    @GetMapping("get/{testOptionId}")
    public ResponseEntity<TestOption> getTestOptionById(@PathVariable Long testOptionId) {
        Optional<TestOption> testOption = testOptionService.getTestOptionById(testOptionId);
        return testOption.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("edit/{testOptionId}")
    public ResponseEntity<TestOption> updateTestOption(@PathVariable Long testOptionId, @RequestBody TestOption updatedTestOption) {
        TestOption testOption = testOptionService.updateTestOption(testOptionId, updatedTestOption);
        if (testOption != null) {
            return new ResponseEntity<>(testOption, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/delete/{testOptionId}")
    public ResponseEntity<Void> deleteTestOption(@PathVariable Long testOptionId) {
        testOptionService.deleteTestOption(testOptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<TestOption>> getAllTestOptionsByQuestionId(@PathVariable Long questionId) {
        List<TestOption> testOptions = testOptionService.getAllTestOptionsByQuestionId(questionId);
        return new ResponseEntity<>(testOptions, HttpStatus.OK);
    }
}
