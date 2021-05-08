package com.gvdw.discogsviewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gullian Van Der Walt
 * Created at 11:43 on May, 2021
 */
@RestController
@RequestMapping("/api/discogs")
public class DiscogsController {
    @GetMapping("/welcome")
    public String index(){
        return "Welcome To Discogs Viewer";
    }
}
