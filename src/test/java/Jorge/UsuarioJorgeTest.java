package Jorge;

import com.ifpe.ifpe.Entities.Usuario;
import com.ifpe.ifpe.Repositories.UsuarioRepository;
import com.ifpe.ifpe.Services.UsuarioService;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(strings = {
        "Senhacer#",
        "senhacer12#",
        "Senhacer12#Senhacer12#",
        "Se12#",
        "Senhacer12",
        "SENHACER12#",
        ""
    })
    void naoDeveEditarComSenhaInvalida(String senhaInvalida) {
        usuario.setSenha(senhaInvalida);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));

        assertTrue(ex.getMessage().contains("Senha não está de acordo com o padrão solicitado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_3_naoDeveEditarEmailJaExistente() {
        usuario.setEmail("nelson1novo@email.com");

        when(usuarioRepository.existePorEmail(usuario.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_4_naoDeveEditarComNomeEmBranco() {
        usuario.setNome("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Nome é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_5_naoDeveEditarComDatadeNascimentoEmBranco() {
        usuario.setDataNascimento("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Data de nascimento é obrigatória!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_6_naoDeveEditarEmailEmBranco() {
        usuario.setEmail("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("E-mail é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_7_naoDeveEditarComCpfEmBranco() {
        usuario.setCpf("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("CPF é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_8_naoDeveEditarComNumeroDeContatoEmBranco() {
        usuario.setNumeroContato("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Número de contato é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_9_naoDeveEditarComLogradouroEmBranco() {
        usuario.setLogradouro("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Logradouro é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_10_naoDeveEditarComBairroEmBranco() {
        usuario.setBairro("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Bairro é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_11_naoDeveEditarComCepEmBranco() {
        usuario.setCep("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("CEP é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_12_naoDeveEditarComCidadeEmBranco() {
        usuario.setCidade("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Cidade é obrigatória!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_13_naoDeveEditarComNumeroEmBranco() {
        usuario.setNumero("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Número é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void J_14_naoDeveEditarComEstadoEmBranco() {
        usuario.setEstado("");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.editarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Estado é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }


}
