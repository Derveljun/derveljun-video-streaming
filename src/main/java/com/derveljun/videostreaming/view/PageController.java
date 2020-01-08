package com.derveljun.videostreaming.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Controller
public class PageController {

    @Value("${video.location}")
    private String videoLocation;

    @GetMapping("/")
    String index(Model model) throws IOException {

        log.info(videoLocation);

        ClassPathResource resource = new ClassPathResource(videoLocation);

        Object[] videos = Files.list(Paths.get(resource.getURI()))
                            .map(f -> f.getFileName().toString())
                            .toArray();
        model.addAttribute("videos", videos);
        return "index";
    }

    @GetMapping("/{videoName}")
    public String video(@PathVariable String videoName, Model model){
        model.addAttribute("videoName", videoName);
        return "video";
    }

}
