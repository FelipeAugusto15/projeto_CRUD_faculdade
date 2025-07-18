package com.esoft.teste_spring.models;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.esoft.teste_spring.DTOs.NinjaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ninja")
public class Ninja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String cla;

    @Column(nullable = false)
    private int idade;

    
    // private String vila; // transformar em um model

    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;

    @ManyToOne
    @JoinColumn(name = "vila_id")
    private Vila vila;

    // Ajuste para uma relação ;) n:n com Jutsu
    @ManyToMany
    @JoinTable(
        name = "ninjas_jutsus",
        joinColumns = @JoinColumn(name = "ninja_id"),
        inverseJoinColumns = @JoinColumn(name = "jutsu_id") // tabela para relacionar ninjas e jutsus
    )
    private List<Jutsu> jutsus;
    
    public Ninja(NinjaDTO ninja) {
        this.id = ninja.id();
        this.nome = ninja.nome();
        this.idade = ninja.idade();
        this.cla = ninja.cla();
        // this.vila = ninja.vila(); // remover pois será uma model
    }

    public Ninja(NinjaDTO ninja, List<Jutsu> jutsus) {
        this.id = ninja.id();
        this.nome = ninja.nome();
        this.idade = ninja.idade();
        this.cla = ninja.cla();
        this.jutsus = jutsus;
    }

}
