package ie.gmit.dip;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// The Filter class is used to store functionality related to applying filters to images,
// as well as changing the currently active filter.

public class Filter {

    // Static variables to keep track of the active filter
    private static String activeFilterName = Kernel.EMBOSS.kernelName;
    private static double[][] activeFilter = Kernel.EMBOSS.kernelArray;

    // Getter and setter for activeFilterName
    public static String getActiveFilterName() {
        return activeFilterName;
    }

    public static void setActiveFilterName(String filterName) {
        activeFilterName = filterName;
    }

    // Getter and setter for activeFilter
    public static double[][] getActiveFilter() {
        return activeFilter;
    }

    public static void setActiveFilter(double[][] filter) {
        activeFilter = filter;
    }


    // Apply a filter to the image with the passed in file name
    public static void applyFilter(String imageName) throws IOException {

        //Read in an image and convert to a BufferedImage
        BufferedImage image;
        try {
            // Read the image file in the png folder matching the input name
            image = ImageIO.read(new File( "./png/" + imageName + ".png"));

        } catch (IOException e) {
            throw new IOException("No image file with that name found. Error: " + e);
        }

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        // pixels array to store the int pixel value for every pixel
        int[][] pixels = new int[imageWidth][imageHeight];

        for (int x = 0; x < imageWidth; x++) { //Loop over the 2D image pixel-by-pixel
            for (int y = 0; y < imageHeight; y++) {
                // Get the integer pixel value at an (x, y) coordinate and assign it
                // to the corresponding index in pixels array
                pixels[x][y] = image.getRGB(x, y);

                // apply the currently selected filter to the current pixel being looped over
                filterPixel(pixels[x][y],x, y, image);
            }
        }

        //Write out the file in PNG format
        ImageIO.write(image, "png", new File(imageName + " " + activeFilterName + ".png"));
    }

    // Apply a filter to the passed in pixel
    public static void filterPixel(int pixel, int x, int y, BufferedImage image){

        // Initialize variables used to hold the new colour value derived from the convolution
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;

        int element = pixel;

        // Loop through each element in the 2d array kernel matrix
        for (int row = 0; row < activeFilter.length; row++) {
            for (int col = 0; col < activeFilter[row].length; col++) {
                try {
                    //This will cause an exception if we overrun the edges of the image
                    // Return the color value of the pixel corresponding to the current matrix element
                    element = image.getRGB(x + col, y + row);

                    // Get the RGB colour channels out of the 32-bit pixel int for the current matrix element
                    // Each colour channel/component consists of 8 bits/1 byte (the alpha component also makes up 8 bits),
                    // with all 4 making up a 32bit int. Each channel has a range of 0-255
                    int elementRed = (element >> 16) & 0xff;
                    int elementGreen = (element >> 8) & 0xff;
                    int elementBlue = element & 0xff;

                    // For each colour, multiply the current kernel element by that colors byte value,
                    // and add it to the storage variable for that colour
                    sumRed += (activeFilter[row][col] * elementRed);
                    sumGreen += (activeFilter[row][col] * elementGreen);
                    sumBlue += (activeFilter[row][col] * elementBlue);

                } catch (Exception e) {
                    // Ignore any exception and keep going, as this implementation
                    // doesn't attempt to apply a filter to the edges of the image
                    continue;
                }
            }
        }

        int red, green, blue;
        // The new colour value is truncated to 0 if below 0, and to 255 if above 255
        red = Math.min(Math.max((sumRed),0),255);
        green = Math.min(Math.max((sumGreen),0),255);
        blue = Math.min(Math.max((sumBlue),0),255);

        // The filter is applied to the current pixel by using the new colour values
        image.setRGB(x, y, new Color(red,green,blue).getRGB());
    }

    // Change the active filter to the one matching the passed in filter name
    public static void changeFilter(String inputFilterName) {

        // Initialize variable to store the matching filter, setting it to null by default
        Kernel matchingFilter = null;

        // Loop over each value in the Kernel enum, and find the filter matching the passed in filter name
        for (Kernel kernel : Kernel.values()) {
            if (kernel.kernelName.equals(inputFilterName)) {
                matchingFilter = kernel;
            }
        }

        // If a matching filter was found, set it to be the active filter
        if (matchingFilter != null) {
            activeFilterName = matchingFilter.kernelName;
            activeFilter = matchingFilter.kernelArray;
            System.out.println("You have selected: " + activeFilterName + " as the active filter.");
        } else { // If an invalid filter name was passed in, return to the menu without changing the filter
            System.out.println("There is no filter matching that name.");
            return;
        }
    }
}
