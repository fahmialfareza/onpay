package com.dinokeylas.onpay;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dinokeylas.onpay.util.Preferences;

import java.util.Objects;

public class SplitBillActivity extends AppCompatActivity implements View.OnClickListener {

    private int nominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        TextView tvNominal = findViewById(R.id.tv_nominal);
        Button btnPay = findViewById(R.id.btn_ok_split);

        Preferences preferences = new Preferences();
        nominal = preferences.getNominalPreferences(this);
        tvNominal.setText(String.valueOf(nominal));

        btnPay.setOnClickListener(this);
    }

    private void buildMessage(int nominal){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Detail");
        alertDialogBuilder
                .setMessage("Total Payment: Rp " + nominal)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Pay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        navigateToHomeActivity();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void navigateToHomeActivity(){
        Intent intent = new Intent(SplitBillActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ok_split) {
            buildMessage(nominal);
        }
    }

}
