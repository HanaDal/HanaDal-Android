package com.hanadal.dooson.hanadal.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.Profile;
import com.hanadal.dooson.hanadal.ui.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.app_info.AppInfoActivity;
import com.hanadal.dooson.hanadal.ui.favorite.FavoriteActivity;
import com.hanadal.dooson.hanadal.ui.my_challenge.MyChallengeFragment;
import com.hanadal.dooson.hanadal.ui.qna.QnaFragment;
import com.hanadal.dooson.hanadal.ui.search.SearchFragment;
import com.hanadal.dooson.hanadal.ui.trending.TrendingFragment;
import com.hanadal.dooson.hanadal.ui.view.DoNotSwipeViewPager;
import com.hanadal.dooson.hanadal.util.UtilClass;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, TabLayout.OnTabSelectedListener{

    private DrawerLayout drawerLayout;
    private DoNotSwipeViewPager viewPager;
    private TabLayout tabLayout;
    public EditText editText;

    private CircleImageView userImage;
    private TextView userName;

    private int lastPagerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.main_view_pager);
        tabLayout = findViewById(R.id.main_tab);
        editText = findViewById(R.id.search_edit);

        View headerLayout =
                navigationView.inflateHeaderView(R.layout.nav_header_main);

        userImage = headerLayout.findViewById(R.id.profile_image);
        userName = headerLayout.findViewById(R.id.name_text);

        FragmentViewPagerAdapter mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new MyChallengeFragment());
        mainViewPagerAdapter.addFragment(new TrendingFragment());
        mainViewPagerAdapter.addFragment(new QnaFragment());
        mainViewPagerAdapter.addFragment(new SearchFragment());

        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeTabAt(3);
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_my_challenge);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_trending);
        tabLayout.getTabAt(1).getIcon().setColorFilter(
                ContextCompat.getColor(getApplicationContext(), R.color.weekWhite), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_question_answer);
        tabLayout.getTabAt(2).getIcon().setColorFilter(
                ContextCompat.getColor(getApplicationContext(), R.color.weekWhite), PorterDuff.Mode.SRC_IN);

        navigationView.setNavigationItemSelectedListener(this);
        tabLayout.addOnTabSelectedListener(this);

        Connector.api.getProfile(UtilClass.getToken(getApplicationContext()))
                .enqueue(new Res<Profile>(getApplicationContext()) {
            @Override
            public void callback(int code, Profile body) {
                if(code == 200) {
                    RequestOptions options = new RequestOptions()
                            .fitCenter()
                            .override(450)
                            .format(DecodeFormat.PREFER_ARGB_8888);
                    userName.setText(body.name);
                    Glide.with(getApplicationContext())
                            .asBitmap()
                            .load(body.picture)
                            .apply(options).into(userImage);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        UtilClass.loadProgress(this);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 3) {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(lastPagerNum);
            return;
        }

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
                lastPagerNum = 0;
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_trending:
                lastPagerNum = 1;
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_question:
                lastPagerNum = 2;
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_hart:
                startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
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
                if (lastPagerNum != 3) {
                    lastPagerNum = viewPager.getCurrentItem();
                    tabLayout.setVisibility(View.GONE);
                    editText.setHint("편린&QnA 검색");
                    viewPager.setCurrentItem(3);
                }
                break;
            case R.id.nav_btn:
                if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        lastPagerNum =  tab.getPosition();
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.white);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.weekWhite);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }
}
