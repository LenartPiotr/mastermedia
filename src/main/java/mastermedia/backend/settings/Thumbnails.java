package mastermedia.backend.settings;

public class Thumbnails {

    private int maxWidth;
    private int maxHeight;
    private int quality;

    public int getMaxWidth() { return maxWidth; }

    public void setMaxWidth(int maxWidth) { this.maxWidth = maxWidth; }

    public int getMaxHeight() { return maxHeight; }

    public void setMaxHeight(int maxHeight) { this.maxHeight = maxHeight; }

    public int getQuality() { return quality; }

    public void setQuality(int quality) { this.quality = quality; }

    @Override
    public String toString() {

        return "Thumbnails [maxWidth=" + maxWidth + ", maxHeight=" + maxHeight + ", quality=" + quality + "]";

    }

}
