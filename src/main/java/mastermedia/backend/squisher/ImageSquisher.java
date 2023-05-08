package mastermedia.backend.squisher;

import java.io.File;
import java.io.IOException;

import mastermedia.backend.settings.Thumbnails;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

public class ImageSquisher implements IFileSquisher, IFolderSquisher {

    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private Thumbnails options;

    public ImageSquisher(FFmpeg ffmpeg, FFprobe ffprobe, Thumbnails options) {

        this.ffmpeg = ffmpeg;
        this.ffprobe = ffprobe;
        this.options = options;

    }

    @Override
    public boolean squishFile(File source, File destination) throws IOException {

        FFmpegProbeResult input = ffprobe.probe(source.getAbsolutePath());
        FFmpegStream stream = input.getStreams().get(0);

        String width, height;

        if(stream.width > stream.height) {

            width = String.valueOf(options.getMaxWidth());
            height = "-1";

        }else {

            width = "-1";
            height = String.valueOf(options.getMaxHeight());

        }

        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput(input)
            .addOutput(destination.getAbsolutePath().substring(0, destination.getAbsolutePath().lastIndexOf('.')) + ".jpg")
            .setVideoFilter("scale=" + width + ":" + height)
            .setVideoQuality(options.getQuality())
            .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);

        executor.createJob(builder, e -> {
            System.out.println(e);
        }).run();

        return false;

    }

    @Override
    public boolean squishFolder(File source, File destination) {

        return false;

    }

}
