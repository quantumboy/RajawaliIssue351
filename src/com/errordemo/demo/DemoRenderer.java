package com.errordemo.demo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import java.io.InputStream;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.content.Context;
import android.content.res.Resources;

import com.errordemo.demo.R;

import rajawali.BaseObject3D;
import rajawali.lights.DirectionalLight;

import rajawali.parser.ObjParser;
import rajawali.renderer.RajawaliRenderer;

public class DemoRenderer extends RajawaliRenderer {
	
	public static final int XAXIS = 0;
	public static final int YAXIS = 1;
	public static final int ZAXIS = 2;
	
	private CameraControl camControl;
	
    private DirectionalLight mLight;

	long currentTime;
	long tempTime;
	long elapsedTime;

    float CAMERA_Z = 200.0f;
	float CAMERA_Y = CAMERA_Z / 2.0F;

	float LIGHT_Z = 100.0f;
	float LIGHT_Y = 500.0f;
	float LIGHT_X = 100.0f;

	float ZNEAR = 36.0f;
	float ZFAR = 3200.0f;

	Resources resource;
	InputStream iFile;

	WindowManager wManager;
	Context context;
	Display display;
	DisplayMetrics metrics = null;

	public DemoRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}

	protected void initScene() {
		mLight = new DirectionalLight(0.1f, -0.2f, 1.0f); // set the direction
		mLight.setPower(3.0f);
		
		float cameraZ = CAMERA_Z;
		float cameraY = cameraZ / 2.0f;

		mCamera.setZ(cameraZ);
		mCamera.setY(cameraY);
		mCamera.setFarPlane(ZFAR);

		mCamera.setUpAxis(0, 1, 0);
		mCamera.setLookAt(0, 0, 0);
		
		camControl = new CameraControl(mCamera);

		currentTime = System.currentTimeMillis();
		
		// add front (z-axis) cube
		ObjParser objParser = new ObjParser(mContext.getResources(), getTextureManager(), R.raw.cube32_green_1_obj);
		objParser.parse();
		BaseObject3D mChildObject = objParser.getParsedObject();
		mChildObject.addLight(mLight);
		mChildObject.setPosition(-16, 0, 16);
		addChild(mChildObject);
		
		// add back (z-axis) cube
		objParser = new ObjParser(mContext.getResources(), getTextureManager(), R.raw.cube32_blue_1_obj);			
		objParser.parse();
		mChildObject = objParser.getParsedObject();
		mChildObject.addLight(mLight);
		mChildObject.setPosition(-16, 0, -16);
		addChild(mChildObject);			
	}
	
	public void moveCameraPlus(int axis)
	{
		switch (axis) {
		case XAXIS:
			camControl.startMoveX(CameraControl.PLUS);
			break;
		case YAXIS:
			camControl.startMoveY(CameraControl.PLUS);
			break;
		case ZAXIS:
			camControl.startMoveZ(CameraControl.PLUS);
			break;
		}
	}

	public void moveCameraMinus(int axis)
	{
		switch (axis) {
		case XAXIS:
			camControl.startMoveX(CameraControl.MINUS);
			break;
		case YAXIS:
			camControl.startMoveY(CameraControl.MINUS);
			break;
		case ZAXIS:
			camControl.startMoveZ(CameraControl.MINUS);
			break;
		}
	}

	public void stopCamera(int axis)
	{
		switch (axis) {
		case XAXIS:
			camControl.startMoveX(CameraControl.STOP);
			break;
		case YAXIS:
			camControl.startMoveY(CameraControl.STOP);
			break;
		case ZAXIS:
			camControl.startMoveZ(CameraControl.STOP);
			break;
		}
	}
	
	public float getCameraX()
	{
		return mCamera.getX();
	}

	public float getCameraY()
	{
		return mCamera.getY();
	}

	public float getCameraZ()
	{
		return mCamera.getZ();
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
		
		// time tracking
		tempTime = System.currentTimeMillis();
		elapsedTime = tempTime - currentTime;
		currentTime = tempTime;
		
		// move camera with time
		camControl.update(elapsedTime);
	}

}
