package app.src.main.java.azure.conditional.access.policy.visualizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PolicyVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        String jsonInput = // your JSON input here
                ObjectMapper objectMapper = new ObjectMapper();
        Policies policies = null;

        try {
            policies = objectMapper.readValue(jsonInput, Policies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane policyPane = new Pane();
        ScrollPane scrollPane = new ScrollPane(policyPane);
        Scene scene = new Scene(scrollPane, 800, 600);

        if (policies != null) {
            Map<String, Circle> locationCircles = new HashMap<>();
            double offsetX = 50;
            double offsetY = 50;

            for (Policy policy : policies.getValue()) {
                Conditions conditions = policy.getConditions();
                Locations locations = conditions.getLocations();

                for (String locationId : locations.getIncludeLocations()) {
                    if (!locationCircles.containsKey(locationId)) {
                        Circle circle = new Circle(offsetX, offsetY, 30, Color.TRANSPARENT);
                        circle.setStroke(Color.BLACK);
                        locationCircles.put(locationId, circle);
                        policyPane.getChildren().add(circle);
                        offsetX += 100;
                    }
                }

                for (String locationId : locations.getExcludeLocations()) {
                    if (!locationCircles.containsKey(locationId)) {
                        Circle circle = new Circle(offsetX, offsetY, 30, Color.TRANSPARENT);
                        circle.setStroke(Color.RED);
                        locationCircles.put(locationId, circle);
                        policyPane.getChildren().add(circle);
                        offsetX += 100;
                    }
                }
            }
        }

        primaryStage.setTitle("Azure Conditional Access Policy Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
