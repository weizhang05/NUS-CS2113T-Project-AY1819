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

    public ChartPanel(ObservableMap<String, Integer> ageData, ObservableMap<String, Integer> majorData,
                      ObservableMap<String, Integer> sexData) {
        super(FXML);

        ObservableList<PieChart.Data> agePieChartData = FXCollections.observableArrayList();
        for (String key : ageData.keySet()) {
            agePieChartData.add(new PieChart.Data(key, ageData.get(key)));
        }
        agePieChart.setData(agePieChartData);

        ObservableList<PieChart.Data> majorPieChartData = FXCollections.observableArrayList();
        for (String key : majorData.keySet()) {
            majorPieChartData.add(new PieChart.Data(key, majorData.get(key)));
        }
        majorPieChart.setData(majorPieChartData);

        ObservableList<PieChart.Data> sexPieChartData = FXCollections.observableArrayList();
        for (String key : sexData.keySet()) {
            sexPieChartData.add(new PieChart.Data(key, sexData.get(key)));
        }
        sexPieChart.setData(sexPieChartData);
    }

    /**
     * Update data
     */
    public void updateChartPanel(ObservableMap<String, Integer> ageData, ObservableMap<String, Integer> majorData,
                                 ObservableMap<String, Integer> sexData) {
        ObservableList<PieChart.Data> agePieChartData = FXCollections.observableArrayList();
        for (String key : ageData.keySet()) {
            agePieChartData.add(new PieChart.Data(key, ageData.get(key)));
        }
        agePieChart.setData(agePieChartData);

        ObservableList<PieChart.Data> majorPieChartData = FXCollections.observableArrayList();
        for (String key : majorData.keySet()) {
            majorPieChartData.add(new PieChart.Data(key, majorData.get(key)));
        }
        majorPieChart.setData(majorPieChartData);

        ObservableList<PieChart.Data> sexPieChartData = FXCollections.observableArrayList();
        for (String key : sexData.keySet()) {
            sexPieChartData.add(new PieChart.Data(key, sexData.get(key)));
        }
        sexPieChart.setData(sexPieChartData);
    }
}
