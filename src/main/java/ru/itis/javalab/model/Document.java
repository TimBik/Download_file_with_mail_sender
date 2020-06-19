package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String path;
    private Long size;

    private String pathForDownload;

    @Transient
    private String fileName;

    @Transient
    private File sourceFile;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @PostLoad
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)
        sourceFile = new File(path);
        fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
    }

    @PreUpdate
    public void updateFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        this.path = sourceFile.getPath();
        this.size = sourceFile.length();
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
    }
}
