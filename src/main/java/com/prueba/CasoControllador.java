package com.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class CasoControllador {
    @Autowired
    CasoService casoService;


    @GetMapping("/agregate/{hasta}/{anno}")
    public void agregate(@PathVariable("hasta") int hasta,@PathVariable("anno") int anno) throws IOException {
        casoService.agregate(hasta,anno);
    }


    @GetMapping("/insertar/{hasta}")
    public void insertar(@PathVariable("hasta") int hasta) throws IOException {
        casoService.insertar(hasta);
    }
}
