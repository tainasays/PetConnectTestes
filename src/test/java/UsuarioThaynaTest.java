
import com.ifpe.ifpe.Entities.Usuario;
import com.ifpe.ifpe.Repositories.UsuarioRepository;
import com.ifpe.ifpe.Services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 *
 * @author Thayna Cenário: Realizar cadastro de usuário Ids caso de teste: T_1,
 * T_2, T_3 e T_4 T_1: Validar cadastro bem-sucedido de usuário com todos os
 * dados válidos T_2.*: Validar retorno de mensagem de erro quando campo
 * obrigatório 'x' não é enviado no cadastro de usuário T_3: Validar retorno de
 * mensagem de erro ao informar email já cadastrado no sistema em cadastro de
 * usuário T_4: Cadastrar usuário com senha inválida Número total de casos de
 * testes: 11. Número total de testes: 15.
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioThaynaTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioValido;

    @BeforeEach
    void setUp() {
        usuarioValido = new Usuario(
                "João Silva",
                "joao@email.com",
                "12345678901",
                "Senha1235789@",
                "01-01-1990",
                "81999999999",
                "Rua 1",
                "Centro",
                "Recife",
                "100",
                "PE",
                "50700000",
                null);
    }

    @Test
    void T1_deveCadastrarUsuarioComSucesso() {
        when(usuarioRepository.existePorEmail(usuarioValido.getEmail())).thenReturn(false);
        when(usuarioRepository.existePorCpf(usuarioValido.getCpf())).thenReturn(false);
        usuarioService.cadastrarUsuario(usuarioValido);
        verify(usuarioRepository, times(1)).salvar(usuarioValido);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_1_naoDeveCadastrarUsuarioComNomeVazioOuNull(String nomeInvalido) {
        Usuario usuario = new Usuario(
                nomeInvalido,
                "email@email.com",
                "12345678901",
                "senha123",
                "01-01-1990",
                "81999999999",
                "Rua 1",
                "Centro",
                "Recife",
                "100",
                "PE",
                "50700000",
                null);
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Nome é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_2_naoDeveCadastrarUsuarioComDataNascimentoVaziaOuNull(String dataInvalida) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email@email.com",
                "12545758485",
                "!SenhaValida123",
                dataInvalida,
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));

        assertTrue(ex1.getMessage().contains("Data de nascimento é obrigatória!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_3_naoDeveCadastrarUsuarioComEmailVazioOuNull(String emailInvalido) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                emailInvalido,
                "12545758485",
                "!SenhaValida123",
                "10-10-2000",
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));

        assertTrue(ex1.getMessage().contains("Email é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_4_naoDeveCadastrarUsuarioComCpfVazioOuNull(String cpfInvalido) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email1@novo.com",
                cpfInvalido,
                "!SenhaValida123",
                "10-10-2000",
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));

        assertTrue(ex1.getMessage().contains("CPF é obrigatório!"));

        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_5_naoDeveCadastrarUsuarioComSenhaVaziaOuNull(String senhaVaziaOuNula) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email1@novo.com",
                "10478754781",
                senhaVaziaOuNula,
                "10-10-2000",
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));

        assertTrue(ex1.getMessage().contains("Senha é obrigatória!"));

        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_3_naoDeveCadastrarUsuarioComNumeroContatoVazioOuNull(String numContatoInvalido) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email@email.com",
                "12545758485",
                "!SenhaValida123",
                "10-10-2000",
                numContatoInvalido,
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));

        assertTrue(ex1.getMessage().contains("Numero de contato é obrigatório!"));

        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_6_naoDeveCadastrarUsuarioComCepVazioOuNull(String cepInvalido) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email@email.com",
                "12545758485",
                "!SenhaValida123",
                "10-10-2000",
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                "77",
                "PE",
                cepInvalido,
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));
        assertTrue(ex1.getMessage().contains("CEP é obrigatório!"));

        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void T2_8_naoDeveCadastrarUsuarioComNumeroVazioOuNull(String numeroInvalido) {
        Usuario usuarioVazio = new Usuario(
                "Clara Maria Alves",
                "email@email.com",
                "12545758485",
                "!SenhaValida123",
                "10-10-2000",
                "4655784474",
                "Rua X",
                "Bairro Y",
                "Cidade Z",
                numeroInvalido,
                "PE",
                "68371456",
                null);

        Exception ex1 = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioVazio));
        assertTrue(ex1.getMessage().contains("Número é obrigatório!"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void T3_naoDeveCadastrarUsuarioComEmailJaCadastrado() {
        Usuario usuario = new Usuario(
                "Bentinho Santana",
                "bentinho@email.com",
                "12345678921",
                "Senhacer95#",
                "15102002",
                "81988556632",
                "Rua Poloni",
                "Várzea",
                "Recife",
                "218",
                "PE",
                "50740340",
                null
        );
        when(usuarioRepository.existePorEmail(usuario.getEmail())).thenReturn(true);
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuario));
        assertTrue(ex.getMessage().contains("Email já cadastrado"));
        verify(usuarioRepository, never()).salvar(any());
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "Sencacer#", // sem número
                "sencacer12#", // sem maiúscula
                "Se12#", // muito curta
                "Sencacer12", // sem especial
                "SENHACER12#" // sem minúscula
            })
    void T4_naoDeveCadastrarUsuarioComSenhaInvalida(String senhaInvalida) {
        Usuario usuarioCurta = new Usuario(
                "José Santos",
                "jose1novo@email.com",
                "12345678921",
                senhaInvalida,
                "15102003",
                "8292334587",
                "Rua Poloni",
                "Várzea",
                "Recife",
                "218",
                "PE",
                "50740340",
                null
        );
        when(usuarioRepository.existePorEmail(usuarioCurta.getEmail())).thenReturn(false);
        when(usuarioRepository.existePorCpf(usuarioCurta.getCpf())).thenReturn(false);
        Exception exCurta = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.cadastrarUsuario(usuarioCurta));
        assertTrue(exCurta.getMessage().contains("Senha não está de acordo com os padrões solicitados"));
        verify(usuarioRepository, never()).salvar(any());

    }

}
