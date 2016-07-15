package room.escape.android.com;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import org.w3c.dom.Text;

import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EscapeTheRoomUtilities{

    private Button buttonMainQuest;
    private Button buttonHint;

    private TextView textViewMainQuest;
    private TextView textViewSubQuest;

    private ImageView imageViewMain;

//    private ImageView imageHide1;
//    private ImageView imageHide2;
//    private ImageView imageHide3;
//    private ImageView imageHide4;
//    private ImageView imageHide5;
//    private ImageView imageHide6;
//    private ImageView imageHide7;
//    private ImageView imageHide8;
//    private ImageView imageHide9;

//    private LinearLayout linearHideImage;

    private int MAIN_OPERATION = MAIN_QUEST_REQUEST_1;

    private int hintCounter = 0;

    private Animator mCurrentAnimator;

    private int mShortAnimationDuration;

    private ClipDrawable mImageDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMainQuest = (Button) findViewById(R.id.buttonMainQuest);
        buttonHint = (Button) findViewById(R.id.buttonHint);

        textViewMainQuest = (TextView) findViewById(R.id.textViewMainQuest);
        textViewSubQuest = (TextView) findViewById(R.id.textViewSubQuest);

        imageViewMain = (ImageView) findViewById(R.id.imageviewMain);

//        imageHide1 = (ImageView) findViewById(R.id.imageHide1);
//        imageHide2 = (ImageView) findViewById(R.id.imageHide2);
//        imageHide3 = (ImageView) findViewById(R.id.imageHide3);
//        imageHide4 = (ImageView) findViewById(R.id.imageHide4);
//        imageHide5 = (ImageView) findViewById(R.id.imageHide5);
//        imageHide6 = (ImageView) findViewById(R.id.imageHide6);
//        imageHide7 = (ImageView) findViewById(R.id.imageHide7);
//        imageHide8 = (ImageView) findViewById(R.id.imageHide8);
//        imageHide9 = (ImageView) findViewById(R.id.imageHide9);

//        linearHideImage = (LinearLayout) findViewById(R.id.linearHideImage);


        buttonMainQuest.setOnClickListener(this);
        buttonHint.setOnClickListener(this);

        initializeScreen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MAIN_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK) {
           String result = data.getStringExtra(QR_READER_RESULT);

            if (MAIN_QUEST_RESULT_1.toString().equalsIgnoreCase(result)){
                MAIN_OPERATION =  MAIN_ANIM_QUEST_REQUEST_1;
                Intent i = new Intent(this, LapuLapuActivity.class);

                startActivityForResult(i, MAIN_OPERATION);

            }
            else{
                showAlert("Invalid QR");
           }
        }

        else if (requestCode == MAIN_ANIM_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK){

            MAIN_OPERATION = SUB_QUEST_REQUEST_1;

            imageViewMain.setImageResource(R.mipmap.blacksmith);
            buttonHint.setVisibility(View.GONE);

            textViewMainQuest.setText("Find The BlackSmith");
            textViewSubQuest.setText("a person who makes and repairs things in iron by hand");
            buttonMainQuest.setText("Scan For BlackSmith");
        }

        else if (requestCode == SUB_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK){
            String result = data.getStringExtra(QR_READER_RESULT);

            if (SUB_QUEST_RESULT_BLACKSMITH.toString().equalsIgnoreCase(result)){
                Intent i = new Intent(this, BlackSmithActivity.class);
                startActivity(i);
                finish();
            }
            else{
                showAlert("Invalid QR");
            }
        }

        else if (resultCode == Activity.RESULT_CANCELED){
            // do nothing
        }

        else {
            showAlert("Invalid QR");

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMainQuest:
                Intent i = new Intent(this, QRScannerActivity.class);
                startActivityForResult(i, MAIN_OPERATION);
            break;

            case R.id.buttonHint:
                hintCounter ++;
                showHint();
                break;

        }
    }

    private void initializeScreen() {
        textViewMainQuest.setText("FIRST QUEST");
        textViewSubQuest.setText(System.getProperty("line.separator") + "Someone altered our history. Please find and scan the first recorded battle in Philippines");
        mImageDrawable = (ClipDrawable) imageViewMain.getDrawable();
        mImageDrawable.setLevel(2500);
        // imageHide5.setVisibility(View.INVISIBLE);

//        final View thumb1View = findViewById(R.id.thumb_button_1);
//        thumb1View.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                zoomImageFromThumb(thumb1View, R.mipmap.diorama_lapu_lapu);
//            }
//        });
//
//        // Retrieve and cache the system's default "short" animation time.
//        mShortAnimationDuration = getResources().getInteger(
//                android.R.integer.config_shortAnimTime);
//    }
    }

    private void showHint(){
        if (hintCounter == 1){
            mImageDrawable.setLevel(5000);
        }
        else if (hintCounter == 2){
            mImageDrawable.setLevel(10000);
            buttonHint.setVisibility(View.GONE);
        }
        else{
            hintCounter = 2;
        }

    }

//    private void zoomImageFromThumb(final View thumbView, int imageResId) {
//        // If there's an animation in progress, cancel it
//        // immediately and proceed with this one.
//        if (mCurrentAnimator != null) {
//            mCurrentAnimator.cancel();
//        }
//
//        // Load the high-resolution "zoomed-in" image.
//        final ImageView expandedImageView = (ImageView) findViewById(
//                R.id.expanded_image);
//        expandedImageView.setImageResource(imageResId);
//
//        // Calculate the starting and ending bounds for the zoomed-in image.
//        // This step involves lots of math. Yay, math.
//        final Rect startBounds = new Rect();
//        final Rect finalBounds = new Rect();
//        final Point globalOffset = new Point();
//
//        // The start bounds are the global visible rectangle of the thumbnail,
//        // and the final bounds are the global visible rectangle of the container
//        // view. Also set the container view's offset as the origin for the
//        // bounds, since that's the origin for the positioning animation
//        // properties (X, Y).
//        thumbView.getGlobalVisibleRect(startBounds);
//        findViewById(R.id.container)
//                .getGlobalVisibleRect(finalBounds, globalOffset);
//        startBounds.offset(-globalOffset.x, -globalOffset.y);
//        finalBounds.offset(-globalOffset.x, -globalOffset.y);
//
//        // Adjust the start bounds to be the same aspect ratio as the final
//        // bounds using the "center crop" technique. This prevents undesirable
//        // stretching during the animation. Also calculate the start scaling
//        // factor (the end scaling factor is always 1.0).
//        float startScale;
//        if ((float) finalBounds.width() / finalBounds.height()
//                > (float) startBounds.width() / startBounds.height()) {
//            // Extend start bounds horizontally
//            startScale = (float) startBounds.height() / finalBounds.height();
//            float startWidth = startScale * finalBounds.width();
//            float deltaWidth = (startWidth - startBounds.width()) / 2;
//            startBounds.left -= deltaWidth;
//            startBounds.right += deltaWidth;
//        } else {
//            // Extend start bounds vertically
//            startScale = (float) startBounds.width() / finalBounds.width();
//            float startHeight = startScale * finalBounds.height();
//            float deltaHeight = (startHeight - startBounds.height()) / 2;
//            startBounds.top -= deltaHeight;
//            startBounds.bottom += deltaHeight;
//        }
//
//        // Hide the thumbnail and show the zoomed-in view. When the animation
//        // begins, it will position the zoomed-in view in the place of the
//        // thumbnail.
//        thumbView.setAlpha(0f);
//        expandedImageView.setVisibility(View.VISIBLE);
//
//        // Set the pivot point for SCALE_X and SCALE_Y transformations
//        // to the top-left corner of the zoomed-in view (the default
//        // is the center of the view).
//        expandedImageView.setPivotX(0f);
//        expandedImageView.setPivotY(0f);
//
//        // Construct and run the parallel animation of the four translation and
//        // scale properties (X, Y, SCALE_X, and SCALE_Y).
//        AnimatorSet set = new AnimatorSet();
//        set
//                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
//                        startBounds.left, finalBounds.left))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
//                        startBounds.top, finalBounds.top))
//                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
//                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
//                View.SCALE_Y, startScale, 1f));
//        set.setDuration(mShortAnimationDuration);
//        set.setInterpolator(new DecelerateInterpolator());
//        set.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mCurrentAnimator = null;
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                mCurrentAnimator = null;
//            }
//        });
//        set.start();
//        mCurrentAnimator = set;
//
//        // Upon clicking the zoomed-in image, it should zoom back down
//        // to the original bounds and show the thumbnail instead of
//        // the expanded image.
//        final float startScaleFinal = startScale;
//        expandedImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mCurrentAnimator != null) {
//                    mCurrentAnimator.cancel();
//                }
//
//                // Animate the four positioning/sizing properties in parallel,
//                // back to their original values.
//                AnimatorSet set = new AnimatorSet();
//                set.play(ObjectAnimator
//                        .ofFloat(expandedImageView, View.X, startBounds.left))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.Y,startBounds.top))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.SCALE_X, startScaleFinal))
//                        .with(ObjectAnimator
//                                .ofFloat(expandedImageView,
//                                        View.SCALE_Y, startScaleFinal));
//                set.setDuration(mShortAnimationDuration);
//                set.setInterpolator(new DecelerateInterpolator());
//                set.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimator = null;
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//                        thumbView.setAlpha(1f);
//                        expandedImageView.setVisibility(View.GONE);
//                        mCurrentAnimator = null;
//                    }
//                });
//                set.start();
//                mCurrentAnimator = set;
//            }
//        });
//    }

    private AnimationSet animation(){
        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation a = new TranslateAnimation(
                Animation.ABSOLUTE,200, Animation.ABSOLUTE,200,
                Animation.ABSOLUTE,200, Animation.ABSOLUTE,200);
        a.setDuration(1000);

        RotateAnimation r = new RotateAnimation(0f, -90f,200,200);
        r.setStartOffset(1000);
        r.setDuration(1000);

        animationSet.addAnimation(a);
        animationSet.addAnimation(r);

        return animationSet;
    }

    private void showAlert(String message){
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Escape The Room")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
