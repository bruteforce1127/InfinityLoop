package com.kucp1127.KidsLearningApp.Practice.Controller;

import com.kucp1127.KidsLearningApp.Practice.Model.PracticeQuestionModel;
import com.kucp1127.KidsLearningApp.Practice.Service.PracticeQuestionModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practice")
@CrossOrigin(origins = "*")
public class PracticeQuestionModelController {

    @Autowired
    private PracticeQuestionModelService service;

    // Create a new Practice Question
    @PostMapping
    public ResponseEntity<PracticeQuestionModel> createPracticeQuestion(@RequestBody PracticeQuestionModel model) {
        PracticeQuestionModel savedModel = service.save(model);
        return ResponseEntity.ok(savedModel);
    }

    // Get by grade
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<PracticeQuestionModel>> getByGrade(@PathVariable int grade) {
        return ResponseEntity.ok(service.getByGrade(grade));
    }

    // Get by grade and category
    @GetMapping("/grade/{grade}/category/{category}")
    public ResponseEntity<List<PracticeQuestionModel>> getByGradeAndCategory(
            @PathVariable int grade,
            @PathVariable String category
    ) {
        return ResponseEntity.ok(service.getByGradeAndCategory(grade, category));
    }

    // Get by grade, category, and subCategory
    @GetMapping("/grade/{grade}/category/{category}/subCategory/{subCategory}")
    public ResponseEntity<List<PracticeQuestionModel>> getByGradeCategoryAndSubCategory(
            @PathVariable int grade,
            @PathVariable String category,
            @PathVariable String subCategory
    ) {
        return ResponseEntity.ok(service.getByGradeAndCategoryAndSubCategory(grade, category, subCategory));
    }


    @PostMapping("/bulk")
    public ResponseEntity<List<PracticeQuestionModel>> createBulk(
            @RequestBody List<PracticeQuestionModel> models
    ) {
        List<PracticeQuestionModel> savedModels = service.saveAll(models);
        return ResponseEntity.ok(savedModels);
    }


}
