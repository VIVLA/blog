package com.vivla.blog.controllers;

import com.vivla.blog.models.PostModel;
import com.vivla.blog.repo.PostModelRepository;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    private PostModelRepository postRepository;

    public BlogController() {
    }

    @GetMapping({"/blogMain"})
    public String blogMain(Model model) {
        Iterable<PostModel> posts = this.postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blogMain";
    }

    @GetMapping({"/blogMain/blogAdd"})
    public String blogAdd() {
        return "blogAdd";
    }

    @PostMapping({"/blogMain/blogAdd"})
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText) {
        PostModel postModel = new PostModel(title, anons, fullText);
        this.postRepository.save(postModel);
        return "redirect:/blogMain";
    }

    @GetMapping({"/blogMain/{id}"})
    public String blogDetails(@PathVariable("id") long id, Model model) {
        this.getPostId(id);
        Optional<PostModel> postModel = this.postRepository.findById(id);
        ArrayList<PostModel> res = new ArrayList();
        Objects.requireNonNull(res);
        postModel.ifPresent(res::add);
        model.addAttribute("postModel", res);
        return "blogDetails";
    }

    @GetMapping({"/blogMain/{id}/edit"})
    public String blogEdit(@PathVariable("id") long id, Model model) {
        this.getPostId(id);
        Optional<PostModel> postModel = this.postRepository.findById(id);
        ArrayList<PostModel> res = new ArrayList();
        Objects.requireNonNull(res);
        postModel.ifPresent(res::add);
        model.addAttribute("postModel", res);
        return "blogEdit";
    }

    private String getPostId(long id) {
        return !this.postRepository.existsById(id) ? "redirect:/blogMain" : "";
    }

    @PostMapping({"/blogMain/{id}/edit"})
    public String blogPostUpdate(@PathVariable("id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText) {
        PostModel postModel = (PostModel)this.postRepository.findById(id).orElseThrow();
        postModel.setTitle(title);
        postModel.setAnons(anons);
        postModel.setFullText(fullText);
        this.postRepository.save(postModel);
        return "redirect:/blogMain";
    }

    @PostMapping({"/blogMain/{id}/delete"})
    public String blogPostDelete(@PathVariable("id") long id) {
        PostModel postModel = (PostModel)this.postRepository.findById(id).orElseThrow();
        this.postRepository.delete(postModel);
        return "redirect:/blogMain";
    }
}
