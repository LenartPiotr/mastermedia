package mastermedia.backend.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import mastermedia.backend.settings.Settings;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetadataManager {
    String filename;

    MetadataList list;

    public MetadataManager(String filename){
        this.filename = filename;
    }

    public List<FileMetadata> getFilesData() {
        return list.getList();
    }

    public void save() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            Constructor c =  new Constructor(FileMetadata.class, new LoaderOptions());
            Yaml yaml = new Yaml(c);
            yaml.dump(list, new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void read() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            list = mapper.readValue(new File(filename), MetadataList.class);
        } catch (IOException e) {
            // e.printStackTrace();
            this.list = new MetadataList();
            list.setList(new ArrayList<>());
        }
        /*try{
            Yaml yaml = new Yaml();

            list = yaml.load(new FileInputStream(filename));
            if (list == null){
                list = new MetadataList();
                list.setList(new ArrayList<>());
            }
        } catch (IOException e){
            e.printStackTrace();
            list = new MetadataList();
            list.setList(new ArrayList<>());
        }*/
    }

    /*public void delete(String fileName) {
        findFileMetadata(fileName).ifPresent(fileMetadata -> {
            fileMetadata.setProcessingStage(FileMetadata.ProcessingStage.DELETED);
        });
    }

    public void sorted(String fileName, String miniaturePath) {
        findFileMetadata(fileName).ifPresent(fileMetadata -> {
            fileMetadata.setProcessingStage(FileMetadata.ProcessingStage.SORTED);
            fileMetadata.setMiniaturePath(miniaturePath);
        });
    }

    private Optional<FileMetadata> findFileMetadata(String fileName) {
        return filesData.stream()
                .filter(fileMetaData -> fileMetaData.getFileName().equals(fileName))
                .findFirst();
    }*/

}
