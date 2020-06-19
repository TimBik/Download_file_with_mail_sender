package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
