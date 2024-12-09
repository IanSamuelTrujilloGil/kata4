package software.ulpgc.kata4.architecture.model;

import java.util.Map;

public class MapToBarchartBuilder implements BarchartBuilder{
    private final String title;
    private final String xAxis;
    private final String yAxis;
    private final Map<String, Integer> categoryCount;

    public MapToBarchartBuilder(String title, String xAxis, String yAxis, Map<String, Integer> categoryCount) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.categoryCount = categoryCount;
    }

    @Override
    public Barchart build() {
        Barchart barchart = new Barchart(title,xAxis, yAxis);
        for (String category : categoryCount.keySet()) {
            barchart.put(category, categoryCount.get(category));
        }
        return barchart;
    }
}
