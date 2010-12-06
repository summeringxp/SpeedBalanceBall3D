package edu.xiapei.GameMap;


public class SlopSouth extends MapElements {

	public SlopSouth(int h) {
		super(h);
		this.setDrawer(new SlopDrawer(hight,3));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		return (y-1.0f)/2.0f+(2.0f+hight)/2.0f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,-0.0016f,-0.0033f};
	}

}
