package edu.xiapei.GameMap;


public class Glass extends MapElements {

	public Glass(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		
		return (1.0f+hight)/2.0f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.0f,0.0f};
	}
	public float getResistant(){
		return 0.00f;
	}

}
