package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;
import software.ulpgc.kata4.architecture.model.FilmIndustryPersonDeserializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileFilmIndustryPersonReader implements FilmIndustryPersonReader{
    private final BufferedReader reader;
    private final FilmIndustryPersonDeserializer deserializer;

    public FileFilmIndustryPersonReader(File file, FilmIndustryPersonDeserializer deserializer) throws IOException {
        this.reader = readerOf(file);
        this.deserializer = deserializer;
        this.skipHeader();
    }

    private BufferedReader readerOf(File file) throws IOException {
        return new BufferedReader( new FileReader(file));
    }

    private void skipHeader() throws IOException {
        this.reader.readLine();
    }

    @Override
    public FilmIndustryPerson read() throws IOException {
        return deserialize(reader.readLine());
    }

    private FilmIndustryPerson deserialize(String line) {
        return line != null ? deserializer.deserialize(line) : null;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}