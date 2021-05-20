package com.tts.TechtTalentBlog.controller;


import com.tts.TechtTalentBlog.model.BlogPost;
import com.tts.TechtTalentBlog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;


    @GetMapping("/")
    public String index(BlogPost blogPost){
        //this return value is a reference to a template it will not return a string value
        return "blogpost/index.html";
    }

    @PostMapping("/")
    public String addNewPost(BlogPost blogPost, Model model){
        blogPostService.addNewBlogPost(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());

        return "blogpost/result";
    }
}
