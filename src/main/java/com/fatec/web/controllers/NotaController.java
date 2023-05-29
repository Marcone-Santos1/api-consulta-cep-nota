package com.fatec.web.controllers;

import com.fatec.web.models.Notas;
import com.fatec.web.observers.MediaObserver;
import com.fatec.web.observers.MediaSubject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notas")
public class NotaController {

    private static final NotaController INSTANCE = new NotaController();

    private NotaController() {}

    private MediaSubject mediaSubject = new MediaSubject();

    public static NotaController getInstance() {
        return INSTANCE;
    }

    @GetMapping
    public String exibirFormulario(Model model) {
        model.addAttribute("notas", new Notas());

        return "notas";
    }

    @PostMapping
    public String calcularMedia(Notas notas, Model model) {
        double p1 = notas.getP1();
        double p2 = notas.getP2();
        String projeto = notas.getProjeto();

        MediaObserver observer = new MediaObserver(model);
        mediaSubject.attach(observer);

        mediaSubject.calculateMedia(p1, p2);

        model.addAttribute("projeto", projeto);

        return "notas";
    }
}
