package room.escape.android.com;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class FinishQuestActivity extends AppCompatActivity implements View.OnClickListener , EscapeTheRoomUtilities{
    private Button buttonFinishQuest;
    private TextView textViewFinishQuestDesc;

    private int QR_OPERATION = MAIN_QUEST_REQUEST_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quest);

        buttonFinishQuest = (Button) findViewById(R.id.buttonFinishQuest);
        textViewFinishQuestDesc = (TextView) findViewById(R.id.textViewFinishQuestDesc);

        buttonFinishQuest.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MAIN_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra(QR_READER_RESULT);

            if (MAIN_QUEST_RESULT_1.toString().equalsIgnoreCase(result)){
                Intent i = new Intent(this, EpilogueActivity.class);
                startActivity(i);
                finish();
            }
            else{
                showAlert("Invalid QR");
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFinishQuest:
                Intent i = new Intent(this, QRScannerActivity.class);
                startActivityForResult(i, QR_OPERATION);
                break;

        }
    }

    private void showAlert(String message){
        new AlertDialog.Builder(FinishQuestActivity.this)
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
