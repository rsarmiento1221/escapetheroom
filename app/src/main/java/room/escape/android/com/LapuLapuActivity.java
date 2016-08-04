package room.escape.android.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class LapuLapuActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private HTextView hTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapu_lapu);

        hTextView  = (HTextView) findViewById(R.id.text);
        hTextView.setAnimateType(HTextViewType.SCALE);
        initializeSpeech();

    }

    private void initializeSpeech(){
        hTextView.animateText("Lapu-Lapu: Those filthy Spaniards!!!");
        setMessage("Lapu-Lapu: They have taken all our swords.", 3000);
        setMessage("Lapu-Lapu: We cannot fight them without it.", 6000);
        setMessage("Lapu-Lapu: Please lend us your hand", 9000);
        setMessage("Lapu-Lapu: Please find the blacksmith", 12000);
        setMessage("Lapu-Lapu: Ask him to forge us new weapons.", 15000);
        setMessage("Lapu-Lapu: Im counting on you.", 18000);

        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent resultIntent = new Intent();
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            }, 22000
        );
    }



    private void setMessage(final String message, int delay){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hTextView.animateText(message);
            }
        }, delay);
    }

}
