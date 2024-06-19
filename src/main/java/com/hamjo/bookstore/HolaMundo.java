package com.hamjo.bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/controller")
public class HolaMundo {

    @GetMapping
    @ResponseBody
    public String mostrarMensaje(){
        return "Me la pelas";
    }
    @GetMapping("/holamundo")
    @ResponseBody
    public String mostrarMensaje1(){
        return "Me la pelas";
    }

}
