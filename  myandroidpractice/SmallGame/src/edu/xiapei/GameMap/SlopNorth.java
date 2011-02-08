/**
 * 
 */
package edu.xiapei.GameMap;

/**
 * @author peixia
 *
 */
public class SlopNorth extends MapElements {

	/**
	 * 
	 */
	public SlopNorth(int hight) {
		super(hight);
		this.setDrawer(new SlopDrawer(hight,1));
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getHight(float x, float y) {
		// TODO Auto-generated method stub
		
		return (1.0f-y)/2.0f+(1.0f+hight)/2.0f;
	}

	@Override
	public float[] getForce() {
		// TODO Auto-generated method stub
		return new float[]{0.0f,0.0016f,-0.0033f};
	}

}
