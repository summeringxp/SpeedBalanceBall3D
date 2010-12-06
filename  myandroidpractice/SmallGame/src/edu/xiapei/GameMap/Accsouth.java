package edu.xiapei.GameMap;


public class Accsouth extends MapElements {

	public Accsouth(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,4));
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
		return new float[]{0.0f,-0.01f,0.0f};
	}

}
