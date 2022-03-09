package org.hyt.audioservice.web;

import org.hyt.audioservice.service.api.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/audio")
public class WebController {

    private final AudioService _service;

    @Autowired
    public WebController(
        AudioService service
    ) {
        _service = service;
    }

    @GetMapping
    public String getMain(Model model) {
        try {
            model.addAttribute("audios", _service.getAll());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/info")
    public String getInfo(){
        return "form";
    }

}
