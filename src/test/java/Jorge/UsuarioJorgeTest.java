package Jorge;

import com.ifpe.ifpe.Entities.Usuario;
import com.ifpe.ifpe.Repositories.UsuarioRepository;
import com.ifpe.ifpe.Services.UsuarioService;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jorge
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioJorgeTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setup() {
        usuario = new Usuario(
                "Nelson Silva",
                "nelson1@email.com",
                "12345678921",
                "Senhacer95#",
                "15-10-2006",
                "8292334586",
                "Rua Dois",
                "Arruda",
                "Recife",
                "20",
                "PE",
                "50274839",
                null);
    }

    @Test
    void J_1_deveEditarPerfilComCamposValidos() {
        when(usuarioRepository.existePorEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.existePorCpf(usuario.getCpf())).thenReturn(false);

        usuarioService.editarUsuario(usuario);
        verify(usuarioRepository, times(1)).salvar(usuario);
    }

    @Test
    void J_2_1_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("Senhacer#");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_2_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("senhacer12#");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_3_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("Senhacer12#Senhacer12#");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_4_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("Se12#");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_5_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("Senhacer12");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_6_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("SENHACER12#");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_2_7_naoDeveCadastrarSenhaInvalida() {
        usuario.setSenha("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_3_naoDeveCadastrarEmailJaExistente() {
        usuario.setEmail("nelson1novo@email.com");

        when(usuarioRepository.existePorEmail(usuario.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_4_naoDeveCadastrarComNomeEmBranco() {
        usuario.setNome("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Nome é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

}
