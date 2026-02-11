package com.leke.Controller;

import com.google.gson.JsonObject;
import com.leke.utils.SqlUtil;
import com.leke.utils.Utilitie;
import com.leke.views.LoginView;
import com.leke.views.SignUpView;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class SignUpController {
    private SignUpView signUpView;

    public SignUpController(SignUpView signUpView){
        this.signUpView = signUpView;
        initialize();
    }

    private void initialize(){
           signUpView.getLoginLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   new LoginView().show();
               }
           });

           signUpView.getRegisterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   if (!validateInput()){
                       Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Invalid input");
                       return;
                   }

                   String name = signUpView.getNameField().getText();
                   String username = signUpView.getUsernameField().getText();
                   String password = signUpView.getPasswordField().getText();

                   JsonObject jsonData = new JsonObject();
                   jsonData.addProperty("name", name);
                   jsonData.addProperty("email", username);
                   jsonData.addProperty("password", password);

                   boolean postCreateAccountStatus = SqlUtil.postCreateUser(jsonData);

                   if (postCreateAccountStatus){
                       Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Account created successfully");
                       new LoginView().show();
                   }else {
                       Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Failed to create account");
                   }

               }
           });
    }

    private boolean validateInput(){
        if (signUpView.getNameField().getText().isEmpty()){
            return false;
        }
        if(signUpView.getUsernameField().getText().isEmpty()) return false;
        if(signUpView.getPasswordField().getText().isEmpty()) return false;
        if(signUpView.getRePasswordField().getText().isEmpty()) return false;
        if(!signUpView.getPasswordField().getText().equals(signUpView.getRePasswordField().getText())) return false;

        if(SqlUtil.getUserByEmail(signUpView.getUsernameField().getText()) != null) return false;
        return true;
    }
}
