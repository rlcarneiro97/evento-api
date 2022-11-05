package com.eventoapi.resources;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.eventoapi.models.Evento;
import com.eventoapi.repository.EventoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/evento")
@Tag(name = "evento", description = "CRUD de evento")
public class EventoResource {
    
    @Autowired
    private EventoRepository er;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna lista de eventos")
    public @ResponseBody Iterable<Evento> listaEventos(){
        Iterable<Evento> listaEventos = er.findAll();
        return listaEventos;
    }

    @PostMapping()
    @Operation(summary = "Salva um evento")
    public Evento cadastraEvento(@RequestBody @Valid Evento evento){
        return er.save(evento);
    }

    @DeleteMapping()
    @Operation(summary = "Deleta um evento")
    public Evento deletaEvento(@RequestBody Evento evento){
        er.delete(evento);
        return evento;
    }

}
