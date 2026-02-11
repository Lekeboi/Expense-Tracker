package com.leke.dialogs;

import com.leke.Controller.DashboardController;
import com.leke.components.CategoryComponent;
import com.leke.models.TransactionCategory;
import com.leke.models.User;
import com.leke.utils.SqlUtil;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ViewOrEditTransactionCategoryDialog extends CustomDialog{
    private DashboardController dashboardController;

    public ViewOrEditTransactionCategoryDialog(User user, DashboardController dashboardController){
        super(user);
        this.dashboardController = dashboardController;

        setTitle("View Category");
        setWidth(815);
        setHeight(500);

        ScrollPane mainContainer = createMainContainerContent();
        getDialogPane().setContent(mainContainer);
    }

    private ScrollPane createMainContainerContent(){
        VBox dialogVbox = new VBox(20);

        ScrollPane scrollPane = new ScrollPane(dialogVbox);
        scrollPane.setMinHeight(getHeight() - 40);
        scrollPane.setFitToWidth(true);

        List<TransactionCategory> transactionCategories = SqlUtil.getAllTransactionCategoriesByUser(user);
        for (TransactionCategory transactionCategory : transactionCategories){
            CategoryComponent categoryComponent = new CategoryComponent(dashboardController, transactionCategory);
            dialogVbox.getChildren().add(categoryComponent);
        }
        return scrollPane;
    }
}
