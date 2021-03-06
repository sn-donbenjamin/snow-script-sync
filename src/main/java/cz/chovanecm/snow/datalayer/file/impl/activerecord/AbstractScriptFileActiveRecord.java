package cz.chovanecm.snow.datalayer.file.impl.activerecord;

import cz.chovanecm.snow.datalayer.ActiveRecord;
import cz.chovanecm.snow.datalayer.file.impl.File;
import cz.chovanecm.snow.datalayer.file.impl.FileWriter;
import cz.chovanecm.snow.records.SnowScript;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractScriptFileActiveRecord implements ActiveRecord {
    private FileWriter fileWriter = new FileWriter();

    @Override
    public void save() {
        Path file = getFilePath();
        try {
            getFileWriter().writeFile(File.builder()
                    .filePath(file)
                    .textContent(getRecord().getScript())
                    .lastModified(getRecord().getUpdatedOn())
                    .build());
        } catch (IOException e) {
            //FIXME
            e.printStackTrace();
        }
    }

    public abstract Path getFilePath();

    public abstract SnowScript getRecord();


    public FileWriter getFileWriter() {
        return this.fileWriter;
    }
}
