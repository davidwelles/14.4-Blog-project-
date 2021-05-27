package com.tts.TechtTalentBlog.controller;


import com.tts.TechtTalentBlog.model.BlogPost;
import com.tts.TechtTalentBlog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class BlogPostController {



    @Autowired
    BlogPostService blogPostService;




    @GetMapping("/")
    public String index(BlogPost blogPost, Model model) {
        model.addAttribute("posts", blogPostService.getAllBlogPosts());

        return "blogpost/index";
    }

    @GetMapping("/blogposts/new")
    public String newBlog(BlogPost blogPost) {
        return "blogpost/new";
    }

    @PostMapping("/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostService.addNewBlogPost(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }



    @DeleteMapping("/blogposts/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
        blogPostService.deletePostById(id);
        return "redirect:/";
    }

    @GetMapping("/blogposts/{id}")
    public String editPostWithId(@PathVariable Long id,
                                 BlogPost blogpost,
                                 Model model) {

        BlogPost foundPost = blogPostService.findBlogPostById(id);
        model.addAttribute("blogPost", foundPost);




        return "blogpost/edit";
    }

    @PostMapping("/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable Long id,
                                     BlogPost blogPost,
                                     Model model) {


        BlogPost editedPost = blogPostService.editBlogPostById(id, blogPost);
        model.addAttribute("blogPost", editedPost);

        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());

        return "blogpost/result";
    }


}
