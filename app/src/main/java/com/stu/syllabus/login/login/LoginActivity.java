package com.stu.syllabus.login.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.HttpMethods;
import com.stu.syllabus.MainActivity;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.account)
    EditText accountEditText;
    @BindView(R.id.password)
    EditText passwordEditText;
    @BindView(R.id.login)
    Button login;

    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void setLogin() {
        Log.d(TAG, "setLogin: ");
        HttpMethods.getInstance().login(new Observer<Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Login login) {
                Log.d(TAG, "onNext: " + " " + login.getMessage());
                Log.d(TAG, "onNext: " + " " + login.getCode());
                if (login.getCode() == 200) {
                    sqLiteOpenHelper = new DataBaseHelper(LoginActivity.this, "user", null, 1);
                    sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, accountEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    protected int getContentView() {
        return R.layout.acticity_login;
    }
    private void saveUser() {

    }
}
