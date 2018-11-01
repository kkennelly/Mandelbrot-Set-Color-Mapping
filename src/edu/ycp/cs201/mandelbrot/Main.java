package edu.ycp.cs201.mandelbrot;

public class Main {
	public static void main(String[] args) {
		Launcher.runGUI(new ColorChooserFactory() {
			@Override
			public ColorChooser createColorChooser(int[][] iterCounts) {
				return new ColorMappingColorChooser(iterCounts);
			}
		});
	}
}
