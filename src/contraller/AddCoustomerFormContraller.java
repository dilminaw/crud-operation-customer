package contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddCoustomerFormContraller extends Component {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSallary;

    @FXML
    void btnAddCoustomerOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double sallary = Double.parseDouble(txtSallary.getText());

        String sql= "insert into customer values(?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,sallary);


            int i = preparedStatement.executeUpdate();
            if (i>0){
              //  JOptionPane.showMessageDialog(this,"Added succesfully.");
                JOptionPane.showMessageDialog(null, "Customer added successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnCancleCoustomerOnAction(ActionEvent event) {

    }

}
