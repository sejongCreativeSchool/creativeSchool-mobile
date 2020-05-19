package com.booreum.booreum.constant;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public final class CheckValid {

    // 이메일 유효성 검사
    public static boolean isValidEmail(Context context, EditText ed) {
        String id = ed.getText().toString();
        Log.d("CheckValid", "in isValidEmail => id = " + id);
        if (id.isEmpty() || id == null) {
            // 이메일 공백
            Toast.makeText(context.getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
            // 이메일 형식 불일치
            Toast.makeText(context.getApplicationContext(), "아이디는 이메일 형식입니다.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    public static boolean isValidPassword(Context context, EditText ed) {
        String pw = ed.getText().toString();
        Log.d("CheckValid", "in isValidEmail => pw = " + pw);
        if (pw.isEmpty() || pw == null) {
            // 비밀번호 공백
            Toast.makeText(context.getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // 공 검사
    public static boolean isValidNotEmpty(Context context, EditText... ed) {
        Log.d("CheckValid", "in isValidNotEmpty ");

        for (EditText editText : ed) {
            String str = editText.getText().toString();
            if (str.isEmpty() || str == null) {
                //  공백
                Toast.makeText(context.getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;

    }
}
