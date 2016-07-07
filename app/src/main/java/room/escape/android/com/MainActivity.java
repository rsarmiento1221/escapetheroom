package room.escape.android.com;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import org.w3c.dom.Text;

import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, EscapeTheRoomUtilities{
    private Button buttonMainQuest;
    private TextView textViewMainQuest;

    private int QR_OPERATION = MAIN_QUEST_REQUEST_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMainQuest = (Button) findViewById(R.id.buttonMainQuest);
        textViewMainQuest = (TextView) findViewById(R.id.textViewMainQuest);

        textViewMainQuest.setText("Find The Main Quest QR");
        buttonMainQuest.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MAIN_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK) {
           String result = data.getStringExtra(QR_READER_RESULT);

            if (MAIN_QUEST_RESULT_1.toString().equalsIgnoreCase(result)){
               textViewMainQuest.setText("Find The BlackSmith");
               QR_OPERATION = SUB_QUEST_REQUEST_1;
               buttonMainQuest.setText("Scan For BlackSmith");
            }
            else{
                showAlert("Invalid QR");
           }
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

        else {
            showAlert("Invalid QR");

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMainQuest:
                Intent i = new Intent(this, QRScannerActivity.class);
                startActivityForResult(i, QR_OPERATION);
            break;

        }
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
