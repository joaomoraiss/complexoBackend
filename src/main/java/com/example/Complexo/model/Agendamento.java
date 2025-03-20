package com.example.Complexo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "agendamento")
@Getter
public class Agendamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artist artista;

    Agendamento(long id, Cliente cliente, Artist artista) {
        this.id = id;
        this.cliente = cliente;
        this.artista = artista;
    }
    Agendamento() {

    }
}
