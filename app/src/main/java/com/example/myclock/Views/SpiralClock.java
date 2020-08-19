package com.example.myclock.Views;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import com.example.myclock.R;
import java.util.ArrayList;
public class SpiralClock extends View {
    //*************************************************Variables
        //************************************Arrays
        private ArrayList<float[]> First=new ArrayList<>();
        private ArrayList<float[]> Second=new ArrayList<>();
        private ArrayList<float[]> Third=new ArrayList<>();
        private ArrayList<float[]> Fourth=new ArrayList<>();
        //************************************Others
        Paint paint ;
        RectF rectf ;
        Path path;
        Typeface typeface;
        private boolean init;
        private int Height ;
        String[] FirstNumbers={"1" , "2" , "3" , "4" , "5"},
                SecondNumbers={"6" , "7" , "8" , "9" , "10" ,"11"},
                ThirdNumbers={"12" , "13" , "14" , "15" , "16" ,"17"},
                FourthNumbers={"18" , "19" , "20" , "21" , "22" ,"23" , "24"};
    //*************************************************Variables

    public SpiralClock(Context context) {
        super(context);
      //  typeface= ResourcesCompat.getFont(context, R.font.numbers);
    }

    public SpiralClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       // typeface= ResourcesCompat.getFont(context, R.font.numbers);
    }

    public SpiralClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       // typeface= ResourcesCompat.getFont(context, R.font.numbers);
    }
    public SpiralClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initClock(){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Height=getHeight();
        paint = new Paint();
        rectf = new RectF();
        path = new Path();
        init=true;
        float[] arr={-90  , 180 , 0} , arr2 = {90 , 180 , 1 }, arr3={-90  , 180 , 2} , arr4 = {90 , 180 , 3 };
        First.add(arr);
        Second.add(arr2);
        Third.add(arr3);
        Fourth.add(arr4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!init)
        {
            initClock();
        }
        DrawSpiral(canvas);
        DrawBackgroundText(canvas);
    }

    private void DrawBackgroundText(Canvas canvas){
       SetPaintForBackgroundText();
       //******************SemiShadow
        rectf.left = Height*(45/215f);
        rectf.right = rectf.left + Height*(139/215f);
        rectf.top = Height*(42/215f);
        rectf.bottom = rectf.top + Height*(135/215f);
        paint.setColor(Color.BLACK);
        paint.setAlpha((int)(6/10f*paint.getAlpha()));
        paint.setMaskFilter(new BlurMaskFilter((5/215f)*Height, BlurMaskFilter.Blur.NORMAL));
        canvas.drawArc(rectf , 90 , 180 , true , paint);
        //******************FullShadow
        rectf.left = Height*(52/215f);
        rectf.right = rectf.left + Height*(122/215f);
        rectf.top = Height*(57/215f);
        rectf.bottom = rectf.top + Height*(120/215f);
        canvas.drawArc(rectf , -90 , 180 , true , paint);
        SetPaintForBackgroundText();
        //******************Semi
        rectf.left = Height*(45/215f);
        rectf.right = rectf.left + Height*(139/215f);
        rectf.top = Height*(42/215f);
        rectf.bottom = rectf.top + Height*(135/215f);
        canvas.drawArc(rectf , 90 , 180 , true , paint);
        //******************Full
        rectf.left = Height*(52/215f);
        rectf.right = rectf.left + Height*(122/215f);
        rectf.top = Height*(57/215f);
        rectf.bottom = rectf.top + Height*(120/215f);
        canvas.drawOval( rectf , paint);

    }
    private void DrawSpiral(Canvas canvas){
        //****************************Spiral
        //********First
        SetPaintForBGSpiral();
        float OffSet=(3f/215)*Height;
        rectf.left = Height*(43.96f/215);
        rectf.right = rectf.left + Height*(138.99f/215);
        rectf.top = Height*(48.68f/215);
        rectf.bottom = rectf.top + Height*(136.79f/215);
        canvas.drawArc(rectf , -90 , 180 , false , paint );
        SetLights(First , canvas);
        SetNumbers(canvas  , FirstNumbers , -60 , OffSet);
        //********Second
        SetPaintForBGSpiral();
        rectf.left = Height*(36.08f/215);
        rectf.right = rectf.left + Height*(155.83f/215);
        rectf.top = Height*(33.36f/215);
        rectf.bottom = rectf.top + Height*(152.11f/215);
        path.addArc(rectf , 85 , 180 );
        canvas.drawArc(rectf , 90 , 182 , false , paint );
        SetLights(Second , canvas);
        SetNumbers(canvas  , SecondNumbers , 90 , OffSet);
        //********Third
        OffSet=(0.5f/215)*Height;
        SetPaintForBGSpiral();
        rectf.left = Height*(29.36f/215);
        rectf.right = rectf.left + Height*(169.44f/215);
        rectf.top = Height*(33.37f/215);
        rectf.bottom = rectf.top + Height*(166.9f/215);
        path.addArc(rectf , -90 , 180 );
        canvas.drawArc(rectf , -90 , 182 , false , paint );
        SetLights(Third , canvas);
        SetNumbers(canvas  , ThirdNumbers , -91 , OffSet);
        //********Fourth
        OffSet=(1f/215)*Height;
        SetPaintForBGSpiral();
        rectf.left = Height*(21.61f/215);
        rectf.right = rectf.left + Height*(184.35f/215);
        rectf.top = Height*(18.99f/215);
        rectf.bottom = rectf.top + Height*(181.26f/215);
        path.addArc(rectf , 90 , 180 );
        canvas.drawArc(rectf , 90 , 185.5f , false , paint );
        SetLights(Fourth , canvas);
        SetNumbers(canvas  , FourthNumbers , 89 , OffSet);
    }

    //************************************************************************lights
    private void SetLights(ArrayList<float[]> arrayList ,Canvas canvas){
        for(float[] StartEnd : arrayList){
            SetColorForLights(StartEnd[2]);
            paint.setStrokeWidth((4/215f)*Height);
            paint.setMaskFilter(new BlurMaskFilter((3f/215)*Height, BlurMaskFilter.Blur.NORMAL));
            canvas.drawArc(rectf , StartEnd[0] , StartEnd[1] ,false , paint);
            paint.setMaskFilter(null);
            paint.setStrokeWidth((5/215f)*Height);
            canvas.drawArc(rectf , StartEnd[0] , StartEnd[1] ,false , paint);
        }
    }
    private void SetColorForLights(float i){
        if(i==3)
            paint.setColor((getResources().getColor(R.color.GreenLight)));
        else if(i==2)
            paint.setColor((getResources().getColor(R.color.YellowLight)));
        else if (i==1)
            paint.setColor((getResources().getColor(R.color.RedLight)));
        else if (i==0)
            paint.setColor((getResources().getColor(R.color.DarkLight)));
        else
            paint.setColor((getResources().getColor(R.color.GreyLight)));
    }
    //************************************************************************lights

    //************************************************************************Numbers
    private void SetNumbers(Canvas canvas, String[] Numbers , int angle , float OffSet){
        String string;
        for(String number : Numbers){
            string = number;
            path.reset();
            path.addArc(rectf , angle-4 , 10 );
            angle+=30;
            SetBackgroundforNumbers(canvas , path);

            paint.reset();
            paint.setAntiAlias(true);
            paint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,(4/215f)*Height,
                    getResources().getDisplayMetrics()));
            paint.setColor(Color.BLACK);
            paint.setTypeface(typeface);
            if(string.equals("10") )
                OffSet=0;
            canvas.drawTextOnPath(string , path ,OffSet , (4/215f)*Height , paint);
        }
    }
    private void SetBackgroundforNumbers(Canvas canvas , Path path){
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((10/215f)*Height);
        paint.setMaskFilter(new BlurMaskFilter((1/215f)*Height, BlurMaskFilter.Blur.SOLID));
        paint.setColor(Color.WHITE);
        canvas.drawPath(path , paint);
    }
    //************************************************************************Numbers

    //************************************************************************Paints
    private void SetPaintForBackgroundText(){
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.BG_Clock));
    }
    private void SetPaintForBGSpiral(){
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor((Color.WHITE));
        paint.setStrokeWidth((12/215f)*Height);
        paint.setStyle(Paint.Style.STROKE);
    }
    //************************************************************************Paints

}
