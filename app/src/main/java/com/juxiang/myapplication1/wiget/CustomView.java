package com.juxiang.myapplication1.wiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View {

    private float startX;//圆点坐标X
    private float startY;//圆点坐标Y
    private int mHeight;//屏幕高度
    private int mWidth;//屏幕宽度
    private float Radius=50;//圆点半径
    private float Radius1=70;//默认圆点大小
    private Paint paint1;//圆点画笔1
    private Paint paint2;//圆点画笔2
    private Paint paint3;//背景画笔
    private boolean onBall;//点击是否在球上
    private OnPosition onPosition;//方向回调
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       mHeight=this.getHeight();
       mWidth=this.getWidth();
       startX=mWidth/10;
       startY=mHeight/2;
       paint1=new Paint();
       paint1.setColor(Color.BLUE);
       paint1.setDither(true);
       paint1.setTextSize(70);
       paint2=new Paint();
       paint2.setColor(Color.RED);
       paint2.setStrokeWidth(5);
        paint2.setDither(true);
       paint3=new Paint();
       paint3.setColor(Color.GREEN);
        paint3.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF=new RectF();

        canvas.drawLine(mWidth/10,100,mWidth/10,mHeight-100,paint2);
        canvas.drawCircle(mWidth/10,mHeight/2,Radius1,paint3);
        canvas.drawCircle(mWidth/10,100+Radius,Radius,paint3);
        canvas.drawText("前",mWidth/10-35,125+Radius,paint1);
        canvas.drawCircle(mWidth/10,mHeight-100-Radius,Radius,paint3);
        canvas.drawText("后",mWidth/10-35,mHeight-75-Radius,paint1);
        canvas.drawCircle(startX,startY,Radius,paint1);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    if(onBall){
                        Log.e("正在移动",event.getX()+" "+event.getY());
                        Radius1=50;

                        startY=(int)event.getY();
                        if(startY<=100){
                            startX=mWidth/10;
                            startY=100+Radius;
                        }else if(startY>mHeight-100){
                            startX=mWidth/10;
                            startY=mHeight-100-Radius;
                        }
                        if(startX<100){
                            startY=mHeight/2;
                            startX=100+Radius;
                        }else if(startX>mWidth+100){
                            startY=mHeight/2;
                            startX=mWidth+100;
                        }
                        this.postInvalidate();
                        if(startY<mHeight/2){
                            onPosition.onUp(startX,startY);
                        }else{
                            onPosition.onDown(startX,startY);
                        }
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    Log.e("按下",event.getX()+" "+event.getY());
                    Log.e("按下",onBall+"");
                    float newx=event.getX();
                    float newy=event.getY();
                    onBall=onCircle(newx,newy);
                    Toast.makeText(getContext(),"你点到我了",Toast.LENGTH_SHORT).show();
                    break;
                case MotionEvent.ACTION_UP:
                    startX=mWidth/10;
                    startY=mHeight/2;
                    Radius1=70;
                    this.postInvalidate();
                    break;
            }
        return true;

    }
    private boolean onCircle(float newx ,float newy){
        double sqrt=Math.sqrt((newx-startX)*(newx-startX)+(newy-startY)*(newy-startY));
        if(sqrt<Radius){
            return true;
        }else{
            return false;
        }
    }
    public void setOnDiction(OnPosition onPosition){
        this.onPosition=onPosition;
    }
    public interface OnPosition{
        void onUp(float x, float y);
        void onDown(float x, float y);
    }
}
