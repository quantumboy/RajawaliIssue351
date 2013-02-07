package com.errordemo.demo;

import rajawali.RajawaliFragmentActivity;
import rajawali.RajawaliActivity;
import rajawali.renderer.RajawaliRenderer;
import rajawali.math.Number3D;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.errordemo.demo.R;

public class DemoActivity extends RajawaliFragmentActivity {
//public class DemoActivity extends RajawaliActivity {
	private final int UP = 0;
	private final int DOWN = 1;
	private final int LEFT = 2;
	private final int RIGHT = 3;
	private final int IN = 4;
	private final int OUT = 5;
	
	private final int TEXT_SIZE = 20;
	
	private LinearLayout mLinearLayout;  // allow nesting of buttons and text layouts
	private LinearLayout mLinearLayoutButtons;
	private LinearLayout mLinearLayoutText;
	
	private Number3D mCameraOffset;
	
	private TextView label;
	
	private float camX = 0;
	private float camY = 0;
	private float camZ = 0;

    /** Called when the activity is first created. */
	private DemoRenderer mRenderer; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
		mCameraOffset = new Number3D();
		mCameraOffset.setAll(0, 0, 0);
        super.onCreate(savedInstanceState);
        
    	mRenderer = new DemoRenderer(this);
    	mRenderer.setSurfaceView(mSurfaceView);
    	super.setRenderer(mRenderer);
    	
        camX = mRenderer.getCameraX();
        camY = mRenderer.getCameraY();
        camZ = mRenderer.getCameraZ();

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        layout.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        layout.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(layout);   
 		
		mLinearLayout = new LinearLayout(this);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setGravity(Gravity.TOP);;
  
		mLinearLayoutButtons = new LinearLayout(this);
	    mLinearLayoutButtons.setOrientation(LinearLayout.HORIZONTAL);
	    mLinearLayoutButtons.setGravity(Gravity.TOP);
        
        Button button = new Button(this);
        button.setId(UP);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraPlus(DemoRenderer.YAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.YAXIS);
                    if (camX == 0.0) camX = mRenderer.getCameraX();
                    if (camZ <= 0.0) camZ = mRenderer.getCameraZ();
                    camY = mRenderer.getCameraY();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ);
                }
                return true;
            }
        });
        button.setText("Up");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);
        
        button = new Button(this);
        button.setId(DOWN);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraMinus(DemoRenderer.YAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.YAXIS); 
                    if (camX == 0.0) camX = mRenderer.getCameraX();
                    if (camZ <= 0.0) camZ = mRenderer.getCameraZ();
                    camY = mRenderer.getCameraY();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ);
                }
                return true;
            }
        });
        button.setText("Down");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);
        
        button = new Button(this);
        button.setId(LEFT);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraMinus(DemoRenderer.XAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.XAXIS);  
                    if (camY == 0.0) camY = mRenderer.getCameraY();
                    if (camZ <= 0.0) camZ = mRenderer.getCameraZ(); 
                    camX = mRenderer.getCameraX();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ);
                }
                return true;
            }
        });
        button.setText("Left");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);
        
        button = new Button(this);
        button.setId(RIGHT);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraPlus(DemoRenderer.XAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.XAXIS); 
                    if (camY == 0.0) camY = mRenderer.getCameraY();
                    if (camZ <= 0.0) camZ = mRenderer.getCameraZ();        
                    camX = mRenderer.getCameraX();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ);
                }
                return true;
            }
        });
        button.setText("Right");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);

        button = new Button(this);
        button.setId(IN);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraMinus(DemoRenderer.ZAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.ZAXIS);  
                    if (camX == 0.0) camX = mRenderer.getCameraX();
                    if (camY == 0.0) camY = mRenderer.getCameraY();     
                    camZ = mRenderer.getCameraZ();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ); 
                }
                return true;
            }
        });
        button.setText("In");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);
        
        button = new Button(this);
        button.setId(OUT);
        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRenderer.moveCameraPlus(DemoRenderer.ZAXIS);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mRenderer.stopCamera(DemoRenderer.ZAXIS);  
                    if (camX == 0.0) mRenderer.getCameraX();
                    if (camY == 0.0) mRenderer.getCameraY();     
                    camZ = mRenderer.getCameraZ();
                    label.setText("X: " + camX + " Y: " + camY + " Z: " + camZ);    
                }
                return true;
            }
        });
        button.setText("Out");
        button.setTextSize(TEXT_SIZE);
        mLinearLayoutButtons.addView(button);
        
      	mLinearLayoutText = new LinearLayout(this);
      	mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
      	mLinearLayoutText.setGravity(Gravity.BOTTOM);
 
        label = new TextView(this);
        label.setText("Halo Dunia!");
        label.setTextSize(20);
        label.setGravity(Gravity.LEFT);
        label.setHeight(100);
      	
        mLinearLayoutText.addView(label);
        
        mLayout.addView(mLinearLayoutButtons);
        mLayout.addView(mLinearLayoutText);
        
    }
}
