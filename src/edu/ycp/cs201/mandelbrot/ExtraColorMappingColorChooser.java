package edu.ycp.cs201.mandelbrot;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Distribute colors based on an even distribution of iteration counts
// Colors are created using a trigonometric process for all 3 colors
//    blue from cos(0) * 255 to cos(PI/2) * 255
//    red from sin(PI/2) * 255 to sin(0) * 255
//    green from sin(0) * 255 to sin(PI) * 255
public class ExtraColorMappingColorChooser implements ColorChooser {

	// declare map references
	private TreeMap<Integer, Integer> iterCountMap;
	private HashMap<Integer, Integer> iterSpectrumMap;
	private HashMap<Integer, Color>   iterColorMap;
	
	// the max spectrum location - should always be Mandelbrot.WIDTH * Mandelbrot.HEIGHT
	//    but it is calculated in createIterSpectrumMap()
	private int maxLocation;
	
	
	// CONSTRUCTOR: that creates the 3 maps, but doesn't populate them
	// the maps will need to be created separately
	// this allows for separate testing of the constituent map creation methods
	ExtraColorMappingColorChooser() {
		
		// create the Maps, but don't populate them
		iterCountMap    = new TreeMap<Integer, Integer>();
		iterSpectrumMap = new HashMap<Integer, Integer>();
		iterColorMap    = new HashMap<Integer, Color>();
	}	
	
	
	// CONSTRUCTOR: creates all 3 Maps and populates them
	ExtraColorMappingColorChooser(int[][] iterCounts) {
		
		// create the Maps
		iterCountMap    = new TreeMap<Integer, Integer>();
		iterSpectrumMap = new HashMap<Integer, Integer>();
		iterColorMap    = new HashMap<Integer, Color>();		
		
		// populate the Maps from iterCounts arrays
		createIterCountMap(iterCounts);
		createIterSpectrumMap();
		createIterColorMap();
	}

	
	// GET COLOR: returns the Color mapped to the iterCount in iterColorMap
	@Override
	public Color getColor(int iterCount) {
		
		// if invalid iterCount, return BLACK
		if (!iterColorMap.containsKey(iterCount)) {
			System.out.println("Invalid iterCount key: " + iterCount + ", set to BLACK");
			return Color.BLACK;
		}
		
		// otherwise return color from Color Map
		return iterColorMap.get(iterCount);
	}	

	
	// CREATE ITERCOUNT MAP: run through iterCounts array, and accumulate distribution of counts
	// for an 800 x 800 array, the # of points will be 640,000, but there can only be
	// maxCounts different iteration values
	public TreeMap<Integer, Integer> createIterCountMap(int[][] iterCounts) {
		throw new UnsupportedOperationException("TODO - implement");		
	}
	
	
	// CREATE ITER SPECTRUM MAP: run through iterCountMap, and determine the spectrum location (this
	// is not the actual color, but rather its relative location in the color spectrum), based on
	// an even distribution for each iteration count in the iterCountMap
	public HashMap<Integer, Integer> createIterSpectrumMap() {
		throw new UnsupportedOperationException("TODO - implement");		
	}
	
	
	// CREATE ITER COLOR MAP: run through iterSpectrumMap, and create a color mapping
	// using trig functions to create a smooth transition between RGB color bands
	public HashMap<Integer, Color> createIterColorMap() {
		throw new UnsupportedOperationException("TODO - implement");		
	}
}
