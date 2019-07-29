package me.codeboy.clipboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文档controller
 * Created by yuedong.li on 2019-07-14
 */
@Controller
public class DocController {

    @RequestMapping(value = {"/", "/index.html"})
    public String index() {
        return "index";
    }
}