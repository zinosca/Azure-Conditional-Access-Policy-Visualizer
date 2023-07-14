package azure.scenehandler;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * @author Zino
 */
public class ScreensController extends StackPane {
    /** Holds the sceen to be displayed */

    /**
     * String is the ID and Node represent the root of the scenes
     */
    private HashMap<String, Node> levelscreens = new HashMap<>();

    private ValueChangeDetector valueChangeDetector = new ValueChangeDetector();

    public ScreensController() {
        super();
    }

    /**
     * Add the screen to the collection
     *
     * @param name   String
     * @param screen Node for fxml
     */
    public void addScreen(String name, Node screen) {
        levelscreens.put(name, screen);
    }

    //Returns the Node with the appropriate name
    public Node getScreen(String name) {
        return levelscreens.get(name);
    }

    /**
     * Loads the fxml file, add the screen to the screens collection
     * and finally injects teh screen to the controller
     *
     * @param name     String
     * @param resource String
     * @return true if it works
     */

    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = myLoader.load(); // we can load the Controller
            ControlledScreen myScreenController = myLoader.getController(); // We can choose the Scene
            myScreenController.setScreenParent(this);
            myScreenController.setValueChangeDetector(valueChangeDetector);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method tries to displayed the screen with a predefined name.
     * First it makes sure the screen has been already loaded. Then if there is more than
     * one screen the new screen is beeb added second, and then the current screen is removed.
     * If there isn't  any screen being displayed, the new screen is just added to the root.
     *
     * @param name (String)
     * @return true if screen has been loaded
     */
    public boolean setScreen(final String name) {
        if (levelscreens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) { //if there is more than one screen
                Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(300), (ActionEvent t) -> {
                    getChildren().remove(0);                        //remove the displayed screen
                    getChildren().add(0, levelscreens.get(name));   //add the screen
                    Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(150), new KeyValue(opacity, 1.0)));
                    fadeIn.play();
                }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(levelscreens.get(name));
                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(150), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            valueChangeDetector.informListener(name);
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }

    /**
     * This method will remove the screen with the given name from the collection of the screens
     *
     * @param name is a String
     * @return true or false
     */
    public boolean unloadScreen(String name) {
        if (levelscreens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
}

