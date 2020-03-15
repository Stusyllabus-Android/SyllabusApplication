package com.stu.syllabus.person.personal;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.util.ToastUtil;
import com.stu.syllabus.widget.GlideLoader;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;

public class PersonalActivity extends BaseActivity implements PersonalContract.view{

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.headImageDraweeView)
    ImageView headImageView;
    @BindView(R.id.accountTextView)
    TextView accountTextView;
    @BindView(R.id.nicknameEditText)
    EditText nicknameEditText;
    @BindView(R.id.signatureEditText)
    EditText signatureEditText;
    @BindView(R.id.cancelFAButton)
    FloatingActionButton cancelFAButton;
    @BindView(R.id.sureFAButton)
    FloatingActionButton sureFAButton;

    private String avatar;
    private String signature;
    private String nickname;

    @Inject
    PersonalPresenter personalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPersonalComponent.builder()
                .appComponent(appComponent)
                .personalModule(new PersonalModule(this))
                .build()
                .inject(this);
        personalPresenter.init();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
    }

    @Override
    public void loadInfo(String avatar, String account, String nickname, String signature) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.signature = signature;
        Glide.with(this).load(avatar).into(headImageView);
        accountTextView.setText(account);
        nicknameEditText.setText(nickname);
        if (signature != null && !signature.isEmpty()) {
            signatureEditText.setText(signature);
        }
    }

    @OnClick({R.id.headImageDraweeView, R.id.cancelFAButton, R.id.sureFAButton})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.headImageDraweeView:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorPrimaryDark))
                        .titleBgColor(getResources().getColor(R.color.colorPrimary))
                        .titleSubmitTextColor(getResources().getColor(R.color.material_textWhite_text))
                        .titleTextColor(getResources().getColor(R.color.material_textWhite_text))
                        .crop(1, 1, 500, 500)
                        // 开启单选   （默认为多选）
                        .singleSelect()
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                        .filePath("/ImageSelector/Pictures")
                        .build();
                ImageSelector.open(this, imageConfig);
                break;
            case R.id.cancelFAButton: finish(); break;
            case R.id.sureFAButton:
                Log.d(TAG, "onClick: ");
                if (nickname.equals(nicknameEditText.getText().toString()))
                    nickname = null;
                if (signature.equals(signatureEditText.getText().toString()))
                    signature = null;
                personalPresenter.updateUserInfo(avatar, nickname, signature); break;
        }
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PersonalActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> list = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            headImageView.setImageURI(Uri.parse(list.get(0)));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
