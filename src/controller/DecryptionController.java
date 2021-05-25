package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class DecryptionController {

    public TextField txtCipher;
    public TextField txtKey;
    public TextField txtText;

    public void btnDecrypt_OnAction(ActionEvent actionEvent) {
        String cipherText = txtCipher.getText();
        String key = txtKey.getText();

        if(cipherText.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter valid cipher text", ButtonType.OK).show();
            txtText.requestFocus();
            return;
        }

        if(key.trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter valid key", ButtonType.OK).show();
            txtKey.requestFocus();
            return;
        }



        String text = "";
        for (int i = 0; i < cipherText.length(); i++) {
            int code  = cipherText.charAt(i);
            code -=10000;
            char orgChar=(char) code;
            text += orgChar;
        }

        String originalText="";
        for (int i = text.length()-1; i >= 0; i--) {
            originalText += text.charAt(i);
        }

        originalText = originalText.substring(0,originalText.length() - key.length());
        txtText.setText(originalText);

    }
}
