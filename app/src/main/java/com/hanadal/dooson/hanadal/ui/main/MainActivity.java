package com.hanadal.dooson.hanadal.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.TestActivity;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.app_info.AppInfoActivity;
import com.hanadal.dooson.hanadal.ui.my_item.ItemFragment;
import com.hanadal.dooson.hanadal.ui.my_challenge_list.MyChallengeListFragment;
import com.hanadal.dooson.hanadal.ui.qna.QnaFragment;
import com.hanadal.dooson.hanadal.ui.shop.ShopFragment;
import com.hanadal.dooson.hanadal.ui.trending.TrendingFragment;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, TextWatcher {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DoNotSwipeViewPager viewPager;
    FragmentViewPagerAdapter mainViewPagerAdapter;
    TabLayout tabLayout;
    EditText editText;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.main_view_pager);
        tabLayout = findViewById(R.id.main_tab);
        editText = findViewById(R.id.search_edit);

        navigationView.setNavigationItemSelectedListener(this);

        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new MyChallengeListFragment());
        mainViewPagerAdapter.addFragment(new TrendingFragment());
        mainViewPagerAdapter.addFragment(new QnaFragment());
        mainViewPagerAdapter.addFragment(new ItemFragment());
        mainViewPagerAdapter.addFragment(new ShopFragment());

        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("내 도전");
        tabLayout.getTabAt(1).setText("트렌딩");
        tabLayout.getTabAt(2).setText("Q&A");
        tabLayout.getTabAt(3).setText("아이템");
        tabLayout.getTabAt(4).setText("상점");

        editText.addTextChangedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_my_challenge:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_trending:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_question:
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_skin:
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_shop:
                viewPager.setCurrentItem(4);
                break;
            case R.id.nav_hart:
                //startActivity(new Intent(getApplicationContext(), TestActivity.class));
                break;
            case R.id.nav_info:
                startActivity(new Intent(getApplicationContext(), AppInfoActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_edit:
                editText.setHint("편린&QnA 검색");
                break;
            case R.id.nav_btn:
                if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

}
