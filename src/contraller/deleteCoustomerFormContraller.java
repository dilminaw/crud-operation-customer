package contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class deleteCoustomerFormContraller {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSallary;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id = ? ");
            preparedStatement.setString(1,txtID.getText());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "1234");

            // Correct SQL query with parameter placeholder
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE id = ?");
            preparedStatement.setString(1, txtID.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                txtName.setText(resultSet.getString("name"));
                txtAddress.setText(resultSet.getString("address"));
                txtSallary.setText(String.valueOf(resultSet.getDouble("sallary")));
            } else {
                // Optional: handle case where ID is not found
                txtName.setText("");
                txtAddress.setText("");
                txtSallary.setText("");
                System.out.println("No customer found with ID: " + txtID.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
