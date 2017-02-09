package main;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private MediaPlayer mp;
    private Alert a;
    private final String map = "This is Thomas More's Utopia!";
    private final String farm = "Each city is surrounded by farmland, \nand every member of each city spends " +
            "\noccasional two-year stints in the country\n doing agricultural work. Cities do not attempt\n to expand their frontiers; " +
            "they think\n of the surrounding areas as land to be\n worked rather than as estates to be owned.";
    private final String church = "A number of religions exist in the Utopia. The\n different religions meet in the same \nchurches run by the same priests, " +
            "and services\n emphasize the similarities between the\n religions. " +
            "If some religion demands a rite or\n prayer that might be offensive to another,\n then that rite must be performed in a home\n in private, not in the church.";

    private final String school = "In Utopia, only a certain amount of successful\n people were allowed to give up manual\n labor and " +
            "pursue intellectual studies.\n These people spend most of their time\n studying in the school.";
    private final String gate = "The island has fifty-four cities, all\n with the same basic structure, architecture,\n language, customs, and laws. " +
            "All citizens \nare within once day's walk of their nearest\n neighbor. " +
            "The city of Amaurot is the political\n center of the island, simply because it is\n the city most accessible to all the other cities. ";
    private final String street = "Other than farm work, every person, woman\n and man, has a specific occupation. The most\n common trades are spinning and weaving,\n masonry, blacksmithing, and carpentry.";

    @FXML
    Circle capitalBorder, gateBorder, churchBorder, farmBorder, schoolBorder;

    @FXML
    ImageView view1, view2;

    @FXML
    Button back, about, help, credits;

    @FXML
    Label text, capitalLabel, farmLabel, churchLabel, schoolLabel;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        capitalBorder.setOnMouseEntered(event -> showBorder(capitalBorder));
        capitalBorder.setOnMouseExited(event -> destroyBorder(capitalBorder));
        capitalBorder.setOnMouseClicked(event -> capitalBorderOnClick());

        gateBorder.setOnMouseClicked(event -> gateBorderOnClick());
        gateBorder.setOnMouseExited(event -> destroyBorder(gateBorder));
        gateBorder.setOnMouseEntered(event -> showBorder(gateBorder));

        schoolBorder.setOnMouseExited(event -> destroyBorder(schoolBorder));
        schoolBorder.setOnMouseEntered(event -> showBorder(schoolBorder));
        schoolBorder.setOnMouseClicked(event -> schoolBorderOnClick());

        churchBorder.setOnMouseClicked(event -> churchBorderOnClick());
        churchBorder.setOnMouseEntered(event -> showBorder(churchBorder));
        churchBorder.setOnMouseExited(event -> destroyBorder(churchBorder));

        farmBorder.setOnMouseClicked(event -> farmBorderOnClick());
        farmBorder.setOnMouseExited(event -> destroyBorder(farmBorder));
        farmBorder.setOnMouseEntered(event -> showBorder(farmBorder));

        help.setOnAction(event -> helpOnClick());
        credits.setOnAction(event -> creditsOnClick());
        about.setOnAction(event -> aboutOnClick());

        String url = Paths.get("src/media/wave.mp3").toUri().toString();
        startOnRepeat(url);

        text.setText(map);

    }

    private void showBorder(Circle c){ c.setOpacity(1); }

    private void destroyBorder(Circle c){ c.setOpacity(0); }

    private void capitalBorderOnClick(){
        view2.setImage(new Image(Paths.get("src/graphics/gate.png").toUri().toString()));

        FadeTransition ft = new FadeTransition();
        ft.setNode(view1);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view2);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        capitalBorder.setDisable(true);
        schoolBorder.setDisable(true);
        farmBorder.setDisable(true);
        churchBorder.setDisable(true);
        gateBorder.setDisable(false);
        mp.stop();

        back.setDisable(false);
        back.setOpacity(0.67);
        back.setOnMouseClicked(event -> backToMap());

        startOnRepeat(Paths.get("src/media/birdchirp.wav").toUri().toString());

        text.setText(gate);
    }

    private void gateBorderOnClick(){
        view1.setImage(new Image(Paths.get("src/graphics/street1.png").toUri().toString()));

        FadeTransition ft = new FadeTransition();
        ft.setNode(view2);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view1);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();


        gateBorder.setDisable(true);

        back.setOnMouseClicked(event -> backToGate());
        mp.stop();
        startOnRepeat(Paths.get("src/media/market.mp3").toUri().toString());

        text.setText(street);
    }

    private void startOnRepeat(String url){
        mp = new MediaPlayer(new Media(url));
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        mp.play();
    }

    private void playOnce(String url){
        mp = new MediaPlayer(new Media(url));
        mp.play();
    }


    private void backToMap(){
        view1.setImage(new Image(Paths.get("src/graphics/v2.png").toUri().toString()));

        FadeTransition ft = new FadeTransition();
        ft.setNode(view2);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view1);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        capitalBorder.setDisable(false);
        schoolBorder.setDisable(false);
        churchBorder.setDisable(false);
        farmBorder.setDisable(false);
        gateBorder.setDisable(true);
        mp.stop();
        startOnRepeat(Paths.get("src/media/wave.mp3").toUri().toString());

        back.setDisable(true);
        back.setOpacity(0);

        text.setText(map);
    }

    private void backToGate(){
        view2.setImage(new Image(Paths.get("src/graphics/gate.png").toUri().toString()));

        FadeTransition ft = new FadeTransition();
        ft.setNode(view1);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view2);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        back.setOnMouseClicked(event -> backToMap());
        gateBorder.setDisable(false);
        gateBorder.setOpacity(1);gateBorder.setOpacity(0);
        mp.stop();
        startOnRepeat(Paths.get("src/media/birdchirp.wav").toUri().toString());

        text.setText(gate);
    }

    private void schoolBorderOnClick(){
        view2.setImage(new Image(Paths.get("src/graphics/school.png").toUri().toString()));

        mp.stop();
        startOnRepeat(Paths.get("src/media/pencil.mp3").toUri().toString());

        text.setText(school);

        FadeTransition ft = new FadeTransition();
        ft.setNode(view1);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view2);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        capitalBorder.setDisable(true);
        schoolBorder.setDisable(true);
        farmBorder.setDisable(true);
        churchBorder.setDisable(true);

        back.setDisable(false);
        back.setOpacity(0.67);
        back.setOnMouseClicked(event -> backToMap());
    }

    private void churchBorderOnClick(){
        view2.setImage(new Image(Paths.get("src/graphics/cruch.png").toUri().toString()));

        mp.stop();
        playOnce(Paths.get("src/media/churchbell.wav").toUri().toString());

        FadeTransition ft = new FadeTransition();
        ft.setNode(view1);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view2);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        capitalBorder.setDisable(true);
        schoolBorder.setDisable(true);
        churchBorder.setDisable(true);
        farmBorder.setDisable(true);


        back.setDisable(false);
        back.setOpacity(0.67);
        back.setOnMouseClicked(event -> backToMap());

        text.setText(church);
    }

    private void farmBorderOnClick(){
        view2.setImage(new Image(Paths.get("src/graphics/farm.png").toUri().toString()));

        mp.stop();
        startOnRepeat(Paths.get("src/media/farm.mp3").toUri().toString());

        FadeTransition ft = new FadeTransition();
        ft.setNode(view1);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();

        FadeTransition ft1 = new FadeTransition();
        ft1.setNode(view2);
        ft1.setDuration(new Duration(2000));
        ft1.setFromValue(0);
        ft1.setToValue(1.0);
        ft1.setCycleCount(1);
        ft1.setAutoReverse(true);
        ft1.play();

        capitalBorder.setDisable(true);
        schoolBorder.setDisable(true);
        churchBorder.setDisable(true);
        farmBorder.setDisable(true);

        back.setDisable(false);
        back.setOpacity(0.67);
        back.setOnMouseClicked(event -> backToMap());

        text.setText(farm);
    }

    private void aboutOnClick(){
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("About");
        a.setHeaderText("About the program");
        a.setContentText("This is an interactive map of Thomas More's Utopia!");

        a.showAndWait();
    }

    private void helpOnClick(){
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Help");
        a.setHeaderText("How to Use the Program");
        a.setContentText("In order to gain more information about Utopia click the circles.");

        a.showAndWait();
    }

    private void creditsOnClick(){
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Help");
        a.setHeaderText("How to Use the Program");
        a.setContentText("This program is made by:\n" +
                "Ege Berkay Gülcan\n" +
                "Irmak Türköz\n" +
                "Supervising Teacher: Sjoerd Levelt");

        a.showAndWait();
    }
}

