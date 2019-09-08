package com.dinokeylas.onpay;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result result) {

        Intent intent = new Intent(QRScannerActivity.this, BillInputActivity.class);
        intent.putExtra("merchantId", result.getText());
        startActivity(intent);

//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setTitle("Details");
//        alertDialogBuilder
//                .setMessage("Nama restoran" + result.getText())
//                .setIcon(R.mipmap.ic_launcher)
//                .setCancelable(false)
//                .setPositiveButton("Next",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//
//                    }
//                })
//                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//
//        mScannerView.resumeCameraPreview(this);
    }
}
