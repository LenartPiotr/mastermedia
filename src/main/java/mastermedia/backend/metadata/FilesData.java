package mastermedia.backend.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilesData {
    @JsonProperty("files_data")
    private List<FileMetadata> filesData = new ArrayList<>();

    public void setFilesData(List<FileMetadata> filesData) {
        this.filesData = filesData;
    }

    public List<FileMetadata> getFilesData() {
        return filesData;
    }

    public void save() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            mapper.writeValue(new File("metadata.yaml"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            FilesData readData = mapper.readValue(new File("metadata.yaml"), FilesData.class);
            this.filesData = readData.getFilesData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void delete(String fileName) {
        findFileMetadata(fileName).ifPresent(fileMetadata -> {
            fileMetadata.setProcessingStage(FileMetadata.ProcessingStage.DELETED);
            save();
        });
    }

    public void sorted(String fileName, String miniaturePath) {
        findFileMetadata(fileName).ifPresent(fileMetadata -> {
            fileMetadata.setProcessingStage(FileMetadata.ProcessingStage.SORTED);
            fileMetadata.setMiniaturePath(miniaturePath);
            save();
        });
    }

    private Optional<FileMetadata> findFileMetadata(String fileName) {
        return filesData.stream()
                .filter(fileMetaData -> fileMetaData.getFileName().equals(fileName))
                .findFirst();
    }

}
