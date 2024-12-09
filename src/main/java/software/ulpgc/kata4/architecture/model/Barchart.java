package software.ulpgc.kata4.architecture.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Barchart {
    private final String title;
    private final String xAxis;
    private final String yAxis;
    private final Map<String, Integer> categoryCount;

    public Barchart(String title, String xAxis, String yAxis) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.categoryCount = new TreeMap<>();
    }

    public Integer get(String category) {
        return categoryCount.get(category);
    }

    public Integer put(String category, Integer value) {
        return categoryCount.put(category, value);
    }

    public Set<String> keySet() {
        return categoryCount.keySet();
    }

    public String getTitle() {
        return title;
    }

    public String getxAxis() {
        return xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }
}
