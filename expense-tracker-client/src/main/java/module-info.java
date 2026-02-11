module expense.tracker.client {
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.graphics;

    //crucial to read data from models and use in our tables
    opens com.leke.models to javafx.base;


    exports com.leke;
}