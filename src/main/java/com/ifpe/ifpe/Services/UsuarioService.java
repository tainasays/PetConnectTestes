/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifpe.ifpe.Services;

import com.ifpe.ifpe.Repositories.UsuarioRepository;
import com.ifpe.ifpe.Entities.Usuario;

/**
 *
 * @author thayn
 */
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (isNullOrEmpty(usuario.getNome())) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getDataNascimento())) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória!");
        }
        if (isNullOrEmpty(usuario.getEmail())) {
            throw new IllegalArgumentException("Email é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getSenha())) {
            throw new IllegalArgumentException("Senha é obrigatória!");
        }
        if (isNullOrEmpty(usuario.getNumeroContato())) {
            throw new IllegalArgumentException("Numero de contato é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getCep())) {
            throw new IllegalArgumentException("CEP é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getNumero())) {
            throw new IllegalArgumentException("Número é obrigatório!");
        }
        // Validação de email já cadastrado
        if (repository.existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        // Validação de CPF já cadastrado
        if (repository.existePorCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (usuario.getCpf().length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");
        }

        var senha = usuario.getSenha();

        if (senha.length() < 8) {
            throw new IllegalArgumentException("Senha não está de acordo com os padrões solicitados");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com os padrões solicitados");
        }

        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com os padrões solicitados");
        }

        if (!senha.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha não está de acordo com os padrões solicitados");
        }

        if (!senha.matches(".*[@#$%^&+=!].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com os padrões solicitados");
        }

        repository.salvar(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (isNullOrEmpty(usuario.getNome())) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getDataNascimento())) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória!");
        }
        if (isNullOrEmpty(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getSenha())) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }
        if (isNullOrEmpty(usuario.getNumeroContato())) {
            throw new IllegalArgumentException("Número de contato é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getCep())) {
            throw new IllegalArgumentException("CEP é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getNumero())) {
            throw new IllegalArgumentException("Número é obrigatório!");
        }

        if (repository.existePorEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");

        }
        if (repository.existePorCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (usuario.getCpf().length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos");
        }

        if (isNullOrEmpty(usuario.getLogradouro())) {
            throw new IllegalArgumentException("Logradouro é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getBairro())) {
            throw new IllegalArgumentException("Bairro é obrigatório!");
        }
        if (isNullOrEmpty(usuario.getCidade())) {
            throw new IllegalArgumentException("Cidade é obrigatória!");
        }
        if (isNullOrEmpty(usuario.getEstado())) {
            throw new IllegalArgumentException("Estado é obrigatório!");
        }

        var senha = usuario.getSenha();

        if (senha.length() < 8 || senha.length() > 16) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }

        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }

        if (!senha.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }

        if (!senha.matches(".*[@#$%^&+=!].*")) {
            throw new IllegalArgumentException("Senha não está de acordo com o padrão solicitado");
        }

        repository.salvar(usuario);
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
