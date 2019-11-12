package com.example.dentalprofileapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    Context m_context;

    public ToastUtil(Context context) {
        m_context = context;
    }

    public void createToastMessage(String message) {
        Toast.makeText(m_context, message, Toast.LENGTH_LONG).show();
    }
}
