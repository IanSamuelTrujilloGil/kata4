package software.ulpgc.kata4.architecture.io;



import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.model.MapToBarchartBuilder;

import java.util.Map;

public class ProfessionBarchartLoader implements BarchartLoader{
    private final Map<String, Integer> professionToPeopleCount;
    private final Map<String, Integer> professionCountToPeopleCount;

    public ProfessionBarchartLoader(Map<String, Integer> professionToPeopleCount, Map<String, Integer> professionCountToPeopleCount) {
        this.professionToPeopleCount = professionToPeopleCount;
        this.professionCountToPeopleCount = professionCountToPeopleCount;
    }

    @Override
    public Barchart load(int id) {
        return switch (id){
          case 0 -> createBarchart("Distribution of people by profession", "Professions", "Number of people",
                  professionToPeopleCount);
          case 1 -> createBarchart("Distribution of people by profession Count", "Number of professions",
                  "Number of people", professionCountToPeopleCount);
            default -> null;
        };
    }

    private Barchart createBarchart(String title, String xAxis, String yAxis, Map<String, Integer> categoryCount) {
        return new MapToBarchartBuilder(title,xAxis,yAxis, categoryCount).build();
    }
}
