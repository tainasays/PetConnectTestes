package com.ifpe.ifpe.Repositories;

import com.ifpe.ifpe.Entities.Usuario;

/**
 *
 * @author thayn
 */

public interface UsuarioRepository {
	void salvar(Usuario usuario);
	void deletarPorCpf(String cpf);
	Boolean existePorEmail(String email);
	Boolean existePorCpf(String cpf);
	Usuario buscarPorCpf(String cpf);
        Usuario buscarPorEmail(String email);
}
