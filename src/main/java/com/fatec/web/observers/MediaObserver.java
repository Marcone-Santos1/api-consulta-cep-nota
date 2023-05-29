package com.fatec.web.observers;

import org.springframework.ui.Model;

public class MediaObserver implements Observer {
    private Model model;

    public MediaObserver(Model model) {
        this.model = model;
    }

    @Override
    public void update(double media) {
        model.addAttribute("media", media);
    }
}