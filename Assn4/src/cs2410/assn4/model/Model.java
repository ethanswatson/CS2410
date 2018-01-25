package cs2410.assn4.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the images
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Model {
    /**
     * Location of the data file
     */
    private static final String FILE_PATH = "data/images.data";

    /**
     * ArrayList to hold all image urls;
     */
    private ArrayList<CaptionedURL> urls;

    /**
     * Holds index of current image
     */
    private int currentImageIndex;

    /**
     * Initializes Model object and reads data from file
     */
    public Model() {
        urls = new ArrayList<>();
        currentImageIndex = 0;
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(FILE_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + FILE_PATH + " not Found... Exiting program");
            System.exit(1);
        }

        while (fileInput.hasNextLine()) {
            String url = fileInput.next();
            // Get the entire caption and exclude the scpace at the start
            String caption = fileInput.nextLine().substring(1);
            urls.add(new CaptionedURL(url, caption));
        }

        fileInput.close();
    }

    /**
     * Adds an image to the list
     * @param url URL of the image to be added
     * @param caption The caption of the image
     */
    public void addImage(String url, String caption) {
        urls.add(currentImageIndex, new CaptionedURL(url, caption));
    }

    /**
     * Removes the current image from the list
     */
    public void delImage() {
        urls.remove(currentImageIndex);
    }

    /**
     * Retrieves the previous image from the list
     * @return URL of the previous image
     */
    public String prevImage() {
        // Update Index
        currentImageIndex--;

        if (currentImageIndex < 0) {
            currentImageIndex = urls.size() - 1;
        }

        return get(currentImageIndex);
    }

    /**
     * Retrieves the current image from the list
     * @return URL of the current image
     */
    public String currentImage() {
        return get(currentImageIndex);
    }

    /**
     * Gets next image in list
     * @return URL of next image
     */
    public String nextImage() {
        // Update Index
        currentImageIndex++;
        if (currentImageIndex >= urls.size()) {
            currentImageIndex = 0;
        }

        return get(currentImageIndex);
    }

    /**
     * Writes data to file
     */
    public void quit() {
        PrintWriter fileOutput = null;

        try {
            fileOutput = new PrintWriter(new FileOutputStream(FILE_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File " + FILE_PATH + " not Found... Exiting program");
            System.exit(1);
        }

        for (int i = 0; i < urls.size(); i++) {
            fileOutput.print(urls.get(i));

            // Add newline if not last element
            if (i != urls.size() - 1) {
                fileOutput.println();
            }
        }

        fileOutput.close();
    }

    /**
     * Gets the caption of the current image
     * @return the caption of the current image
     */
    public String getCaption() {
        return urls.get(currentImageIndex).getCaption();
    }

    /**
     * Helper method to facilitate returning null if the list is empty
     * @param index Index to get
     * @return Item stored at index
     */
    private String get(int index) {
        if (urls.size() == 0) {
            return null;
        } else {
            return urls.get(index).getUrl();
        }
    }
}
