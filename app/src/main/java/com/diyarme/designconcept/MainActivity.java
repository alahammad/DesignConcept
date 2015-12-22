package com.diyarme.designconcept;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.button)
    MorphingButton morphing;

    @Bind(R.id.parent)
    RelativeLayout mParent;

    @Bind(R.id.editText)
    EditText mEditText;

    boolean isMorphed;
    int mOrgWidth;
    int mOrgHeight;

    int mParentWidth,mParentHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            Toast.makeText(this,version,Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // Call here getWidth() and getHeight()
        mOrgWidth = morphing.getWidth();
        mOrgHeight = morphing.getHeight();

        mParentHeight = mParent.getHeight();
        mParentWidth = mParent.getWidth();
    }

    @OnClick(R.id.button)
    public void onClick (View v){
        int height = 0, width =0 ;
        if (!isMorphed) {
            height =mParentHeight;
            width = mParentWidth;
            mEditText.setVisibility(View.GONE);
        }else {
            height= mOrgHeight;
            width = mOrgWidth;
            mEditText.setVisibility(View.VISIBLE);
        }
        morph(width,height);
        isMorphed=!isMorphed;
    }

    private void morph(int width, int height) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(160)
//                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(width)
                .height(height)
                .text("Done !")
                .color(getResources().getColor(R.color.colorPrimary))

                .colorPressed(getResources().getColor(R.color.colorAccent));

//                .text(getString(R.string.mb_button));
        morphing.morph(square);
    }


    public int dimen(@DimenRes int resId) {
        return (int) getResources().getDimension(resId);
    }
}
