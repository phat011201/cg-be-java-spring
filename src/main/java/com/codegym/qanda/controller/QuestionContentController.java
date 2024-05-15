package com.codegym.qanda.controller;


import com.codegym.qanda.entity.QuestionContent;
import com.codegym.qanda.entity.QuestionTypes;
import com.codegym.qanda.service.IQuestionContentService;
import com.codegym.qanda.service.IQuestionTypesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/question-content")
@AllArgsConstructor
public class QuestionContentController {
    private final IQuestionContentService questionContentService;
    private final IQuestionTypesService questionTypesService;

    @GetMapping
    public ModelAndView showList(@RequestParam(name = "search", defaultValue = "") String search,
                                 @PageableDefault(size = 10, page = 0,
                                         sort = {"createdDate", "status"},
                                         direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println(search);
        ModelAndView modelAndView = new ModelAndView("QuestionContent");
        Page<QuestionContent> questionContents = questionContentService.findAll(pageable, search);
        Page<QuestionTypes> questionTypes = questionTypesService.findAll(pageable, search);
        modelAndView.addObject("questionContents", questionContents);
        modelAndView.addObject("questionTypes", questionTypes);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") UUID id) {
        QuestionContent questionContent = questionContentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("detail-question-content");
        modelAndView.addObject("questionContent", questionContent);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(@PageableDefault(size = 5, page = 0, sort = {"id"}) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("create-question-content");
        modelAndView.addObject("questionContent", new QuestionContent());
        modelAndView.addObject("questionTypes", questionTypesService.findAll(pageable, ""));
        return modelAndView;
    }


    @PostMapping("/create")
    public String create(QuestionContent questionContent) {
        questionContentService.save(questionContent);
        return "redirect:/question-content";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") UUID id, @PageableDefault(size = 5, page = 0, sort = {"id"}) Pageable pageable) {
        QuestionContent questionContent = questionContentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit-question-content");
        modelAndView.addObject("questionContent", questionContent);
        modelAndView.addObject("questionTypes", questionTypesService.findAll(pageable, ""));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, QuestionContent questionContent) {
        questionContent.setId(id);
        questionContentService.save(questionContent);
        return "redirect:/question-content";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        questionContentService.deleteById(id);
        return "redirect:/question-content";
    }
}
