package com.leke.Controller;

import com.leke.utils.ApiUtil;
import com.leke.utils.SqlUtil;
import com.leke.utils.Utilitie;
import com.leke.views.DashboardView;
import com.leke.views.LoginView;
import com.leke.views.SignUpView;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
        initialize();
    }

    private void initialize(){
        loginView.getLoginButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!validateUser()) return;

                String email = loginView.getUsernameField().getText();
                String password = loginView.getPasswordField().getText();

                if(SqlUtil.postLoginUser(email, password)){
                    Utilitie.showAlertDialog(Alert.AlertType.INFORMATION, "Login successful");
                    new DashboardView(email).show();
                }else {
                    Utilitie.showAlertDialog(Alert.AlertType.ERROR, "Invalid credentials");
                };
            }
        });

        loginView.getSignupLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new SignUpView().show();
            }
        });
    }
    private boolean validateUser(){
        // empty username
        if(loginView.getUsernameField().getText().isEmpty()){
            return false;
        }

        // empty password
        if(loginView.getPasswordField().getText().isEmpty()){
            return false;
        }

        return true;
    }
}
