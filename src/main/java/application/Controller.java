package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
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
    private ScrollPane sliderScrollPane;
    @FXML
    private Pane sliderPane;

    public void initialize() {
        tabPane.getSelectionModel().select(1);

        Percentages.init(sliderPane);
        sliderScrollPane.setContent(sliderPane);
    }


    @FXML
    private void generateSentence(ActionEvent event) {
        Sentence sentence = new Sentence();
        latinSentenceLabel.setText(sentence.assembleLatin());
        englishSentenceLabel.setText(sentence.assembleEnglish());
    }
}
