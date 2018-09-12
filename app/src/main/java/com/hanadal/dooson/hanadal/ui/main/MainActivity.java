package com.hanadal.dooson.hanadal.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.app_info.AppInfoActivity;
import com.hanadal.dooson.hanadal.ui.my_item.ItemFragment;
import com.hanadal.dooson.hanadal.ui.my_challenge_list.MyChallengeListFragment;
import com.hanadal.dooson.hanadal.ui.qna.QnaFragment;
import com.hanadal.dooson.hanadal.ui.search.SearchFragment;
import com.hanadal.dooson.hanadal.ui.shop.ShopFragment;
import com.hanadal.dooson.hanadal.ui.trending.TrendingFragment;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

/**
 * TODO
 * <p>
 * 매인 액티비티에서 모든 데이터를 서버에서 다 가져와서 플래그먼트에서 데이터를 주어도 상관 없을까?
 * 아니면 플래그먼트에서 서버에서 데이터를 가져와서 사용하는게 좋을까?
 **/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, TabLayout.OnTabSelectedListener{

    private DrawerLayout drawerLayout;
    private DoNotSwipeViewPager viewPager;
    private TabLayout tabLayout;
    public EditText editText;

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

        FragmentViewPagerAdapter mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new MyChallengeListFragment());
        mainViewPagerAdapter.addFragment(new TrendingFragment());
        mainViewPagerAdapter.addFragment(new QnaFragment());
        mainViewPagerAdapter.addFragment(new ItemFragment());
        mainViewPagerAdapter.addFragment(new ShopFragment());
        mainViewPagerAdapter.addFragment(new SearchFragment());

        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.removeTabAt(5);
        tabLayout.getTabAt(0).setText("내 도전");
        tabLayout.getTabAt(1).setText("트렌딩");
        tabLayout.getTabAt(2).setText("Q&A");
        tabLayout.getTabAt(3).setText("아이템");
        tabLayout.getTabAt(4).setText("상점");

        navigationView.setNavigationItemSelectedListener(this);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 5) {
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
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_trending:
                lastPagerNum = 1;
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_question:
                lastPagerNum = 2;
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_skin:
                lastPagerNum = 3;
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_shop:
                lastPagerNum = 4;
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
                if (lastPagerNum != 5) {
                    lastPagerNum = viewPager.getCurrentItem();
                    tabLayout.setVisibility(View.GONE);
                    editText.setHint("편린&QnA 검색");
                    viewPager.setCurrentItem(5);
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
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
