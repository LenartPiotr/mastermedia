package mastermedia.backend.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MetadataList {

    public MetadataList(){}

    @JsonProperty("list")
    private List<FileMetadata> list;

    public List<FileMetadata> getList() {
        return list;
    }

    public void setList(List<FileMetadata> list) {
        this.list = list;
    }
}
