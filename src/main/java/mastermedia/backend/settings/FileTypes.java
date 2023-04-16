package mastermedia.backend.settings;

import java.util.List;

public class FileTypes {

    private List<String> video;
    private List<String> image;

    public List<String> getVideo() { return video; }

    public void setVideo(List<String> video) { this.video = video; }

    public List<String> getImage() { return image; }

    public void setImage(List<String> image) { this.image = image; }

    @Override
    public String toString() { return "FileTypes [video=" + video + ", audio=" + image + "]"; }

}
