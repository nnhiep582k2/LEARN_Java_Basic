package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication implements WebMvcConfigurer {
    // region Field
    private static final String template = "Hello %s, you are %s years old!";
    private final AppConfiguration appConfiguration;
    public record Greeting(int id, String name, int age, String message) {}
    public record Todo(int id, int userId, String title, boolean completed) {}
    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;
    // endregion

    public DemoApplication(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    // region endpoints
    @GetMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "id", defaultValue = "0") int id,
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "age", defaultValue = "100") int age) {
        String message = String.format(template, name, age);
        System.out.println("Author is " + this.appConfiguration.getAuthor());
        System.out.println("ConnectionString is " + this.appConfiguration.getConnectionString());
        return new Greeting(id, name, age, message);
    }

    @PostMapping("/upload")
    public void handleFileUpload(@RequestBody MultipartFile file, HttpServletRequest request) {
        if(!file.isEmpty()) {
            try {
                String uploadDir = request.getServletContext().getRealPath("/uploads");
                Path uploadPath = Paths.get(uploadDir);
                if(!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File uploaded successfully: " + filePath.toString());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    // endregion

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor);
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Todo>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>() {});
        List<Todo> todos = response.getBody();
        System.out.println(todos);
        SpringApplication.run(DemoApplication.class, args);
    }
}
