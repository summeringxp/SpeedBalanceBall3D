package edu.xiapei.GameMap;


public class Accwest extends MapElements {

	public Accwest(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,5));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{-0.01f,0.0f,0.0f};
	}

}
