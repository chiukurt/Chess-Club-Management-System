/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

/*
FILE NAME: FXMLDocumentController.java
 Pramesh Khadka 101080018 
Kalvin Balasingam 101128371
Kurt Chiu 101190261
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField lName, fName;
    @FXML
    private Label errorLabel;
    @FXML
    private ListView memberList;

    MemberManager club;

    private boolean validateName(String s) { //Must contain anything besides numbers. False if any numbers.
        return !(s.equals("") || !s.matches("\\D+"));
    }

    @FXML
    private void addMemberClicked(ActionEvent event) {
        if (validateName(fName.getText())
                && validateName(lName.getText())
                && club.addMember(lName.getText(), fName.getText())) {
            updateForm();
            lName.setText("");
            fName.setText("");
            errorLabel.setText("Member added.");
        } else {
            if (!validateName(fName.getText()) || !validateName(lName.getText())) {
                errorLabel.setText("Failed. Cannot be empty or have numbers.");
            } else {
                errorLabel.setText("Failed. Full capacity.");
            }
        }

    }

    @FXML
    private void viewMembersClicked(ActionEvent event) {
        updateForm();
    }

    @FXML
    private void removeMemberClicked(ActionEvent event) {
        String s;
        if (memberList.getSelectionModel().getSelectedItem() != null) {
            s = memberList.getSelectionModel().getSelectedItem().toString();
            s = s.substring(0, s.indexOf(" "));

            club.deleteMember(Integer.parseInt(s));
            updateForm();
            errorLabel.setText("Member removed.");
        } else {
            errorLabel.setText("Please select a member.");
        }
    }

    @FXML
    private void bestPlayersClicked(ActionEvent event) {
        memberList.getItems().clear();
        String s = club.getBestPlayer();
        String[] rows = s.split("\n");
        for (int i = 0; i < rows.length; i++) {
            memberList.getItems().add(rows[i]);
        }
        errorLabel.setText("");
    }

    @FXML
    private void mostWinsClicked(ActionEvent event) {
        memberList.getItems().clear();
        String s = club.getMostWins();
        String[] rows = s.split("\n");
        for (int i = 0; i < rows.length; i++) {
            memberList.getItems().add(rows[i]);
        }
        errorLabel.setText("");
    }

    @FXML
    private void addWin(ActionEvent event) {
        String s;
        int index;
        if (memberList.getSelectionModel().getSelectedItem() != null) {
            index = memberList.getSelectionModel().getSelectedIndex();
            s = memberList.getSelectionModel().getSelectedItem().toString();
            s = s.substring(0, s.indexOf(" "));

            club.addWin(Integer.parseInt(s));
            updateForm();
            errorLabel.setText("Win recorded.");
            memberList.getSelectionModel().select(index);
        } else {
            errorLabel.setText("Please select a member.");
        }
    }

    @FXML
    private void addLoss(ActionEvent event) {
        String s;
        int index;
        if (memberList.getSelectionModel().getSelectedItem() != null) {
            index = memberList.getSelectionModel().getSelectedIndex();
            s = memberList.getSelectionModel().getSelectedItem().toString();
            s = s.substring(0, s.indexOf(" "));

            club.addLoss(Integer.parseInt(s));
            updateForm();
            errorLabel.setText("Loss recorded.");
            memberList.getSelectionModel().select(index);
        } else {
            errorLabel.setText("Please select a member.");
        }
    }

    public void updateForm() {
        memberList.getItems().clear();

        String s = club.listMembers();
        String[] rows = s.split("\n");
        for (int i = 0; i < rows.length; i++) {
            memberList.getItems().add(rows[i]);
        }

        errorLabel.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        club = new MemberManager(30, 100);
        club.addMember("Magnus", "Carlsen");
        club.addMember("Garry", "Kasparov");
        club.addMember("Deep", "Blue");
        club.addMember("Stockfish", "AI");
        updateForm();

    }

}
