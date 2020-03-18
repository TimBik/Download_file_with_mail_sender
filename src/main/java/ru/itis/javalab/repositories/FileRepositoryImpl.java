package ru.itis.javalab.repositories;

import ru.itis.javalab.dto.FileDto;

import java.util.List;
import java.util.Optional;

public class FileRepositoryImpl implements FileRepository {

    @Override
    public Optional<FileDto> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<FileDto> findAll() {
        return null;
    }

    @Override
    public void save(FileDto entity) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(FileDto entity) {

    }
}
