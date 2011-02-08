/*package gameTools;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import jp.pekatuu.android.util.BufferAllocator;
import jp.pekatuu.android.util.GLTextureFactory;
import jp.pekatuu.android.util.Time;
import android.util.Log;

public class Road {
	private float currentZ = 0f;

	public void draw(final GL10 gl) {
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, roadVB);
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 2, 4);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 6, 4);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, roadTB);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.ROAD1);
		gl.glTranslatef(0f, 0f, currentZ);
		gl.glColor4f(150f / 255f, 180f / 255f, 220f / 255f, 1f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glTranslatef(0f, 0f, -currentZ);
	}

	public void move() {
		currentZ -= GameData.player.vel * Time.interval;
		final float temp = currentZ + oneCycleLength;
		if (temp < 0f) {
			currentZ = temp;
		}
	}

	public void drawNmove(final GL10 gl) {
		currentZ -= GameData.player.vel * Time.interval;
		final float temp = currentZ + oneCycleLength;
		if (temp < 0f) {
			currentZ = temp;
		}
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, roadVB);
		gl.glColor4f(0f, 0f, 0f, 1f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 2, 4);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 6, 4);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, roadTB);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.ROAD1);
		gl.glTranslatef(0f, 0f, currentZ);
		gl.glColor4f(150f / 255f, 180f / 255f, 220f / 255f, 1f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glTranslatef(0f, 0f, -currentZ);
	}

	public static void allocateGLResource() {
		Log.d("road", "start allocation");
		final float floorX = BackGround.width + 3000f;
		final float[] vb = { -WIDTH, Y, -500f, -WIDTH, Y, LENGTH, WIDTH, Y,
				-500f, WIDTH, Y, LENGTH, floorX, Y, -500f, floorX, Y, LENGTH,
				-floorX, Y, -500f, -floorX, Y, LENGTH, -WIDTH, Y, -500f,
				-WIDTH, Y, LENGTH, };
		roadVB = BufferAllocator.allocateFloatBuffer(vb);

		float interval = LENGTH / oneCycleLength;
		final float[] tb = { 0f, 0f, 0f, interval, 2f, 0f, 2f, interval, };
		roadTB = BufferAllocator.allocateFloatBuffer(tb);
	}

	public static final float Y = -GameData.boundY - 50f;

	private static float oneCycleLength = 500f;

	private static FloatBuffer roadVB;
	private static FloatBuffer roadTB;

	public static final float WIDTH = GameData.boundX;
	public static final float LENGTH = 40000f;
}
*/