package cs2410.assn4.controller;

import cs2410.assn4.model.Model;
import javafx.scene.image.Image;

/**
 * Button functionality for the GUI
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Controller {
    /**
     * Location of the image to display if model is empty
     */
    private static String NOT_FOUND_IMAGE = "https://pbs.twimg.com/profile_images/600060188872155136/st4Sp6Aw.jpg";

    /**
     * Image object for notFound image
     */
    private Image notFound;

    /**
     * Model containing data
     */
    private Model model;

    /**
     * Init Controller object
     */
    public Controller() {
        model = new Model();
        notFound = new Image(NOT_FOUND_IMAGE);
    }

    /**
     * Get next image
     * @return Next image
     */
    public Image nextImage() {
        String url = model.nextImage();
        return getImage(url);
    }

    /**
     * Get Previous Image
     * @return Previous Image
     */
    public Image prevImage() {
        String url = model.prevImage();
        return getImage(url);
    }

    /**
     * Get Current Image
     * @return current image
     */
    public Image currentImage() {
        String url = model.currentImage();
        return getImage(url);
    }

    /**
     * Add an image to the list
     * @param url URL of image to add
     * @param caption the caption of the image
     * @return The image
     */
    public Image addImage(String url, String caption) {
        model.addImage(url, caption);
        return getImage(url);
    }

    /**
     * Delete current image from list
     * @return Next image
     */
    public Image delImage() {
        model.delImage();
        return getImage(model.currentImage());
    }

    /**
     * Quit program, save file
     */
    public void quit() {
        model.quit();
    }

    public String getCaption() {
        if (model.currentImage() == null) {
            return "";
        }
        return model.getCaption();
    }

    /**
     * Helper method to return notFound if there are no URLs in the model
     * @param url url to be used
     * @return the image
     */
    private Image getImage(String url) {
        if (url == null) {
            return notFound;
        }

        return new Image(url);
    }
}
