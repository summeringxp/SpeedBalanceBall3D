package edu.xiapei.GameMap;


public class SlopEast extends MapElements {

	public SlopEast(int h) {
		super(h);
		this.setDrawer(new SlopDrawer(hight,2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		return x/2.0f+(1.0f+hight)/2.0f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{-0.0016f,0.0f,-0.0033f};
	}

}
