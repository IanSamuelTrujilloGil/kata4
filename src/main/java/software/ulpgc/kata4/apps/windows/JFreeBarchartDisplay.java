package software.ulpgc.kata4.apps.windows;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.view.BarchartDisplay;

import javax.swing.*;
import java.awt.*;

public class JFreeBarchartDisplay extends JPanel implements BarchartDisplay {

    public JFreeBarchartDisplay(){
        this.setLayout(new BorderLayout());
    }

    @Override
    public void show(Barchart barchart) {
        this.removeAll();
        this.add(new ChartPanel(adapt(barchart)));
        this.revalidate();
    }

    private JFreeChart adapt(Barchart barchart) {
        return JFreeBarchartAdapter.adapt(barchart);
    }
}
