package util;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Percentages {
    // verbs
    public static PercentageSlider interrogative_chance;
    public static PercentageSlider explicit_subject_chance;

    public static PercentageSlider third_person_chance;
    public static PercentageSlider first_person_chance; // if third person chance fails, then this is checked


    // nouns
    public static PercentageSlider compound_noun_phrase_chance;

    // adjectives
    public static PercentageSlider recursive_adjective_chance;


    // adverbs
    public static PercentageSlider recursive_adverb_chance;


    public static void init(Pane scrollPane) {
        PercentageSlider.sliderPane = scrollPane;

        interrogative_chance = new PercentageSlider(0.1, "Interrogative Chance");
        explicit_subject_chance = new PercentageSlider(0.8, "Explicit Subject Chance");
        third_person_chance = new PercentageSlider(0.6, "Third Person Chance");
        first_person_chance = new PercentageSlider(0.7, "First Person Chance");
        compound_noun_phrase_chance = new PercentageSlider(0.4, "Compound Noun Phrase Chance");
        recursive_adjective_chance = new PercentageSlider(0.5, "Recursive Adjective Count Chance");
        recursive_adverb_chance = new PercentageSlider(0.5, "Recursive Adverb Count Chance");
    }

    public static class PercentageSlider {
        public static int nextSliderY = 30;
        public static Pane sliderPane;

        public double value;
        @FXML
        private final Slider slider;
        @FXML
        private final Label label;
        @FXML
        private final Label percentageLabel;

        public PercentageSlider(double defaultValue, String name) {
            value = defaultValue;


            slider = new Slider();
            slider.setValue(value * 100);
            slider.setBlockIncrement(1);
            slider.setLayoutX(12);
            slider.setLayoutY(nextSliderY);
            slider.setMajorTickUnit(10);
            slider.setMinorTickCount(9);
            slider.setPrefHeight(38);
            slider.setPrefWidth(615);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setSnapToTicks(true);

            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                value = newValue.intValue() / 100d;
                updateLabel();
            });


            label = new Label();
            label.setLayoutX(14);
            label.setLayoutY(nextSliderY - 26);
            label.setPrefHeight(27);
            label.setPrefWidth(500);
            label.setFont(new Font(18));
            label.setText(name);

            percentageLabel = new Label();
            percentageLabel.setLayoutX(590);
            percentageLabel.setLayoutY(nextSliderY - 26);
            percentageLabel.setPrefHeight(27);
            percentageLabel.setPrefWidth(206);
            percentageLabel.setFont(new Font(18));
            percentageLabel.setTextAlignment(TextAlignment.RIGHT);
            percentageLabel.setText("%");


            sliderPane.getChildren().add(slider);
            sliderPane.getChildren().add(label);
            sliderPane.getChildren().add(percentageLabel);


            nextSliderY += 70;
            sliderPane.setPrefHeight(nextSliderY - 20);
            updateLabel();
        }

        private void updateLabel() {
            percentageLabel.setText((int) (value * 100) + "%");
        };
    }
}
