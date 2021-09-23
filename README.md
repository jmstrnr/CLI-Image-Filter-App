# CLI Image Filter App

This Java command line application is used to accept an image, apply a filter to that image, and then output the filtered image. It uses a convolution matrix to apply a filter to each pixel of a rasterized image, changing the value of each pixel based on the value of surrounding pixels and the matrix weights.

Features:
-Image filtering.
By entering 1 into the command line terminal when prompted, you are able to enter an image name. A copy of that image will be created and have the active filter applied to it, before being output.

-Changing the active filter.
By entering 2 into the command line terminal when prompted, you can change the currently active filter. There are 12 different filters to choose from.

-Filtering multiple images at once.  
By entering 3 into the command line terminal when prompted, you can input the number of images you wish to filter. You can then input the name of each individual image, and a copy with the filter applied will be output.
