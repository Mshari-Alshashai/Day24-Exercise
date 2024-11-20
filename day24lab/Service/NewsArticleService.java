package com.example.day24lab.Service;

import com.example.day24lab.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticle(String id, NewsArticle newsArticle){
        for (NewsArticle n:newsArticles){
            if (n.getID().equals(id)){
                newsArticles.set(newsArticles.indexOf(n),newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(String id){
        for (NewsArticle n:newsArticles){
            if (n.getID().equals(id)){
                newsArticles.remove(n);
                return true;
            }
        }
        return false;
    }

    public boolean publish(String id){
        for (NewsArticle n:newsArticles){
            if (n.getID().equals(id)){
                n.setPublished(true);
                n.setPublishDate(LocalDate.now());
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getPublished(){
        ArrayList<NewsArticle> published = new ArrayList<>();
        for (NewsArticle n:newsArticles){
            if (n.isPublished()) published.add(n);
        }
        return published;
    }

    public ArrayList<NewsArticle> getByCategory(String category){
        ArrayList<NewsArticle> byCategory= new ArrayList<>();
        for (NewsArticle n:newsArticles){
            if(n.getCategory().equals(category))byCategory.add(n);
        }
        return byCategory;
    }
}
