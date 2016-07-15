package room.escape.android.com;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import room.escape.android.com.Adapter.BlackSmithRequirementAdapter;
import room.escape.android.com.Model.BlackSmithRequirements;
import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class BlackSmithActivity extends AppCompatActivity implements View.OnClickListener, EscapeTheRoomUtilities{
    private ListView listViewRequirements;

    private TextView textViewBlackSmith;
    private TextView textViewSubBlackSmith;

    private BlackSmithRequirementAdapter blackSmithRequirementAdapter;

    private Button buttonScanQR;

    private ArrayList<BlackSmithRequirements> blackSmithRequirementsArrayList = new ArrayList<BlackSmithRequirements>();

    private int QR_OPERATION = BLACKSMITH_QUEST_REQUEST;

    private boolean isFindScanned = false;
    //private boolean isFinishCollectingIngredients = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_smith);

        listViewRequirements = (ListView) findViewById(R.id.listViewRequirements);

        textViewBlackSmith  = (TextView) findViewById(R.id.textViewBlackSmith);
        textViewSubBlackSmith = (TextView) findViewById(R.id.textViewSubBlackSmith);

        buttonScanQR = (Button) findViewById(R.id.buttonScanQR);

        blackSmithRequirementAdapter = new BlackSmithRequirementAdapter(this, R.layout.list_blacksmith_requirement, blackSmithRequirementsArrayList);
        listViewRequirements.setAdapter(blackSmithRequirementAdapter);

        buttonScanQR.setOnClickListener(this);

        initiateValues();
    }

    private void initiateValues(){
        textViewBlackSmith.setText("BlackSmith Requirements");
        textViewSubBlackSmith.setText("Hi, in order to help Lapu-Lapu, I need the following requirements");

        BlackSmithRequirements bs1 = new BlackSmithRequirements();
        bs1.setName("Iron");
        bs1.setDataName(BLACKSMITH_INGREDIENTS_1);
        bs1.setValue(false);
        BlackSmithRequirements bs2 = new BlackSmithRequirements();
        bs2.setName("Carabao Horn");
        bs2.setDataName(BLACKSMITH_INGREDIENTS_4);
        bs2.setValue(false);
        BlackSmithRequirements bs3 = new BlackSmithRequirements();
        bs3.setName("String");
        bs3.setDataName(BLACKSMITH_INGREDIENTS_2);
        bs3.setValue(false);

        blackSmithRequirementsArrayList.add(bs1);
        blackSmithRequirementsArrayList.add(bs2);
        blackSmithRequirementsArrayList.add(bs3);

        blackSmithRequirementAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BLACKSMITH_QUEST_REQUEST && resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra(QR_READER_RESULT);

            for (int i = 0; i < blackSmithRequirementsArrayList.size(); i++){
                if (result.equalsIgnoreCase(blackSmithRequirementsArrayList.get(i).getDataName())){
                    isFindScanned = true;
                    blackSmithRequirementsArrayList.get(i).setValue(true);
                    break;
                }
            }

            if (isFindScanned){
                isFindScanned = false;
                blackSmithRequirementAdapter.notifyDataSetChanged();
            }
            else{
                showAlert("Invalid QR");
            }

            if (isFinishCollectingIngredients()){
                QR_OPERATION = SUB_QUEST_REQUEST_1;
                textViewSubBlackSmith.setText("You Gathered all the missing requirements. Time to go back to finish the quest");
            }

        }

        else if (requestCode == SUB_QUEST_REQUEST_1 && resultCode == Activity.RESULT_OK){
            String result = data.getStringExtra(QR_READER_RESULT);

            if (SUB_QUEST_RESULT_BLACKSMITH.toString().equalsIgnoreCase(result)){
                Intent i = new Intent(this, FinishQuestActivity.class);
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
            case R.id.buttonScanQR:
                Intent i = new Intent(this, QRScannerActivity.class);
                startActivityForResult(i, QR_OPERATION);
                break;
        }
    }


    private boolean isFinishCollectingIngredients (){
        for (int i = 0; i < blackSmithRequirementsArrayList.size(); i++){
          if (blackSmithRequirementsArrayList.get(i).getValue() == false){
              return false;
          }
        }
        return true;
    }
    private void showAlert(String message){
        new AlertDialog.Builder(BlackSmithActivity.this)
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
