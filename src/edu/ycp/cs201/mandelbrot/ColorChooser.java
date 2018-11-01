package edu.ycp.cs201.mandelbrot;

import java.awt.Color;

// Interface for "color chooser" objects which compute a color
// based on an iteration count
public interface ColorChooser {
	public Color getColor(int iterCount);
}
