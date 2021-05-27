package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.crypto.Crypto;


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

        try{
            txtText.setText(Crypto.decrypt(cipherText,key));
        }catch(RuntimeException exception){
            new Alert(Alert.AlertType.ERROR,exception.getMessage(),ButtonType.OK).show();
            txtKey.requestFocus();
        }
    }
}
