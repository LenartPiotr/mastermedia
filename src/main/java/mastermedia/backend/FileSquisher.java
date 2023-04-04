package mastermedia.backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileSquisher {

    public class Options {

        public int maxWidth;
        public int maxHeight;
        public boolean maintainAspectRatio;

    };

    private String path;
    private Options default_options = new Options();
    {

        default_options.maxWidth = 500;
        default_options.maxHeight = 500;
        default_options.maintainAspectRatio = true;

    }

    public FileSquisher(String path) {

        this.path = path;

    }

    public BufferedImage squish(File file) {

        return squish(file, default_options);

    }

    public BufferedImage squish(File file, Options options) {

        try {

            return squish(ImageIO.read(file), options);

        }catch(IOException e) {

            return null;

        }

    }

    public BufferedImage squish(BufferedImage image, Options options) {

        return null;

    }

    public void squish() {

        File directory = new File(path);

        if(!directory.isDirectory()) return;

        for(File file : directory.listFiles()) {

        }

    }

}
