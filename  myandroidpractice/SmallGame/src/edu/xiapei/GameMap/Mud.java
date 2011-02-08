package edu.xiapei.GameMap;


public class Mud extends MapElements {

	public Mud(int h) {
		super(h);
		this.setDrawer(new CubeDrawer(hight,7));
		// TODO Auto-generated constructor stub
	}


	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.0f,0.0051f};
	}
	public float getResistant(){
		return 0.10f;
	}

}
