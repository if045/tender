package com.softserveinc.tender.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

    @RequestMapping("/tendersHome")
    public String getAllTenders() {
        return "tenders";
    }

    @RequestMapping(value = "/tenderView/{tenderId}", method = RequestMethod.GET)
    public String showTender(@PathVariable("tenderId") Integer tenderId) {
        return "tender";
    }

    @RequestMapping("/mydeals")
    public String getAllDeals() {
        return "deals";
    }

    @RequestMapping("/moderatorHome")
    public String moderator() {
        return "moderator";
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/moderatorHome")
    public String moderatorView() {
        return "/moderatorHome";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/tendersHome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }
        model.setViewName("403");
        return model;
    }

}
