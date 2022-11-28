package br.ufsm.poow2.biblioteca_rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("")
    public String IndexController() {
        return "index";
    }

}
