/**
 * Author: Soft Red (Jeffery Yang, Dylan Wheeler, Steven Loppe, Thien-Kim Nguyen, Hafsa Zia) - 2020
 * Created for SENG 300 at The University of Calgary during the winter semester of 2020
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.JournalListPane;

public class Main extends Application{

    Scene login_scene, researcher_scene, reviewer_scene, editor_scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Journal Submission System");

        /*
        * Populate the login page
        */
        BorderPane login_layout = new BorderPane();

        TextField login_tf = new TextField();

        Button login_b = new Button("Press Me");
        // TODO: set this to have logic based on the account given as input
        login_b.setOnAction(e -> {
            primaryStage.setScene(researcher_scene);
        });

        login_layout.setTop(login_b);
        login_layout.setCenter(login_tf);
        login_scene = new Scene(login_layout, 800, 600);


        /*
        * Populate the Researcher User page
        */

        ResearcherPane researcher_layout = new ResearcherPane(primaryStage);
        researcher_scene = new Scene(researcher_layout.getPane(), 600, 800);


        /*
         * Populate the Reviewer User page
         */

        ReviewerPane reviewer_layout = new ReviewerPane(primaryStage);




        reviewer_scene = new Scene(reviewer_layout.getPane(), 600, 800);



        /*
         * Populate the Editor User page
         */

        EditorPane editor_layout = new EditorPane(primaryStage);

        editor_scene = new Scene(editor_layout.getPane(), 600, 800);



        primaryStage.setScene(login_scene);
        primaryStage.show();
        
        
        //TEMP
        JournalListPane jlp = new JournalListPane(primaryStage);
        Scene journalListScene = new Scene(jlp, 600, 600);
        primaryStage.setScene(journalListScene);
        primaryStage.show();
    }

    public User[] getAccounts() {
        // Gets accounts from a file and returns them all as an array
        // TODO: read accounts from a separate file, call methods in each user class to get values as objects
        User[] accounts = new User[3];

        return accounts;
    }

    public void addAccount(User aUser) {
        // TODO: access accounts file and save this user to a new line

    }

    public void removeAccount(User aUser) {
        // Should this take the user or something like an ID or index
    }

}
