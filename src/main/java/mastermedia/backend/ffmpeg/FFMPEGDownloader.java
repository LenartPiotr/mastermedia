package mastermedia.backend.ffmpeg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public abstract class FFMPEGDownloader {

    private URL binaryUrl;
    private URL checksumUrl;

    protected FFMPEGDownloader(String binaryUrl, String checksumUrl) {

        try {

            this.binaryUrl = new URL(binaryUrl);
            this.checksumUrl = new URL(checksumUrl);

        }catch(MalformedURLException e) {

            e.printStackTrace();

        }

    }

    public void download(File destination) {

        try {

            // ReadableByteChannel sumChannel = Channels.newChannel(checksumUrl.openStream());
            // String sum = new BufferedReader(Channels.newReader(sumChannel, "utf-8")).lines().parallel()
            //     .collect(Collectors.joining("\n"));

            ReadableByteChannel binaryChannel = Channels.newChannel(binaryUrl.openStream());
            FileOutputStream output = new FileOutputStream(new File(destination, binaryUrl.toString().substring(binaryUrl.toString().lastIndexOf("/"))));
            FileChannel fileChannel = output.getChannel();
            fileChannel.transferFrom(binaryChannel, 0, Long.MAX_VALUE);
            output.close();

            // int count;
            // MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // BufferedInputStream bis = new BufferedInputStream(new FileInputStream(destination));

        }catch(IOException /* | NoSuchAlgorithmException */ e) {

            e.printStackTrace();

        }

    }

}
