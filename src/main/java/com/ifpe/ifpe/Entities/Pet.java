/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Entities;

/**
 *
 * @author elaine
 */
public class Pet {
    private String nome;
    private String tipo;
    private String sexo;
    private String raca;
    private String idade;
    private String peso;
    private String saude;
    private String foto;
    private Usuario tutor; // Relacionamento com o dono do pet

    public Pet(String nome, String tipo, String sexo, String raca, String idade, String peso, String saude, String foto, Usuario tutor) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.saude = saude;
        this.foto = foto;
        this.tutor = tutor;
    }
    
    //Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getSaude() {
        return saude;
    }

    public void setSaude(String saude) {
        this.saude = saude;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario getTutor() {
        return tutor;
    }

    public void setTutor(Usuario tutor) {
        this.tutor = tutor;
    }
    
}
