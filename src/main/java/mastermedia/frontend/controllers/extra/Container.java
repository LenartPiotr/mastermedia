package mastermedia.frontend.controllers.extra;

public class Container {
    private String directoryName;
    private FileType[] filetype = new FileType[10];


    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }


    public Container(String directoryName, FileType[] filetype) {
        this.directoryName = directoryName;
        this.filetype = filetype;

    }

    public FileType[] getFiletype() {
        return filetype;
    }

    public void setFiletype(FileType[] filetype) {
        this.filetype = filetype;
    }

    static public class FileType{
        public String name;
        public Properties properties ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public String[] getType() {
            return type;
        }

        public void setType(String[] type) {
            this.type = type;
        }

        private String[] type;

        public FileType(String name,String[] type, Properties properties ) {
            this.name = name;
            this.properties = properties;
            this.type = type;
        }

        static public class Properties{
            public int maxWidth;
            public  int maxHeight;
            public int extra;

            public Properties(int maxWidth, int maxHeight, int extra) {
                this.maxWidth = maxWidth;
                this.maxHeight = maxHeight;
                this.extra = extra;
            }
        }

    }
}
