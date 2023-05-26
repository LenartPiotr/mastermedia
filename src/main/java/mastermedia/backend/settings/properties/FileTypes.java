package mastermedia.backend.settings.properties;

import java.util.ArrayList;
import java.util.List;

public class FileTypes {

    private List<FileType> fileTypeList;

    public FileTypes(){}
    public FileTypes(FileTypes fileTypes){
        this.fileTypeList = new ArrayList<>();
        for (FileType ft : fileTypes.fileTypeList){
            this.fileTypeList.add(new FileType(ft));
        }
    }

    public List<FileType> getFileTypeList() {
        return fileTypeList;
    }

    public void setFileTypeList(List<FileType> fileTypeList) {
        this.fileTypeList = fileTypeList;
    }

    @Override
    public String toString() {
        return "FileTypes{" +
                "fileTypeList=" + fileTypeList +
                '}';
    }
}
