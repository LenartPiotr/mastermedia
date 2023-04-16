package mastermedia.backend.settings;

public class Toenails {

    private int maxWidth;
    private int maxHeight;
    private int bitrate;
    private String fps;

    public int getMaxWidth() { return maxWidth; }

    public void setMaxWidth(int maxWidth) { this.maxWidth = maxWidth; }

    public int getMaxHeight() { return maxHeight; }

    public void setMaxHeight(int maxHeight) { this.maxHeight = maxHeight; }

    public int getBitrate() { return bitrate; }

    public void setBitrate(int bitrate) { this.bitrate = bitrate; }

    public String getFps() { return fps; }

    public void setFps(String fps) { this.fps = fps; }

    @Override
    public String toString() {

        return "Toenails [maxWidth=" + maxWidth + ", maxHeight=" + maxHeight + ", bitrate=" + bitrate + ", fps=" +
               fps + "]";

    }

}
