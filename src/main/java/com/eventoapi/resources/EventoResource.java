package com.eventoapi.resources;

import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.eventoapi.models.Evento;
import com.eventoapi.repository.EventoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/evento")
@Tag(name = "evento", description = "CRUD de evento")
public class EventoResource {

    @Autowired
    private EventoRepository er;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna lista de eventos")
    public @ResponseBody ArrayList<Evento> listaEventos(){
        Iterable<Evento> listaEventos = er.findAll();
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        for(Evento evento : listaEventos){
            long codigo = evento.getCodigo();
            evento.add(linkTo(methodOn(EventoResource.class).evento(codigo)).withSelfRel());
            eventos.add(evento);
        }

        return eventos;
    }

    @GetMapping(value = "/{codigo}", produces = "application/json")
    @Operation(summary = "Retorna um evento especifico")
    public @ResponseBody Evento evento(@PathVariable(value = "codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        evento.add(linkTo(methodOn(EventoResource.class).listaEventos()).withRel("Lista de Eventos"));

        return evento;
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
