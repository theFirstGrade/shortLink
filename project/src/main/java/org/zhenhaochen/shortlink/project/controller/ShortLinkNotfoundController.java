package org.zhenhaochen.shortlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Short Link Not Found Controller
 */
@Controller
public class ShortLinkNotfoundController {

    /**
     * short link not found redirect
     */
    @RequestMapping("/page/notfound")
    public String notfound() {
        return "notfound";
    }
}

