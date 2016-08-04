package room.escape.android.com;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class EpilogueActivity extends Activity {

    private Handler handler = new Handler();

    private ImageView imageViewScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epilogue);

        imageViewScene = (ImageView) findViewById(R.id.imageViewScene);

        initializeValue();

    }

    private void initializeValue(){

        imageViewScene.setImageResource(R.mipmap.image1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewScene.setImageResource(R.mipmap.image2);
                AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
                animation1.setDuration(800);
                imageViewScene.setAlpha(1f);
                imageViewScene.startAnimation(animation1);
            }
        }, 2500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewScene.setImageResource(R.mipmap.image3);
                AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
                animation1.setDuration(800);
                imageViewScene.setAlpha(1f);
                imageViewScene.startAnimation(animation1);
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewScene.setImageResource(R.mipmap.image4);
                AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
                animation1.setDuration(800);
                imageViewScene.setAlpha(1f);
                imageViewScene.startAnimation(animation1);
            }
        }, 7500);

    }
}
