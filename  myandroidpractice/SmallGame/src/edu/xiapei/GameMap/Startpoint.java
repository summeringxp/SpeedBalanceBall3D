package edu.xiapei.GameMap;


public class Startpoint extends MapElements {

	public Startpoint(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		
		return 0.5f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.0f,0.0f};
	}

}
