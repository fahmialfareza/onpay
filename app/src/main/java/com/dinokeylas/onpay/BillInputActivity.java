package com.dinokeylas.onpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dinokeylas.onpay.model.MerchantModel;
import com.dinokeylas.onpay.util.Preferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.dinokeylas.onpay.util.Constant.COLLECTION.COLLECTION_MERCHANT;

public class BillInputActivity extends AppCompatActivity {

    private TextView merchatName, merchantAddress;
    private EditText nominal;
    private Button btnNext;
    private ProgressBar progressBar;
    private String merchantId;

    private FirebaseFirestore db;
    private MerchantModel merchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_input);

        merchatName = findViewById(R.id.tv_merchant_name);
        merchantAddress = findViewById(R.id.tv_merchant_address);
        nominal = findViewById(R.id.et_nominal);
        progressBar = findViewById(R.id.progressbar);
        btnNext = findViewById(R.id.btn_next_pay);

        merchantId = getIntent().getStringExtra("merchantId");

        db = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_MERCHANT).document(merchantId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.GONE);
                merchant = documentSnapshot.toObject(MerchantModel.class);

                assert merchant != null;
                merchatName.setText(merchant.getName());
                merchantAddress.setText(merchant.getLocation());
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nominal.getText().toString().trim();
                int nomm = Integer.valueOf(nom);

                Preferences preferences = new Preferences();
                preferences.setNominalPreferences(BillInputActivity.this, nomm);

                Intent intent = new Intent(BillInputActivity.this, PayOptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
