package br.com.zup.fatura.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartoes")
public class ParcelaFaturaController {

    @PostMapping("/{id}/faturas/{id}")
    public String parcelarFatura(){
        return "ok";
    }

}
