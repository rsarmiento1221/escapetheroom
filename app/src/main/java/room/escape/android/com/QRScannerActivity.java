package room.escape.android.com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import room.escape.android.com.Utility.EscapeTheRoomUtilities;

public class QRScannerActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener, EscapeTheRoomUtilities {
    private QRCodeReaderView mydecoderview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
