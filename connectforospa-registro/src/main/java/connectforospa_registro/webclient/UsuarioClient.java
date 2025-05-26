package connectforospa_registro.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8082").build(); 
    }


    public Mono<UsuarioAuth> crearUsuarioAuth(Long idUsuario, String email, String password, String estado) {
        return webClient.post()
                .uri("/api/v1/auth/usuarios")
                .bodyValue(new UsuarioAuth(idUsuario, email, password, estado))
                .retrieve()
                .bodyToMono(UsuarioAuth.class); 
    }


    public Mono<UsuarioAuth> actualizarEstadoUsuarioAuth(Long idUsuario, String estado) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/auth/usuarios/{idUsuario}/estado").build(idUsuario))
                .bodyValue(new ActualizarEstado(estado))
                .retrieve()
                .bodyToMono(UsuarioAuth.class); 
    }


    public Mono<UsuarioAuth> obtenerUsuarioDesdeAuth(Long idUsuario) {
        return webClient.get()
                .uri("/api/v1/auth/usuarios/{idUsuario}", idUsuario)
                .retrieve()
                .bodyToMono(UsuarioAuth.class);
    }


    public Mono<Void> eliminarUsuarioAuth(Long idUsuario) {
        return webClient.delete()
                .uri("/api/v1/auth/usuarios/{idUsuario}", idUsuario)
                .retrieve()
                .bodyToMono(Void.class);
    }


    private record UsuarioAuth(Long idUsuario, String email, String password, String estado) {}
    private record ActualizarEstado(String estado) {}
}
