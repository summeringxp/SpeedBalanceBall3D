package edu.xiapei;

public class Statics {
	

	//Map Elements Type
	public static int EMPTY = 0;
	public static int GROUND = 1;
	public static int SLOP_NORTH = 2;
	public static int SLOP_SOUTH = 3;
	public static int SLOP_WEST = 4;
	public static int SLOP1_EAST = 5;
	public static int ACCELERATOR_NORTH = 6;
	public static int ACCELERATOR_SOUTH = 7;
	public static int ACCELERATOR_WEST = 8;
	public static int ACCELERATOR_EAST = 9;
	public static int ACCELERATOR_UP = 10;
	public static int TARGET = 11;
	public static int START = 12;
	
	//MapSize
	public static int MAPHIGHT = 16;
	public static int MAPWIDTH = 16;
	
	//
	public static float TOUCH_SCALE_FACTOR = 0.01f;
	public static float TRACKBALL_SCALE_FACTOR = 0.1f;
	//
	public static int CELLSIZE = 0x10000;
	//color 
	public static float[] SKYCOLOR = {0.8f,1.0f,1.0f,1.0f};

	
	//some data for test
	public static int[][] testMap = {
		{1 ,1 ,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,12,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,1 ,3 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,11,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,1 ,1 ,1 ,1 ,4, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,10,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,1 ,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1 ,1 ,1 ,1 ,0 ,1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0 ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	public static int[][] testHightMap = {
		{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
		{0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	
	
}