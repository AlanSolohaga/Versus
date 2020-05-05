package com.project.versus.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.versus.R;

public class ImagenCanchaActivity extends Activity {

    private TextView text1;
    private TextView text2;
    private TextView text3;

    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_cancha);

        rootLayout = findViewById(R.id.view_root);
        text1 = rootLayout.findViewById(R.id.text1);

        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(400,100);
        //text1.setLayoutParams(layoutParams);
        text1.setOnTouchListener(new ChoiceTouchListener());


        text2 = rootLayout.findViewById(R.id.text2);
        text2.setOnTouchListener(new ChoiceTouchListener());

        text3 = rootLayout.findViewById(R.id.text3);
        text3.setOnTouchListener(new ChoiceTouchListener());
    }

    private final class ChoiceTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent event){
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            rootLayout.invalidate();
            return true;
        }

    }
}
