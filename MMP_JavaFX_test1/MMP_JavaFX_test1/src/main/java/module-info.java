module com.mmp.mmp_javafx_test1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.mmp.mmp_javafx_test1 to javafx.fxml;
    exports com.mmp.mmp_javafx_test1;
}