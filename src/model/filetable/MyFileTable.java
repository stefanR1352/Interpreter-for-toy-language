package model.filetable;

import exceptions.FileTableException;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFileTable implements MyIFileTable<StringValue, BufferedReader> {
    private final Map<StringValue, BufferedReader> fileTable = new HashMap<>();

    @Override
    public void add(StringValue key, BufferedReader value) throws FileTableException {
        if (fileTable.containsKey(key)) throw new FileTableException("File already opened");
        fileTable.put(key, value);
    }

    @Override
    public BufferedReader get(StringValue key) throws FileTableException {
        if (!fileTable.containsKey(key)) throw new FileTableException("File not found");
        return fileTable.get(key);
    }

    @Override
    public void remove(StringValue key) throws FileTableException {
        BufferedReader reader = fileTable.remove(key);
        if (reader == null) throw new FileTableException("File not found to close");
        try { reader.close(); } catch (IOException e) { throw new FileTableException("Error closing file"); }
    }

    @Override
    public boolean contains(StringValue key) { return fileTable.containsKey(key); }
}

