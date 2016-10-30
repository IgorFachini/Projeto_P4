/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Cyber
 */
@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String Titulo;
    @Column(nullable = false, length = 45)
    private String Autor;

    @Column(nullable = false, length = 45)
    private int AnoEdicao;
    @Column(nullable = false, length = 45)
    private double Valor;

    //@GeneratedValue(strategy = GenerationType.AUTO)    
    // @Column(unique = true, nullable = false)    
    //private int GeneroId ;
    @OneToOne
    private Genero Genero;

    @Override
    public String toString() {
        return "Livro[ id=" + id + " ] [ nome = " + this.getTitulo() + " ]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getAnoEdicao() {
        return AnoEdicao;
    }

    public void setAnoEdicao(int AnoEdicao) {
        this.AnoEdicao = AnoEdicao;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public Genero getGenero() {
        return Genero;
    }

    public void setGenero(Genero Genero) {
        this.Genero = Genero;
    }

}
