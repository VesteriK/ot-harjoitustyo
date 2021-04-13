package CourseDiary.ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.layout.*;

public class ui extends Application{
    
    
    @Override
    public void start (Stage window) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        
        HBox studentName = new HBox();
        TextField name = new TextField();
        name.setPromptText("Enter your name");
        Button changeButton = new Button("Change");
        studentName.getChildren().add(name);
        studentName.getChildren().add(changeButton);
        
        changeButton.setOnAction((event) -> {
            changeName();
        });
        
        
        VBox courses = new VBox();
        courses.setFillWidth(true);
        
        Label courseName = new Label("Course name");
        Label courseCredits = new Label("Credits");
        Label courseCompleted = new Label("Completed?");
        courses.setMaxWidth(Double.MAX_VALUE);
        Button addCourse = new Button("Add");
        addCourse.setAlignment(Pos.TOP_RIGHT);
        
        addCourse.setOnAction((event) -> {
           addCourse(); 
        });
        
        GridPane courseLabels = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(10);
        courseLabels.getColumnConstraints().addAll(col1, col2, col3);
        courseLabels.add(courseName, 0, 0);
        courseLabels.add(courseCredits, 1, 0);
        courseLabels.add(courseCompleted, 1, 0);
        
        courses.getChildren().add(addCourse);
        courses.getChildren().add(courseLabels);
        
        HBox completed = new HBox();
        Label completedText = new Label("Completed: ");
        Label completedCourses = new Label("0/180");
        completed.getChildren().add(completedText);
        completed.getChildren().add(completedCourses);
        completed.setPadding(new Insets(5,10,5,10));
        completed.setSpacing(5);
        completed.setAlignment(Pos.BOTTOM_RIGHT);
        
        
        layout.setTop(studentName);
        layout.setLeft(courses);
        layout.setBottom(completed);
        
        Scene scene = new Scene(layout);
        
        window.setScene(scene);
        window.setHeight(500);
        window.setWidth(500);
        window.setTitle("Course Tracker");
        window.show();
    }
    
    public void changeName() {
        
    }
    
    public void addCourse() {
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
