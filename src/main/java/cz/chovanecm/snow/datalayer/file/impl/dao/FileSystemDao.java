package cz.chovanecm.snow.datalayer.file.impl.dao;

import cz.chovanecm.snow.datalayer.GenericDao;
import cz.chovanecm.snow.records.SnowScript;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemDao implements GenericDao<SnowScript> {

    @Getter
    private final FileLocator fileLocator;


    public FileSystemDao(FileLocator fileLocator) {
        this.fileLocator = fileLocator;
    }

    @Override
    public SnowScript get(String id) {
        try {
            String name = getNameById(id);
            String category = getFileLocator().getCategoryById(id);
            String content = null;
            content = getContentById(id);
            return createSnowScript(id, category, name, content);
        } catch (IOException e) {
            //TODO FIXME
            throw new UnsupportedOperationException(e);
        }
    }

    private SnowScript createSnowScript(String id, String category, String name, String content) {
        SnowScript script = new SnowScript(id, name, content);
        script.setCategory(category);
        return script;
    }

    private String getContentById(String id) throws IOException {
        Path path = getFileLocator().getFileById(id);
        return String.join(System.lineSeparator(), Files.readAllLines(path));
    }

    private String getNameById(String id) {
        //TODO
        return "TBD";
    }

    @Override
    public Iterable<SnowScript> getAll() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
