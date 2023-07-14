/*
 * This file is part of the PredJack application.
 * Copyright (c) 2023, Team 9Git.
 *
 * PredJack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PredJack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PredJack.  If not, see <http://www.gnu.org/licenses/>.
 */
package azure.scenehandler;

/**
 * The ControlledScreen interface defines a method that allows the injection of the parent screen controller.
 *
 * <p>Implementing this interface allows a screen controller to access the parent screen controller, which
 * can be used to switch between screens in the application.</p>
 *
 * <p>For example, a screen controller can use the setScreen method of the parent screen controller to switch
 * to another screen in the application:</p>
 *
 * <pre>{@code
 * public class MyController implements ControlledScreen {
 *     ScreensController myController;
 *
 *     @Override
 *     public void setScreenParent(ScreensController screenParent) {
 *         myController = screenParent;
 *     }
 *
 *     @FXML
 *     private void switchToScreen2(ActionEvent event) {
 *         myController.setScreen(ScreensController.screen2ID);
 *     }
 * }
 * }</pre>
 *
 * <p>The ScreensController class is an example of a parent screen controller that can be used with this
 * interface.</p>
 *
 * @author Andy Bui
 * @see ScreensController
 */
public interface ControlledScreen {

    /**
     * Sets the parent screen controller for this screen controller.
     *
     * @param screenPage the parent screen controller
     */
    public void setScreenParent(ScreensController screenPage);

    public void setValueChangeDetector(ValueChangeDetector valueChangeDetector);
}
