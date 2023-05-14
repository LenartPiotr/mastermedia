package mastermedia.backend.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
public class FileMetadata {
    public enum ProcessingStage {
        SOURCE, MINI, SORTED, DELETED
    }
    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("miniature_path")
    private String miniaturePath;

    @JsonProperty("processing_stage")
    private ProcessingStage processingStage;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

    public void setMiniaturePath(String miniaturePath) {
        this.miniaturePath = miniaturePath;
    }

    public String getMiniaturePath() {
        return miniaturePath;
    }

    public void setProcessingStage(ProcessingStage processingStage) {
        this.processingStage = processingStage;
    }

    public ProcessingStage getProcessingStage() {
        return processingStage;
    }
}
