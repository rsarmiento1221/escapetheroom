package room.escape.android.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

public class ForgingActivity extends Activity {
    private Handler handler = new Handler();

    private ImageView imageViewAnimateForging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));

        setContentView(R.layout.activity_forging);

        imageViewAnimateForging = (ImageView) findViewById(R.id.imageViewAnimateForging);

        initiateValues();
    }

    private void initiateValues() {

        String path = "android.resource://" + getPackageName() + "/" + R.raw.forge;
        Ion.with(imageViewAnimateForging)
                .error(R.raw.forge)
                .animateGif(AnimateGifMode.ANIMATE)
                .load(path);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewAnimateForging.setImageResource(R.mipmap.sword);
                YoYo.with(Techniques.ZoomIn)
                        .duration(700)
                        .playOn(imageViewAnimateForging);
//                AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
//                animation1.setDuration(800);
//                imageViewAnimateForging.setAlpha(1f);
//                imageViewAnimateForging.startAnimation(animation1);
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        },4500);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }
}
