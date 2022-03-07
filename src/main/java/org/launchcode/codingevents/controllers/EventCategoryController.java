package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("index")
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create a Category");
        model.addAttribute(new EventCategory());
        //model.addAttribute("eventCategory", eventCategory);
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newCategory, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create a Category");
            model.addAttribute("errorMsg", "Something's not right...please check your entries.");
            return ("eventCategories/create");
        }
        model.addAttribute("title", "Create a Category");
        model.addAttribute("newCategory", "New Category");
        return "eventCategories/index";
    }
}