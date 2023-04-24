package mastermedia.backend.ffmpeg;

public class WindowsFFMPEGDownloader extends FFMPEGDownloader {

    private static final String BINARY_URL = "https://www.gyan.dev/ffmpeg/builds/ffmpeg-git-essentials.7z";
    private static final String SUM_URL = "https://www.gyan.dev/ffmpeg/builds/ffmpeg-git-essentials.7z.sha256";

    public WindowsFFMPEGDownloader() {

        super(BINARY_URL, SUM_URL);

    }

}
