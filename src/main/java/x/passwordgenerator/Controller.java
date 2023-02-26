package x.passwordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.Random;

public class Controller {
    @FXML
    private TextField passwordBox;

    // save state of this all
    @FXML
    private Slider lengthSlider;

    @FXML
    private Spinner<Integer> lengthSpinner;

    public void initialize() {
        SpinnerValueFactory<Integer> WhyDoWeNeedThisValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64);
        WhyDoWeNeedThisValueFactory.setValue(length);
        lengthSpinner.setValueFactory(WhyDoWeNeedThisValueFactory);

        lengthSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            length = newValue;
            lengthSlider.setValue(length);
            updateCharactersPool();
        });

        lengthSlider.valueProperty().addListener((ObservableValue,oldValue,newValue)-> {
            length = newValue.intValue();
            WhyDoWeNeedThisValueFactory.setValue(length);
            updateCharactersPool();
        });

        updateCharactersPool();
    }

    String charactersPool = "";

    private boolean lowercaseLetters = true;
    private boolean uppercaseLetters = true;
    private boolean specialCharacters = true;
    private boolean digits = true;

    private int length = 20;

    @FXML
    void toggleSpecialCharacters() { specialCharacters = !specialCharacters; updateCharactersPool(); }
    @FXML
    void toggleUppercaseLetters() { uppercaseLetters = !uppercaseLetters; updateCharactersPool(); }
    @FXML
    void toggleLowercaseLetters() { lowercaseLetters = !lowercaseLetters; updateCharactersPool(); }
    @FXML
    void toggleDigits() { digits = !digits; updateCharactersPool(); }


    private Clipboard clipboard = Clipboard.getSystemClipboard();
    private ClipboardContent content = new ClipboardContent();
    @FXML
    void copyPassword() {
        content.putString(passwordBox.getText());
        clipboard.setContent(content);
    }

    @FXML
    void updateCharactersPool() {
        charactersPool = "";
        if (lowercaseLetters) charactersPool = charactersPool.concat("abcdefghijklmnopqrstuvwxyz");
        if (uppercaseLetters) charactersPool = charactersPool.concat("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (specialCharacters) charactersPool = charactersPool.concat("!@#$%^&*");
        if (digits) charactersPool = charactersPool.concat("123456790");
        passwordBox.setText(randomString(charactersPool, length));
    }

    private final Random randomGenerator = new Random();
    String randomString(String characters, int length) {
        if (characters.length() == 0) {
            return "";
        }

        StringBuilder fsb = new StringBuilder();
        int n = characters.length() - 1;
        for (int i = 0; i < length; i++) {
            fsb.append(characters.charAt(randomGenerator.nextInt(n)));
        }
        return fsb.toString();
    }
}
