package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 *  Controller for a pie chart
 */
public class ChartPanel extends UiPart<Region> {
    private static final String FXML = "ChartPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ChartPanel.class);

    @FXML
    private PieChart agePieChart;

    @FXML
    private PieChart majorPieChart;

    @FXML
    private PieChart sexPieChart;

    public ChartPanel() {
        super(FXML);
        agePieChart.setStartAngle(90);
        agePieChart.setClockwise(true);
        sexPieChart.setStartAngle(90);
        sexPieChart.setClockwise(true);
        majorPieChart.setStartAngle(90);
        majorPieChart.setClockwise(true);
    }

    /**
     * Update chart's data
     */
    public void updateChartPanel(ObservableMap<String, Integer> ageData, ObservableMap<String, Integer> majorData,
                                 ObservableMap<String, Integer> sexData) {
        agePieChart.setData(processedData(ageData));

        sexPieChart.setData(processedData(sexData));

        majorPieChart.setData(processedData(majorData));
    }

    /**
     * Update individual chart data
     */
    public ObservableList<PieChart.Data> processedData (ObservableMap<String, Integer> data) {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (String key : data.keySet()) {
            chartData.add(new PieChart.Data(key, data.get(key)));
        }
        return chartData;
    }
}
