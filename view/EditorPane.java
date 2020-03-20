package view;
import model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DataStore;
import model.Reviewer;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class   EditorPane extends BasePane {
    private Pane pane;
    private Pane pane2;
    private File entry;
    private Label fileDir;
    private Label pickR;
    public EditorPane(Stage ps){
    	super(ps, "Editor Pane");
    	
    	
        pane = new VBox();
        pane2 = new HBox();
        

        Label researcher_l = new Label("Editor");
        researcher_l.setTranslateY(-300);

        createSubmission(ps);
        
        DataStore db = DataStore.load();  
        ArrayList<Reviewer> reviewers = db.university.reviewers;
        //ArrayList<String> names = new ArrayList<String>();


       // ChoiceBox cb = new ChoiceBox();
       // for(Reviewer r : reviewers) {
         //      cb.getItems().add(r.name);
        //}
        
        
        ChoiceBox cb1 = new ChoiceBox(FXCollections.observableArrayList(
        	    "First", "Reviewers")
        	);
        cb1.setTranslateY(120);
        cb1.setTranslateX(275);
        
       ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList(
        	    "Second", "Reviewers")
        	);
       cb2.setTranslateY(140);
       cb2.setTranslateX(275);
       ChoiceBox cb3 = new ChoiceBox(FXCollections.observableArrayList(
       	    "Third", "Reviewers" )
       	);
       cb3.setTranslateY(160);
       cb3.setTranslateX(275);
 
   
       
        addChild(cb1);
        addChild(cb2);
        addChild(cb3);
        
        this.setCenter(pane);
   
        
        
    }

    private void addChild(Node child){
        pane.getChildren().addAll(child);

    };

    public void createSubmission(Stage ps){
        Button findBtn = new Button("Open File");
        findBtn.setTranslateY(100);
        findBtn.setTranslateX(200);
        // TODO: set this to have logic based on the account given as input
        findBtn.setOnAction(e -> {
            entry = selectFile(ps);
        });
        Button submitBtn = new Button("Submit");
        submitBtn.setTranslateY(100);
        submitBtn.setTranslateX(250);

        
        submitBtn.setOnAction(e -> {
            System.out.println("Saving. . .");
            try{
                saveFile(entry);
                System.out.println("Complete!");

            }catch (IOException error){
                error.printStackTrace();
            }
        });
        pickR = new Label("Select a Reviewer");
        pickR.setTranslateY(150);
        pickR.setTranslateX(-126);
        
        Button assignBtn = new Button("Assign");
        assignBtn.setTranslateY(236);
        assignBtn.setTranslateX(195);

        fileDir = new Label("Select a PDF File");
         fileDir.setTranslateY(105);
         fileDir.setTranslateX(132);
   
        
        pane2.getChildren().addAll(fileDir);
        pane2.getChildren().addAll(findBtn);
        pane2.getChildren().addAll(submitBtn);
        pane2.getChildren().addAll(assignBtn);
        pane2.getChildren().addAll(pickR);
        addChild(pane2);
    }

    private void saveFile(File source) throws IOException {
        File folder = new File("All Journals");
        folder.mkdirs();

        File dest = new File("All Journals\\NAME_"+source.getName());
        DataStore db = new DataStore();
        University u = db.load().university;
        u.journals.add(new Journal(source.getName()));
        db.serialize();


        InputStream is = null;
        OutputStream os = null;


        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    private File selectFile(Stage ps){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.pdf")
        );

        File f = fc.showOpenDialog(ps);
        if(f != null) {
            fileDir.setText(f.getName());

            return f;
        }else{
            System.out.println("file not selected");
            return null;
        }
    }

    public Pane getPane(){
        return pane;
    }


}
