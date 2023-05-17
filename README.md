# CLI Image Filter App

This application allows the user to select between 12 different filters to apply to an image. The user can input an image via the command line to apply the currently selected filter, and a filtered image will be generated and output.
It uses a convolution matrix to apply a filter to each pixel of a rasterized image, changing the value of each pixel based on the value of surrounding pixels and the matrix weights.

Upon starting the application, the user will be prompted to choose between 4 options:

1.  To filter an image: you enter the filename of the image you wish to process. A copy of that image will be created and have the active filter applied to it, before being output.
2.  To change the active filter: you enter the name of the filter you wish to use. There are 12 different filters to choose from.
3.  To filter multiple images at once: you input the number of images you wish to filter. You can then input the name of each individual image, and a copy of each image will be generated with the active filter applied.
4.  To close the terminal and prevent further user input.

For testing the application I used a variety of black and white images, contained in the "png" folder.    
A selection of output files displaying how an image looks with each filter applied to it is contained in the "filtered" folder.

Languages used: Java
