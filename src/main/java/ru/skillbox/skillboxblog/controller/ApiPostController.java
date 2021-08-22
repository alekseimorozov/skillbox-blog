package ru.skillbox.skillboxblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/post/", method = RequestMethod.POST)
public class ApiPostController {
}
