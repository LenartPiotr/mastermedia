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
    private final File configFile;

    public FileManager(){
        configFile = new File("config.yml");
    }

    public File getConfigFile(){
        return configFile;
    }

    public void checkFiles() {

        // FIXME: debug only
        // Change to false due to getting file config from local path not from resources
        if(/*!configFile.exists()*/ false) {

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
