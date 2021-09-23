package ie.gmit.dip;

//	   A convolution kernel is a 2D array of numbers that can be used to apply
//	   a filtering effect to an image.

public enum Kernel {

	IDENTITY("Identity", new double[][]{
			{0, 0, 0},
			{0, 1, 0},
			{0, 0, 0}
	}),

	EDGE_DETECTION_1("Edge Detection 1", new double[][]{
			{-1, -1, -1},
			{-1, 8, -1},
			{-1, -1, -1}
	}),

	EDGE_DETECTION_2("Edge Detection 2", new double[][]{
			{1, 0, -1},
			{0, 0, 0},
			{-1, 0, 1}
	}),

	LAPLACIAN("Laplacian", new double[][]{
			{0, -1, 0},
			{-1, 4, -1},
			{0, -1, 0}
	}),

	SHARPEN("Sharpen", new double[][]{
			{0, -1, 0},
			{-1, 5, -1},
			{0, -1, 0}
	}),

	VERTICAL_LINES("Vertical Lines", new double[][]{
			{-1, 2, -1},
			{-1, 2, -1},
			{-1, 2, -1}
	}),

	HORIZONTAL_LINES("Horizontal Lines", new double[][]{
			{-1, -1, -1},
			{2, 2, 2},
			{-1, -1, -1}
	}),

	DIAGONAL_45_LINES("Diagonal 45 Lines", new double[][]{
			{-1, -1, 2},
			{-1, 2, -1},
			{2, -1, -1}
	}),

	BOX_BLUR("Box Blur", new double[][]{
			{0.111, 0.111, 0.111},
			{0.111, 0.111, 0.111},
			{0.111, 0.111, 0.111}
	}),

	SOBEL_HORIZONTAL("Sobel Horizontal", new double[][]{
			{-1, -2, -1},
			{0, 0, 0},
			{1, 2, 1}
	}),

	SOBEL_VERTICAL("Sobel Vertical", new double[][]{
			{-1, 0, 1},
			{-2, 0, 2},
			{-1, 0, 1}
	}),

	EMBOSS("Emboss", new double[][]{
			{-2,-1,0},
			{-1,1,1},
			{0,1,2}
	});

	public String kernelName;
	public double[][] kernelArray;

	Kernel(String kernelName, double[][] kernelArray) {
		this.kernelName = kernelName;
		this.kernelArray = kernelArray;
	}
}