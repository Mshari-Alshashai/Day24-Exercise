package com.example.day24lab.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "The ID should not be empty")
    private String ID;

    @NotEmpty(message = "The title should not be empty")
    @Size(max = 100)
    private String title;

    @NotEmpty(message = "The author should not be empty")
    @Size(min = 5,max = 20)
    private String author;

    @NotEmpty(message = "The content should not be empty")
    @Size(min = 200)
    private String content;

    @NotEmpty(message = "The category should not be empty")
    @Pattern(regexp = "politics|sport|technology",message = "The category should be politics or sport or technology")
    private String category;

    @NotEmpty(message = "The image URL should not be empty")
    @URL(message = "Get sure of the URL format")
    private String imageUrl;

    @NotNull(message = "The is published should not be null")
    @AssertFalse(message = "Is published should be false")
    private boolean isPublished;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
