package room.escape.android.com;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import java.util.ArrayList;
import java.util.List;

import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class QRScannerActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener, EscapeTheRoomUtilities {
    private QRCodeReaderView mydecoderview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(QRScannerActivity.this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(QRScannerActivity.this,
                        Manifest.permission.CAMERA)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    ActivityCompat.requestPermissions(QRScannerActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);

                } else {

                    // No explanation needed, we can request the permission.
                    //Toast.makeText(getActivity(),"else",Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(QRScannerActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
            else{
                setContentView(R.layout.activity_qrscanner);
                mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
                mydecoderview.setOnQRCodeReadListener(this);

                if (mydecoderview != null){
                    mydecoderview.getCameraManager().startPreview();
                }
            }

        } else{
            // do something for phones running an SDK before lollipop
            setContentView(R.layout.activity_qrscanner);
            mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
            mydecoderview.setOnQRCodeReadListener(this);

            if (mydecoderview != null){
                mydecoderview.getCameraManager().startPreview();
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setContentView(R.layout.activity_qrscanner);
                    mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
                    mydecoderview.setOnQRCodeReadListener(this);

                    if (mydecoderview != null){
                        mydecoderview.getCameraManager().startPreview();
                    }

                } else {
                    System.exit(0);
                    finish();
                    finishAffinity();
                }
                return;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mydecoderview != null)
            mydecoderview.getCameraManager().stopPreview();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(QR_READER_RESULT, text);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        finish();
    }

    @Override
    public void cameraNotFound() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(QR_READER_RESULT, "");
//        setResult(Activity.RESULT_CANCELED, resultIntent);
//        finish();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(QR_READER_RESULT, "");
//        setResult(Activity.RESULT_CANCELED, resultIntent);
//        finish();
    }
}
