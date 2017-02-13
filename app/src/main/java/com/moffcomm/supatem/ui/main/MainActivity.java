package com.moffcomm.supatem.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.moffcomm.supatem.R;
import com.moffcomm.supatem.data.local.model.DBManager;
import com.moffcomm.supatem.data.local.model.UserEntity;
import com.moffcomm.supatem.ui.SimpleDialogFragment;
import com.moffcomm.supatem.ui.login_join.LoginJoinActivity;
import com.moffcomm.supatem.ui.main.event.EventFragment;
import com.moffcomm.supatem.ui.main.mypage.MyPageFragment;
import com.moffcomm.supatem.ui.main.quest.QuestFragment;
import com.moffcomm.supatem.ui.main.supadeal.SupaDealFragment;
import com.moffcomm.supatem.ui.main.supafree.SupaFreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private DBManager dbManager;
    private UserEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        dbManager = DBManager.getInstance(this);
        user = dbManager.getUser();
    }

    private void setupTabIcons() {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.customtab, tabLayout, false);
        textView.setText(R.string.main_supafree);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_access_time_white_24dp, 0, 0);
        tabLayout.getTabAt(0).setCustomView(textView);

        textView = (TextView) LayoutInflater.from(this).inflate(R.layout.customtab, tabLayout, false);
        textView.setText(R.string.main_supadeal);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_audiotrack_white_24dp, 0, 0);
        tabLayout.getTabAt(1).setCustomView(textView);

        textView = (TextView) LayoutInflater.from(this).inflate(R.layout.customtab, tabLayout, false);
        textView.setText(R.string.main_quest);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorite_white_24dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(textView);

        textView = (TextView) LayoutInflater.from(this).inflate(R.layout.customtab, tabLayout, false);
        textView.setText(R.string.main_event);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_audiotrack_white_24dp, 0, 0);
        tabLayout.getTabAt(3).setCustomView(textView);

        textView = (TextView) LayoutInflater.from(this).inflate(R.layout.customtab, tabLayout, false);
        textView.setText(R.string.main_mypage);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_access_time_white_24dp, 0, 0);
        tabLayout.getTabAt(4).setCustomView(textView);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SupaFreeFragment());
        adapter.addFragment(new SupaDealFragment());
        adapter.addFragment(new QuestFragment());
        adapter.addFragment(new EventFragment());
        adapter.addFragment(new MyPageFragment());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2 && user == null) {
            showLoginJoinDialog();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

    private void showLoginJoinDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        SimpleDialogFragment loginDialogFragment = SimpleDialogFragment.newInstance(
                R.string.dialog_login_title, R.string.dialog_login_msg, R.string.dialog_login_ok);

        loginDialogFragment.setCallback(new SimpleDialogFragment.CallbackSimpleDialog() {
            @Override
            public void onPositiveClick() {
                startActivity(new Intent(MainActivity.this, LoginJoinActivity.class));
            }

            @Override
            public void onNegativeClick() {
            }

            @Override
            public void onNeutralClick() {
            }
        });
        loginDialogFragment.show(ft, "dialog");
    }
}