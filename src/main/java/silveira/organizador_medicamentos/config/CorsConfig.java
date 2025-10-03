package silveira.organizador_medicamentos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica o CORS a todos os endpoints que começam com /api/
                .allowedOrigins("http://localhost:5173") // A URL do seu frontend React (Vite)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite os métodos que você está usando
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}