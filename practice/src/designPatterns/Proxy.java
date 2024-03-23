package designPatterns;

/*
The Proxy Pattern provides a surrogate or placeholder
for another object to control access to it.

Let's consider a scenario where we have an
image viewer application that loads and displays images.
 We'll implement a proxy to control access to the image
 object and simulate lazy loading behavior.
 */

// Subject: Image
interface Image {
    void display();
}
// Real Subject: RealImage
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
// Proxy: ProxyImage
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class Proxy {

    public static void main(String[] args) {

        // We demonstrate how to use the proxy pattern by
        // creating proxy images and displaying them.
        // The proxy ensures that the real image is loaded from
        // disk only when necessary, which can be useful
        // for performance optimization, especially when dealing
        // with large or numerous images.

        // Create proxy images
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // Image 1 will be loaded
        image1.display();

        // Image 2 will be loaded
        image2.display();

        // Image 1 will not be loaded again (loaded already)
        image1.display();
    }

}
