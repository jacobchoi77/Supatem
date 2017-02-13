package com.moffcomm.supatem.ui.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.moffcomm.supatem.Constant;
import com.moffcomm.supatem.R;
import com.moffcomm.supatem.ui.main.MainActivity;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;

public class TutorialActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white_e)
                .buttonsColor(R.color.mint_light)
                .image(R.drawable.img_tutorial_1)
                .title(getString(R.string.tutorial_1_title))
                .description(getString(R.string.tutorial_1_desc))
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white_e)
                .buttonsColor(R.color.mint_light)
                .image(R.drawable.img_tutorial_2)
                .title(getString(R.string.tutorial_2_title))
                .description(getString(R.string.tutorial_2_desc))
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white_e)
                .buttonsColor(R.color.mint_light)
                .image(R.drawable.img_tutorial_3)
                .title(getString(R.string.tutorial_3_title))
                .description(getString(R.string.tutorial_3_desc))
                .build());

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.white_e)
                .buttonsColor(R.color.mint_light)
                .image(R.drawable.img_tutorial_4)
                .title(getString(R.string.tutorial_4_title))
                .description(getString(R.string.tutorial_4_desc))
                .build());
    }

    @Override
    public void onFinish() {
        startActivity(new Intent(TutorialActivity.this, MainActivity.class));
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putBoolean(Constant.PREF_TUTORIAL_DONE, true).commit();
    }
}
