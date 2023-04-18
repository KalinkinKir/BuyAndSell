package ru.test.buyandsell.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.buyandsell.Models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
