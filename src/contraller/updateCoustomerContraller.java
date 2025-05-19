package contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.*;

public class updateCoustomerContraller {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtsallary;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {






        String sql = "UPDATE customer SET name=?, address=?, sallary=? WHERE id=?";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade","root","1234");


            PreparedStatement ps = connection.prepareStatement(sql);
         //   PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, txtName.getText());
            ps.setString(2, txtAddress.getText());
            ps.setBigDecimal(3, new BigDecimal(txtsallary.getText()));
            ps.setString(4, txtID.getText());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

 public void BTNsearchOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/thogakade", "root", "1234");

            // Correct SQL query with parameter placeholder
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE id = ?");
            preparedStatement.setString(1, txtID.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                txtName.setText(resultSet.getString("name"));
                txtAddress.setText(resultSet.getString("address"));
                txtsallary.setText(String.valueOf(resultSet.getDouble("sallary")));
            } else {
                // Optional: handle case where ID is not found
                txtName.setText("");
                txtAddress.setText("");
                txtsallary.setText("");
                System.out.println("No customer found with ID: " + txtID.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
  }
    }

