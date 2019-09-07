package com.dinokeylas.onpay;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class Preferences {

    public void setNominalPreferences(Context context, int nominal){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Nominal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("nominal", nominal);
        editor.apply();
    }

    public int getNominalPreferences(Context context){
        SharedPreferences sharedPreferences = Objects.requireNonNull(context).getSharedPreferences("Nominal", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("nominal", 0);
    }
}
