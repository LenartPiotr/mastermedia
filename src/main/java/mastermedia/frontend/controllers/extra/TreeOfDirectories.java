package mastermedia.frontend.controllers.extra;

import mastermedia.backend.BackendService;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TreeOfDirectories {

    String[] albumList;
    List<Directory> directoryList = new ArrayList<>();
    File[] images;

    int previousSize, actualSize;

    public String[] getAlbumList() {
        return albumList;
    }

    public void setAlbumList(String[] albumList) {
        this.albumList = albumList;
    }

    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }

    public File[] getImages() {
        return images;
    }

    public void setImages(File[] images) {
        this.images = images;
    }

    public TreeOfDirectories() {
        previousSize = 0;
        actualSize = 0;

        albumList = BackendService.getInstance().getFolderStructure().getSorted().list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }

        });

        previousSize = albumList.length;
        for (int i = 0; i < previousSize; i++) {

            Directory directory = new Directory();
            directory.setName(albumList[i]);
            directoryList.add(directory);

        }

        for (int i = 0; i < directoryList.size(); i++) {

            String album = albumList[i];
            File albumFolder = new File(BackendService.getInstance().getFolderStructure().getSorted().getPath(), album);
            images = albumFolder.listFiles();

            directoryList.get(i).setFileList(Arrays.stream(images).toList());

        }


    }

}
