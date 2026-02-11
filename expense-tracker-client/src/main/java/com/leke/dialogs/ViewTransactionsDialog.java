package com.leke.dialogs;

import com.leke.Controller.DashboardController;
import com.leke.components.TransactionComponent;
import com.leke.models.Transaction;
import com.leke.utils.SqlUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.time.Month;
import java.util.List;

public class ViewTransactionsDialog extends CustomDialog{
    private DashboardController dashboardController;
    private String monthName;

    public ViewTransactionsDialog(DashboardController dashboardController, String monthName) {
        super(dashboardController.getUser());
        this.dashboardController = dashboardController;
        this.monthName = monthName;

        setTitle("View Transactions");
        setWidth(815);
        setHeight(500);

        ScrollPane transactionScrollPane = createTransactionScrollPane();
        getDialogPane().setContent(transactionScrollPane);
    }

    private ScrollPane createTransactionScrollPane(){
        VBox vBox = new VBox(20);

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMinHeight(getHeight() - 40);
        scrollPane.setFitToWidth(true);

        List<Transaction> monthTransactions = SqlUtil.getAllTransactionsByUserId(
                dashboardController.getUser().getId(),
                dashboardController.getCurrentYear(),
                Month.valueOf(monthName).getValue()
        );

        if(monthTransactions != null){
            for(Transaction transaction : monthTransactions){
                TransactionComponent transactionComponent = new TransactionComponent(
                        dashboardController,
                        transaction
                );
                transactionComponent.getStyleClass().addAll("border-light-gray");

                vBox.getChildren().add(transactionComponent);
            }
        }

        return scrollPane;
    }
}
