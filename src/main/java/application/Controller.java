package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import sentence.Sentence;
import util.Percentages;

public class Controller {
    @FXML
    private Label latinSentenceLabel;
    @FXML
    private Label englishSentenceLabel;

    @FXML
    private TabPane tabPane;

    @FXML
    private Slider interrogative, explicitSubject, thirdPerson, firstPerson, recursiveAdjective;
    @FXML
    private Label interrogativeLbl, explicitSubjectLbl, thirdPersonLbl, firstPersonLbl, recursiveAdjectiveLbl;

    public void initialize() {
        tabPane.getSelectionModel().select(1);

        initSliders();
    }

    private void initSliders() {
        // TODO make thd slider be set to the right amount on init
        // TODO make sliders controlled with a pseudo-registry or smth in the Percentages class
        interrogative.valueProperty().setValue(Percentages.interrogative_chance);
        interrogative.valueProperty().addListener((observable, oldValue, newValue) -> {
            Percentages.interrogative_chance = newValue.intValue() / 100d;
            updateSliderLabels();
        });

        updateSliderLabels();
    }
    private void updateSliderLabels() {
        interrogativeLbl.setText((int) (Percentages.interrogative_chance * 100) + "%");
    }


    @FXML
    private void generateSentence(ActionEvent event) {
        Sentence sentence = new Sentence();
        latinSentenceLabel.setText(sentence.assembleLatin());
        englishSentenceLabel.setText(sentence.assembleEnglish());
    }
}
