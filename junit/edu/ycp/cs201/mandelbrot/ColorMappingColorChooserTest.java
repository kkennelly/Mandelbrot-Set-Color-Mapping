package edu.ycp.cs201.mandelbrot;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class ColorMappingColorChooserTest {
	
	// reference for Color chooser under test
	ColorMappingColorChooser chooser;
	
	// reference for test iterCounts array
	int[][] iterCounts;	
	
	// references for Maps with expected values to test against
	TreeMap<Integer, Integer> testIterCountMap;
	HashMap<Integer, Integer> testIterSpectrumMap;
	HashMap<Integer, Color>   testIterColorMap;
	
	// max location for SpectrumMap
	int maxLocation = 0;

	
	@Before
	public void setUp() throws Exception {
 
		System.out.println("");		
		System.out.println("*** Test Setup Step ***");
		
		// create Maps with expected values to test against
		testIterCountMap    = new TreeMap<Integer, Integer>();
		testIterSpectrumMap = new HashMap<Integer, Integer>();
		testIterColorMap    = new HashMap<Integer, Color>();

		// create iterCounts array for test cases
		createTestIterCountsArray();
		
		// create expected results for iterCountMap test
		createTestIterCountsMap();
		
		// create expected results for iterSpectrumMap test
		createTestIterSpectrumMap();
		
		// create expected results for iterColoorMap test
		createTestIterColorMap();
	}
	

	// test case for constructor that accepts iterCounts array
	// this constructor creates all of the Maps, based on iterCounts contents
	// for this to work, all of the Map creation methods must work first
	// there are also individual tests for the 3 Map creation methods
	@Test
	public void testColorMappingColorChooser() {
		
		System.out.println("*** Constructor Test ***");		
		
		// test constructor that creates everything - creates all 3 Maps
		chooser = new ColorMappingColorChooser(iterCounts);
		
		// get key set from test Color Map
		Set<Integer> testKeySet = testIterColorMap.keySet();
		
		// get Set contents as array and sort it
		// sorting the keys so that we get the Colors in a predictable order
		Integer[] testKeySetArray = new Integer[testKeySet.size()];
		testKeySet.toArray(testKeySetArray);
		Arrays.sort(testKeySetArray);
		
		// using getColor() to test results, since everything else has to work for getColor()
		//   to return the expected Colors		
		// test for invalid keys (should return BLACK)
		assertTrue(chooser.getColor(-1).equals(Color.BLACK));
		assertTrue(chooser.getColor(0).equals(Color.BLACK));		
		
		// test getColor() for all valid keys 
		for (Integer key : testKeySetArray) {
			System.out.println("key: " + key + ", expected Color: " + testIterColorMap.get(key) + ", actual Color: " + chooser.getColor(key));
			assertTrue(chooser.getColor(key).equals(testIterColorMap.get(key)));
		}
	}

	
	// test case for method that creates iterCountMap
	// it calls the default chooser constructor, which does not populate the Maps
	@Test
	public void testCreateIterCountMap() {
		
		System.out.println("*** createIterCountMap Test ***");
		
		// create color chooser that we are testing
		chooser = new ColorMappingColorChooser();
		
		// create iterCountMap and retrieve it (without creating other Maps)
		TreeMap<Integer, Integer> iterCountMap = chooser.createIterCountMap(iterCounts);
		
		// # of entries in each Map must be the same
		assertTrue(testIterCountMap.size() == iterCountMap.size());
		
		// get key sets and place in arrays
		Set<Integer> testKeySet = testIterCountMap.keySet();
		Set<Integer> keySet     = iterCountMap.keySet();
		
		// convert to arrays so that we can test them, in order
		Integer[] keySetArray = new Integer[keySet.size()];
		keySet.toArray(keySetArray);
		
		Integer[] testKeySetArray = new Integer[testKeySet.size()];
		testKeySet.toArray(testKeySetArray);
		
		// test for equality of all keys, in order
		// they should be coming in from Tree sets
		// so no sorting necessary - this verifies that a tree set was used
		for (int i = 0; i < testKeySetArray.length; i++) {
			System.out.println("key: " + testKeySetArray[i] + ", " + keySetArray[i]);
			assertTrue((int)testKeySetArray[i] == (int)keySetArray[i]);
		}
		
		// test for equality of all occurrences for those keys
		for (Integer key : testKeySet) {
			System.out.println("value: " + testIterCountMap.get(key) + "  " + iterCountMap.get(key));
			assertTrue((int)testIterCountMap.get(key) == (int)iterCountMap.get(key));
		}		
	}
	


	// TODO: Test that the createIterSpectrumMap() method creates all of the expected keys and values
	// TODO: You should test against the contents of testIterSpectrumMap, which has already been populated
	// TODO: You must check that iterCountMap contains the exact same keys as testIterSpectrumMap 
	// TODO:    and that those keys return the exact same values as testIterSpectrumMap

	// test case for method that creates iterSpectrumtMap
	// it calls the default chooser constructor that does not populate the Maps
	// and then calls the createIterCountMap() method, which is required for this test
	@Test
	public void testCreateIterSpectrumMap() {

		System.out.println("*** createIterSpectrumMap Test ***");
		
		throw new UnsupportedOperationException("TODO - implement");				
	}


	// TODO: Test that the createIterColorMap() method creates all of the expected keys and Colors
	// TODO: You should test against the contents of testIterColorMap, which has already been populated
	// TODO: You must check that iterColorMap contains the exact same keys as testIterColorMap
	// TODO:    and that those keys return the exact same Colors as testIterColorMap	

	// test case for method that creates iterColortMap
	// it calls the default chooser constructor that does not populate the Maps
	// and then calls the createIterCountMap() and createIterSpectrumMap() methods,
	// which are both required for this test
	@Test
	public void testCreateIterColorMap() {

		System.out.println("*** createIterColorMap Test ***");

		throw new UnsupportedOperationException("TODO - implement");				
	}

// *******************************************************************************************
// ***** METHODS FOR SETTING UP ITERCOUNTS ARRAY AND ALL TEST MAPS WITH EXPECTED VALUES  *****
// *****                   DO NOT ALTER ANYTHING BEYOND THIS POINT                       *****
// *******************************************************************************************	
	
	// create a test iterCounts array
	private void createTestIterCountsArray() {
		// only 10 x 10 for this test
		// this means that the class and methods under test can't use Mandelbrot.WIDTH and Mandelbrot.HEIGHT
		//
		// filling upper-triangle with powers of 2, from 2 to 512, increasing for each diagonal
		// filling bottom triangle with powers of 2 - 1, from 511 to 1, decreasing for each diagonal
		// filling major diagonal with THRESHOLD
		// this should result in a diagonally striped 10 x 10 pixel output
		
// **** POPULATING iterCounts ARRAY WITH TEST VALUES ****
// **** UNCOMMENT PRINT STATEMENTS TO PRINT ARRAY CONTENTS TO CONSOLE ****		
		
//		System.out.println("test iterCounts array");		
//		System.out.println("    -0-   -1-   -2-   -3-   -4-   -5-   -6-   -7-   -8-   -9-");
		iterCounts = new int[10][10];
		
		for (int i = 0; i < 10; i++) {
			
//			System.out.printf("%1d  ", i);
			
			for (int j = 0; j < 10; j++) {
				// upper triangle - powers of 2 from 2 to 512
				if ((i + j) < 9) {
					iterCounts[i][j] = (int)Math.pow(2, (i + j + 1));
				}
				// lower triangle - powers of 2 - 1
				else if ((i + j )> 9) {
					iterCounts[i][j] = (int)Math.pow(2,  ((10 - i) + (10 - j) - 1)) - 1;
				}
				// main diagonal - THRESHOLD
				else {
					iterCounts[i][j] = Mandelbrot.THRESHOLD;
				}
				
//				System.out.printf("%4d  ", iterCounts[i][j]);
			}
//			System.out.println("");
		}
	}
	
	private void createTestIterCountsMap() {
		// **** POPULATING testIterCountMap WITH EXPECTED VALUES ****
		// **** UNCOMMENT PRINT STATEMENTS TO PRINT EXPECTED VALUES TO THE CONSOLE ****
				
		// now populate testIterCountMap with expected values
		for (int i = 1; i < 10; i++) {
			// put upper triangle occurrences into map
			testIterCountMap.put((int)Math.pow(2, i), i);
			
			// put lower triangle occurrences into map
			testIterCountMap.put((int)Math.pow(2, 10-i) - 1, 10-i);
		}
		
		// put occurrences for THRESHOLD into map
		testIterCountMap.put(Mandelbrot.THRESHOLD, 10);
/*
		// get key set for printing
		Set<Integer> occurrenceSet = testIterCountMap.keySet();
		
		// print contents of testIterCountMap - in sorted order
		System.out.println("");
		System.out.println("testIterCountMap");
		for (Integer occurrence : occurrenceSet) {
			System.out.printf("(%4d, %2d)\n", occurrence, testIterCountMap.get(occurrence));
		}
*/		
	}
	
	private void createTestIterSpectrumMap() {
		// **** POPULATING testIterSpectrumMap WITH EXPECTED VALUES ****
		// **** UNCOMMENT PRINT STATEMENTS TO PRINT EXPECTED VALUES TO THE CONSOLE ****		
	
		// populate testIterSpectrumMap with expected values
		testIterSpectrumMap.put(1, 1);
		testIterSpectrumMap.put(2, 2);
		testIterSpectrumMap.put(3, 4);
		testIterSpectrumMap.put(4, 6);
		testIterSpectrumMap.put(7, 8);
		testIterSpectrumMap.put(8, 11);
		testIterSpectrumMap.put(15, 15);
		testIterSpectrumMap.put(16, 19);
		testIterSpectrumMap.put(31, 23);
		testIterSpectrumMap.put(32, 28);
		testIterSpectrumMap.put(63, 34);
		testIterSpectrumMap.put(64, 40);
		testIterSpectrumMap.put(127, 46);
		testIterSpectrumMap.put(128, 53);
		testIterSpectrumMap.put(255, 61);
		testIterSpectrumMap.put(256, 69);
		testIterSpectrumMap.put(511, 77);
		testIterSpectrumMap.put(512, 86);
		testIterSpectrumMap.put(Mandelbrot.THRESHOLD, 96);
		
		// set maxLocation
		maxLocation = 100;
		
		// get keys from SpectrumMap and print Map contents in sorted order
		Set<Integer> spectrumSet = testIterSpectrumMap.keySet();
		Object[] sortedSpectrumSet = spectrumSet.toArray();
		Arrays.sort(sortedSpectrumSet);
		
/*
		// print contents of testIterSpectrumMap
		System.out.println("");
		System.out.println("testIterSpectrumMap");
		for (Object spectrum : sortedSpectrumSet) {
			System.out.printf("(%4d, %2d)\n", spectrum, testIterSpectrumMap.get(spectrum));
		}
		
		System.out.println("maxLocation: " + maxLocation);
*/				
	}
	
	private void createTestIterColorMap() {
	
	// **** POPULATING testIterColorMap WITH EXPECTED VALUES ****
	// **** UNCOMMENT PRINT STATEMENTS TO PRINT EXPECTED VALUES TO THE CONSOLE ****

		// populate iterColorMap
		testIterColorMap.put(1, new Color(4, 8, 254));
		testIterColorMap.put(2, new Color(8, 16, 254));
		testIterColorMap.put(3, new Color(16, 31, 254));
		testIterColorMap.put(4, new Color(23, 47, 253));
		testIterColorMap.put(7, new Color(31, 63, 252));
		testIterColorMap.put(8, new Color(43, 86, 251));
		testIterColorMap.put(15, new Color(59, 115, 247));
		testIterColorMap.put(16, new Color(74, 143, 243));
		testIterColorMap.put(31, new Color(90, 168, 238));
		testIterColorMap.put(32, new Color(108, 196, 230));
		testIterColorMap.put(63, new Color(129, 223, 219));
		testIterColorMap.put(64, new Color(149, 242, 206));
		testIterColorMap.put(127, new Color(168, 252, 191));
		testIterColorMap.put(128, new Color(188, 253, 171));
		testIterColorMap.put(255, new Color(208, 239, 146));
		testIterColorMap.put(256, new Color(225, 210, 119));
		testIterColorMap.put(511, new Color(238, 168, 90));
		testIterColorMap.put(512, new Color(248, 108, 55));
		testIterColorMap.put(2000, new Color(0, 0, 0));
		
		// get keys from colorMap and print Map contents in sorted order
		Set<Integer> colorSet = testIterSpectrumMap.keySet();
		Object[] sortedColorSet = colorSet.toArray();
		Arrays.sort(sortedColorSet);
/*		
		// print contents of testIterColorMap
		System.out.println(""); 
		System.out.println("testIterSpectrumMap");
		for (Object colorKey : sortedColorSet) {
			int red   = testIterColorMap.get(colorKey).getRed();
			int green = testIterColorMap.get(colorKey).getGreen();
			int blue  = testIterColorMap.get(colorKey).getBlue();			
			System.out.printf("(%4d, %3d, %3d, %3d)\n", colorKey, red, green, blue);
		}
*/
	}
}
