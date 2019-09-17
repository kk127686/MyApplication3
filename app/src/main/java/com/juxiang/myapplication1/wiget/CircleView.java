package com.juxiang.myapplication1.wiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.print.PrinterId;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {
    private Paint paint1;
    private float mWidth;
    private float mHeight;
    private float controlWidth;
    private float controlHeight;
    private float deg;
    public CircleView(Context context) {
        super(context);
        init();

    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth=getWidth();
        this.mHeight=getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawArc(getWidth()-700,getHeight()-100,getWidth()-300,getHeight()-300,0,90,true,paint1);
//        Path path2=new Path();
//        path2.moveTo(0, 0);//设置Path的起点
//        path2.quadTo(controlWidth, controlHeight, mWidth, 0); //设置贝塞尔曲线的控制点坐标和终点坐标
//        canvas.drawPath(path2, paint1);//画出贝塞尔曲线
//        canvas.drawArc(200,200,500,500,0,deg,true,paint1);
//        canvas.drawLine(350,350,(float)(150*Math.sin(deg)+350),(float) (150*Math.cos(deg)+350),paint1);
//        canvas.drawText("当前角度："+deg,300,350,paint1);
//        for(int i=0;i<=360;i+=30){
//            canvas.drawArc(210,210,490,490,0+i,3,false,paint1);
//        }
            canvas.drawLine(350,150,350,550,paint1);
            canvas.drawLine(150,350,550,350,paint1);
            for(int i=50;i<=200;i+=50){
                canvas.drawCircle(350,350,i,paint1);
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                controlHeight=event.getY();
                if(controlHeight>=500) {
                    controlHeight = 500;
                }
                deg=getDeg(event.getX(),event.getY());
                Log.e("角度",deg+"");
                this.postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                controlWidth=mWidth/2;
                controlHeight=0;
                this.postInvalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                Log.e("当前位置",event.getX()+" "+event.getY());
                break;
        }
        return true;
    }
    private void init(){
//        int[] color={Color.RED,Color.BLUE,Color.GRAY,Color.GREEN};
//        Shader shader=new SweepGradient(350,350,color,null);
        paint1=new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.GREEN);
        paint1.setTextSize(20);
        paint1.setStrokeWidth(2);
      //  paint1.setShader(shader);
        controlWidth=mWidth/2;
        controlHeight=0;
        deg=360;
    }
    private float getDeg(float x,float y){
        float banjin=150;
        float centerx=350;
        float centery=350;
        double sum=getLen(x,y,centerx,centery);
        double sum1=getLen(x,y,500,centery);
        double sum2=((banjin*banjin)+(sum*sum)-(sum1*sum1))/(2*banjin*sum);
        Log.e("SADSAD",sum2+"");
        double sum3=Math.acos(sum2);
        float sum4= (float)(sum3*180/3.14);
        if(y<350){
            sum4=360-sum4;
        }
        return  sum4;
    }
    /*
    求两点间距离
     */
    private double getLen(float x,float y,float startx,float starty){
        float x1=x-startx;
        float y1=y-starty;
        return Math.hypot(x1,y1);
    }
    public void setDeg(float deg){
        this.deg=deg;
        this.postInvalidate();
    }
}
