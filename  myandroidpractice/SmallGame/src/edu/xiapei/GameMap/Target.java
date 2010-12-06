package edu.xiapei.GameMap;


public class Target extends MapElements {

	public Target(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,1));
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
