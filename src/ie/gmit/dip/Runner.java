package ie.gmit.dip;

import java.util.Scanner;

public class Runner {

	// isOpen variable to keep track of the state of the menu and whether its open or closed
	private static boolean isOpen = true;

	// Getter for isOpen
	public static boolean getIsOpen() {
		return isOpen;
	}

	// Setter for isOpen
	public static void setIsOpen(boolean state) {
		isOpen = state;
	}


	public static void main(String[] args) throws Exception {

		do {  // Loop to keep menu running while isOpen is true
			System.out.println(ConsoleColour.BLUE_BRIGHT);
			System.out.println("***************************************************");
			System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
			System.out.println("*                                                 *");
			System.out.println("*           Image Filtering System V0.1           *");
			System.out.println("*     H.Dip in Science (Software Development)     *");
			System.out.println("*                                                 *");
			System.out.println("***************************************************");


			System.out.println("1) Enter Image Name"); //Ask user to specify the file to process
			System.out.println("2) Select a Filter"); //List the set of filters available in the class Kernel.java
			System.out.println("3) Filter Multiple Images"); // Filter multiple images at once
			System.out.println("4) Quit"); //Terminate
			System.out.println("\nSelect Option [1-4]>");

			// Used to read user input
			Scanner scanner = new Scanner(System.in);

			int userChoice;

			try {
				userChoice = scanner.nextInt(); // Read user input choice
				scanner.nextLine(); // Move to the next line
			} catch (Exception e) {
				throw new Exception("Invalid input. Please enter a number between 1 and 4. Error: " + e);
			}

			switch (userChoice) {

				case 1 -> {
					System.out.println("You chose 1");
					System.out.println("The current active filter is: " + Filter.getActiveFilterName());
					System.out.println("Please enter the filename of the image you wish to process.");

					String imageName = scanner.nextLine().trim();  // Read user input image name

					System.out.println("You chose: " + imageName);

					// Apply the filter to the specified image
					Filter.applyFilter(imageName);
				}

				case 2 -> {
					System.out.println("You chose 2");
					System.out.println("Please enter the name of the filter you wish to use.");
					System.out.println("Your options are:");

					// Loop over each value in the Kernel enum, and print out its name
					for (Kernel kernel : Kernel.values()) {
						System.out.println(kernel.kernelName);
					}

					// Read user input filter name
					String inputFilterName = scanner.nextLine().trim();

					System.out.println("You chose: " + inputFilterName);

					// Call the changeFilter method to change the active filter
					Filter.changeFilter(inputFilterName);
				}

				case 3 -> {
					System.out.println("You chose 3");
					System.out.println("The current active filter is: " + Filter.getActiveFilterName());
					System.out.println("Please enter how many images you would like filter.");

					int numImages;

					try {
						numImages = scanner.nextInt(); // Read user input choice
					} catch (Exception e) {
						throw new Exception("Invalid input. Please enter an integer for how many images you wish to filter. Error: " + e);
					}

					System.out.println("You chose to filter " + numImages + " images.");

					String currentImage;  // Temporary variable used to store the current image in the loop
					for (int i = 1; i <= numImages; i++) {  // Loop once for each image to be filtered
						System.out.println("Please enter the filename of image number " + i + ".");
						currentImage = scanner.nextLine().trim();  // Read user input image name
						System.out.println("You chose " + currentImage + " as image number " + i + ".");
						Filter.applyFilter(currentImage);  // Apply the filter to the current image
					}

					System.out.println("All images have been filtered.");
				}

				case 4 -> {
					System.out.println("You chose 4");
					System.out.println("Closing the menu...");
					scanner.close();  // Close to avoid memory leaks
					setIsOpen(false); // Set isOpen to false to close the menu
				}

				default -> System.out.println("Invalid selection, please enter a number between 1 and 4.");
			}

			System.out.println(ConsoleColour.RESET);
		} while (isOpen);  // Keep the menu running while isOpen is true
	}
}