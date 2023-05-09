package mastermedia.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import mastermedia.backend.settings.Settings;

public class FileManager {

    private Settings settings;

    public void checkFiles() {

        File configFile = new File("config.yml");

        // FIXME: debug only
        if(/*!configFile.exists()*/ true) {

            InputStream configFileResourceStream = this.getClass().getResourceAsStream("config.yml");

            try {

                FileOutputStream configFileOutputStream = new FileOutputStream(configFile);
                configFileResourceStream.transferTo(configFileOutputStream);

            }catch(IOException e) {

                e.printStackTrace();

            }

        }

        try {

            Constructor c =  new Constructor(Settings.class, new LoaderOptions());
            Yaml yaml = new Yaml(c);

            settings = yaml.load(new FileInputStream(configFile));

        }catch(FileNotFoundException e) {

            e.printStackTrace();

        }

    }

    public Settings getSettings() { return settings; }

}
