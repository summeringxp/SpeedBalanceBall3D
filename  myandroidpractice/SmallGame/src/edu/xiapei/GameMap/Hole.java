package edu.xiapei.GameMap;


public class Hole extends MapElements {

	public Hole(int hight) {
		super(hight);
		hight =-1;
		this.setDrawer(new HoleDrawer());
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		return -100.0f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.0f,0.0f};
	}

}
