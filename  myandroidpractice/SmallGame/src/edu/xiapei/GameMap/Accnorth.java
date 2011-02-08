package edu.xiapei.GameMap;


public class Accnorth extends MapElements {

	public Accnorth(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,3));
		// TODO Auto-generated constructor stub
	}


	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.01f,0.0f};
	}

}
