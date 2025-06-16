package com.example.Complexo.controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.Complexo.model.Artist;
import com.example.Complexo.model.GalleryRequest;
import com.example.Complexo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.Complexo.model.User;
import com.example.Complexo.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            if (userService.emailExists(user.getStudioEmail())) {
                // Se o email já existe vai retornar um BAD_REQUEST com uma mensagem de erro
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            User newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/searchByName")
    public ResponseEntity<?> getStudioByName(@RequestParam String name){
        try {
            List<User> allUsers = userService.getAllUsers();
            List<User> foundStudios = new ArrayList<>();

            // Normalizar o termo de busca
            String normalizedSearch = normalizeString(name);

            for (User studio : allUsers) {
                if (studio.getStudioName() != null) {
                    // Normalizar o nome do estúdio
                    String normalizedStudioName = normalizeString(studio.getStudioName());

                    // Verificar se contém o termo normalizado
                    if (normalizedStudioName.contains(normalizedSearch)) {
                        foundStudios.add(studio);
                    }
                }
            }

            return ResponseEntity.ok(foundStudios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private String normalizeString(String input) {
        if (input == null) return "";

        // Converter para minúsculas
        String normalized = input.toLowerCase();

        // Remover espaços
        normalized = normalized.replaceAll("\\s+", "");

        // Remover acentos e caracteres especiais
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[^\\p{ASCII}]", "");
        normalized = normalized.replaceAll("[^a-z0-9]", "");

        return normalized;
    }
    

    @GetMapping("/{studioId}")
    public ResponseEntity<User> getUserById(@PathVariable Long studioId) {
        User user = userService.getUserById(studioId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{studioId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long studioId) {
        userService.deleteUserById(studioId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{studioId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long studioId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUserById(studioId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        UserDetails userDetails = userService.findBystudioEmail(email);

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); //
        }

        User user = (User) userDetails;
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{studioId}/gallery")
    public ResponseEntity<?> addGalleryPhotos(
            @PathVariable Long studioId,
            @RequestBody GalleryRequest request) {

        Optional<User> estudioOpt = userRepository.findById(studioId);
        if (estudioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        for (String image : request.getImages()) {
            if (!image.startsWith("data:image/")) {
                return ResponseEntity.badRequest().body("Formato de imagem inválido");
            }

            // Verificar tamanho (1 caractere base64 ≈ 1.37 bytes)
            // 2 MB = 2 * 1024 * 1024 bytes
            double maxSizeBytes = 2 * 1024 * 1024;
            double maxSizeBase64 = maxSizeBytes * 1.37;

            if (image.length() > maxSizeBase64) {
                return ResponseEntity.badRequest().body("Imagem muito grande. Tamanho máximo é 2MB");
            }
        }

        User estudio = estudioOpt.get();
        estudio.getStudioImages().addAll(request.getImages());

        // Limita o número máximo de fotos (opcional)
        if (estudio.getStudioImages().size() > 50) {
            estudio.setStudioImages(
                    estudio.getStudioImages().subList(0, 50)
            );
        }

        userRepository.save(estudio);
        return ResponseEntity.ok(estudio);
    }

    @DeleteMapping("/{studioId}/gallery/{index}")
    public ResponseEntity<?> removeGalleryPhoto(
            @PathVariable Long studioId,
            @PathVariable int index) {

        Optional<User> estudioOpt = userRepository.findById(studioId);
        if (estudioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User estudio = estudioOpt.get();
        if (index < 0 || index >= estudio.getStudioImages().size()) {
            return ResponseEntity.badRequest().body("Índice inválido");
        }

        estudio.getStudioImages().remove(index);
        userRepository.save(estudio);
        return ResponseEntity.ok(estudio);
    }
}
