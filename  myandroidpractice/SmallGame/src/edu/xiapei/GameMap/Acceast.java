package edu.xiapei.GameMap;


public class Acceast extends MapElements {

	public Acceast(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,6));
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
		
		return new float[]{0.01f,0.0f,0.0f};
	}

}
