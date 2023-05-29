package com.fatec.web.controllers;

import com.fatec.web.controllers.request.CepRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cep")
public class CepController {

    private static final CepController INSTANCE = new CepController();

    private CepController() {}

    public static CepController getInstance() {
        return INSTANCE;
    }

    @GetMapping
    public String exibirFormulario() {
        return "cep";
    }

    @PostMapping
    public String consultarCep(CepRequest cep, Model model) throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> endereco = Unirest.get("http://viacep.com.br/ws/" + cep.getCep() + "/json/")
                .asString();

        JsonObject jsonObject = JsonParser.parseString(endereco.getBody()).getAsJsonObject();

        String logradouro = jsonObject.get("logradouro").getAsString();
        String bairro = jsonObject.get("bairro").getAsString();
        String cidade = jsonObject.get("localidade").getAsString();
        String uf = jsonObject.get("uf").getAsString();

        model.addAttribute("endereco", endereco.getBody());
        model.addAttribute("cep", cep.getCep());
        model.addAttribute("logradouro", logradouro);
        model.addAttribute("bairro", bairro);
        model.addAttribute("cidade", cidade);
        model.addAttribute("uf", uf);

        return "cep";
    }
}
