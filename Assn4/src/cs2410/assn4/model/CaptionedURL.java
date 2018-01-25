package cs2410.assn4.model;

/**
 * Holds URLS and Captions together
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class CaptionedURL {
    private String url;
    private String caption;

    public CaptionedURL(String url, String caption) {
        this.url = url;
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }

    public String toString() {
        return url + " " + caption;
    }
}
