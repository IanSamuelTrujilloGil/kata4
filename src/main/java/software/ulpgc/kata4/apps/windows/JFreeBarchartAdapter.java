package software.ulpgc.kata4.apps.windows;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.architecture.model.Barchart;


public class JFreeBarchartAdapter {
    public static JFreeChart adapt(Barchart barchart) {
        JFreeChart chart = ChartFactory.createBarChart(
                barchart.getTitle(),
                barchart.getxAxis(),
                barchart.getyAxis(),
                dataOf(barchart)
        );

        setVerticalCategoryLabel(chart);
        return chart;
    }

    private static CategoryDataset dataOf(Barchart barchart) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String category : barchart.keySet()) {
            dataset.addValue(barchart.get(category),"", category );
        }
        return  dataset;
    }

    private static void setVerticalCategoryLabel(JFreeChart chart) {
        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    }
}
