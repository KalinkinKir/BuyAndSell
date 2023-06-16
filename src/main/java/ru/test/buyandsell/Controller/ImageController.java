package ru.test.buyandsell.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.buyandsell.Models.Image;
import ru.test.buyandsell.Repositories.ImageRepository;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер изображений", description = "Получает фото из БД, преобразовывает байты в фото")
public class ImageController {
    private final ImageRepository imageRepository;
    @GetMapping("images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
