package com.example.day24lab.Controller;

import com.example.day24lab.ApiResponse.ApiResponse;
import com.example.day24lab.Model.NewsArticle;
import com.example.day24lab.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/news-article")
@RequiredArgsConstructor
public class NewsArticleController {

    public final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticles(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News article added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (newsArticleService.updateNewsArticle(id,newsArticle)) return ResponseEntity.status(200).body(new ApiResponse("News article updated"));
        return ResponseEntity.status(400).body(new ApiResponse("News article not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id){
        if (newsArticleService.deleteNewsArticle(id)) return ResponseEntity.status(200).body(new ApiResponse("News article deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("News article not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publish(@PathVariable String id){
        if (newsArticleService.publish(id)) return ResponseEntity.status(200).body(new ApiResponse("News article is published"));
        return ResponseEntity.status(400).body(new ApiResponse("News Article not found"));
    }

    @GetMapping("/get-published")
    public ResponseEntity getPublished(){
        if(newsArticleService.getPublished().isEmpty())return ResponseEntity.status(200).body(new ApiResponse("There is no news article published"));
        return ResponseEntity.status(200).body(newsArticleService.getPublished());
    }

    @GetMapping("/get-by-category")
    public ResponseEntity getByCategory(@RequestBody @Valid String category){
        if(newsArticleService.getByCategory(category).isEmpty())return ResponseEntity.status(200).body(new ApiResponse("No news articles in this category"));
        return ResponseEntity.status(200).body(newsArticleService.getByCategory(category));
    }

}
