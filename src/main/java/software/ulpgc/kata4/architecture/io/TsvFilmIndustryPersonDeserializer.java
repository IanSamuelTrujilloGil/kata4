package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;
import software.ulpgc.kata4.architecture.model.FilmIndustryPersonDeserializer;

public class TsvFilmIndustryPersonDeserializer implements FilmIndustryPersonDeserializer {

    @Override
    public FilmIndustryPerson deserialize(String text) {
        return deserialize(text.split("\t"));
    }

    private FilmIndustryPerson deserialize(String[] fields) {
        return new FilmIndustryPerson(fields[0],
                fields[1],
                toInt(fields[2]),
                toInt(fields[3]));
    }


    private int toInt(String field) {
        return field.equals("\\N")? -1:Integer.parseInt(field);
    }
}