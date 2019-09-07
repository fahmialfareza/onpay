package com.dinokeylas.onpay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dinokeylas.onpay.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.dinokeylas.onpay.util.Constant.COLLECTION.COLLECTION_USER;

public class AccountFragment extends Fragment implements View.OnClickListener{

    Intent intent;
    private static final String PICKER_ID = "pickerId";
    private String userId = "uid";
    private UserModel userModel;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;

    //for Fragment
    private static String profileImageUrl;
    private CardView cv_detail_profile, cv_edit_profile, cv_logout;
    private ProgressBar progress_bar;
    private ImageView iv_edit_profile, iv_detail_profile, logout;
    private CircleImageView civ_profile_image;
    private TextView tv_email, tv_picker_name;
    private Dialog dialog_profile;
    private Button btn_transaction, btn_transaction_history;
    private RecyclerView rv_transaction_recycler_view;

    //for Dialog
    CircleImageView d_profile_image;
    TextView d_picker_name, d_picker_name2, d_email_address, d_phone_number;


    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        //initialize database
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //get user id
        assert mUser != null;
        userId = mUser.getUid();

        //initialize all view
        cv_detail_profile = (CardView) view.findViewById(R.id.cv_detail_profile);
        cv_edit_profile = (CardView) view.findViewById(R.id.cv_edit_profile);
        cv_logout = (CardView) view.findViewById(R.id.cv_logout);
        civ_profile_image = (CircleImageView) view.findViewById(R.id.civ_profileImage);
        tv_email = (TextView) view.findViewById(R.id.tv_email_address);
        tv_picker_name = (TextView) view.findViewById(R.id.tv_picker_name);
        progress_bar = (ProgressBar) view.findViewById(R.id.progress_bar);

        //set on click listener
        cv_detail_profile.setOnClickListener(this);
        cv_edit_profile.setOnClickListener(this);
        cv_logout.setOnClickListener(this);

        //get data from firestore
        DocumentReference docRef = db.collection(COLLECTION_USER).document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userModel = documentSnapshot.toObject(UserModel.class);
                profileImageUrl = userModel.getProfileImageUrl();
                fillToLayout();
                loadImageProfilePicker(profileImageUrl);
                //loadData(false);
            }
        });

        return view;
    }

    private void fillToLayout() {
        String text = "(" + userModel.getEmailAddress() + ")";
        tv_email.setText(text);
        tv_picker_name.setText(userModel.getUserName());
        loadImageProfilePicker(profileImageUrl);
        progress_bar.setVisibility(View.GONE);
    }

    private void loadImageProfilePicker(String ImageUrl) {
        if (profileImageUrl != null) {
            Glide.with(this)
                    .load(ImageUrl)
                    .into(civ_profile_image);
        }
    }

    private void navigateToLoginActivity() {
        intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_detail_profile:
                //showProfileDialog();
                break;
            case R.id.cv_edit_profile:
                //navigateToEditProfileActivity();
                break;
            case R.id.cv_logout:
                mAuth.signOut();
                Objects.requireNonNull(getActivity()).finish();
                navigateToLoginActivity();
                break;
        }
    }
}
