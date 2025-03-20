package com.example.Complexo.controller;

import com.example.Complexo.model.Agendamento;
import com.example.Complexo.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Agendamento> buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id);
    }

    @PostMapping
    public Agendamento salvar(@RequestBody Agendamento agendamento) {
        return agendamentoService.salvar(agendamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }
}
