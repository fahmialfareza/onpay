package com.dinokeylas.onpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuickPayFragment extends Fragment {

    public QuickPayFragment() {
        // Required empty public constructor
    }

    public static QuickPayFragment newInstance(String param1, String param2) {
        return new QuickPayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_pay, container, false);
        return view;
    }

}
