package de.planet_heavy.apps.kissthemeli;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

/**
 * Created by Benny on 31.12.2014.
 */
public class WimmelView extends View {

    private static final int[] images =
            {
                    R.drawable.distract_01,
                    R.drawable.distract_02,
                    R.drawable.distract_03,
                    R.drawable.distract_04,
                    R.drawable.distract_05,
                    R.drawable.distract_06,
                    R.drawable.distract_07
            };

    private Random rnd;
    private long randomSeed=1;
    private int imageCount;
    private Paint paint = new Paint();

    /**
     *
     * @param context
     */
    public WimmelView(Context context) {
        super(context);

        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rnd = new Random(randomSeed);

        for(int image : images) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), image);

            for(int i=0; i<imageCount/images.length; i++) {

                float left = (float)(rnd.nextFloat() * (getWidth() - bmp.getWidth()));
                float top = (float)(rnd.nextFloat() * (getHeight() - bmp.getHeight()));

                canvas.drawBitmap(bmp, left, top, paint);

            }

            bmp.recycle();
        }
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
        randomSeed = System.currentTimeMillis();
        invalidate();
    }
}
