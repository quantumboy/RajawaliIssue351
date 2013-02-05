package com.errordemo.demo;

import com.errordemo.demo.R;
import rajawali.Camera;

public class CameraControl {
	private final int DEFAULT_SPEED = 5;
	private final int SLOW_SPEED = 1;

	public static final int STOP = 0;
	public static final int PLUS = 1;
	public static final int MINUS = 2;

	private Camera camera;

	private int movingX;
	private int movingY;
	private int movingZ;

	private int cameraSpeed = 0;

	public CameraControl(Camera cam) {
	//	cameraSpeed = DEFAULT_SPEED;
		cameraSpeed = SLOW_SPEED;
		camera = cam;

		movingX = STOP;
		movingY = STOP;
		movingZ = STOP;

	}
	
	public void startMoveX(int dir)
	{
		movingX = dir;
	}
	
	public void stopMoveX()
	{
		movingX = STOP;
	}

	
	public void startMoveY(int dir)
	{
		movingY = dir;
	}
	
	public void stopMoveY()
	{
		movingY = STOP;
	}

	
	public void startMoveZ(int dir)
	{
		movingZ = dir;
	}
	
	public void stopMoveZ()
	{
		movingZ = STOP;
	}

	public void update(long elapsedTime) {
		// handle x-axis movement
		if (movingX != STOP) {
			float x = camera.getX();

			if (movingX == PLUS) {
				// move in positive direction
				camera.setX(x - cameraSpeed);
			} else if (movingX == MINUS) {
				// move in negative direction
				camera.setX(x + cameraSpeed);
			}
		}

		// handle y-axis movement
		if (movingY != STOP) {
			float y = camera.getY();

			if (movingY == PLUS) {
				// move in positive direction
				camera.setY(y + cameraSpeed);
			} else if (movingY == MINUS) {
				// move in negative direction
				camera.setY(y - cameraSpeed);
			}
		}

		// handle z-axis movement
		if (movingZ != STOP) {
			float z = camera.getZ();

			if (movingZ == PLUS) {
				// move in positive direction
				camera.setZ(z + cameraSpeed);
			} else if (movingZ == MINUS) {
				// move in negative direction
				camera.setZ(z - cameraSpeed);
			}
		}

	}

}

