package br.com.henriqueandrew.Atividade4;

import java.io.Serializable;

// @author Henrique Andrew da Silva

public class Pessoa implements Serializable {

    private String nome;
    private int idade;

    public Pessoa() {

    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        try {
            this.idade = idade;
        } catch (NumberFormatException e) {
            e.getMessage();
        }

    }

}