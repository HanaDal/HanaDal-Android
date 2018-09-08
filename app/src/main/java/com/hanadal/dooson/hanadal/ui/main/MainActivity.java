package com.hanadal.dooson.hanadal.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.app_info.AppInfoActivity;
import com.hanadal.dooson.hanadal.ui.change_skin.ChangeSkinActivity;
import com.hanadal.dooson.hanadal.ui.qna.QnaActivity;
import com.hanadal.dooson.hanadal.ui.store.StoreActivity;
import com.hanadal.dooson.hanadal.ui.trending.TrendingActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
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

        switch (item.getItemId()){
            case R.id.nav_hart:
                startActivity(new Intent(getApplicationContext(), TrendingActivity.class));
                break;
            case R.id.nav_my_challenge:
                startActivity(new Intent(getApplicationContext(), TrendingActivity.class));
                break;
            case R.id.nav_question:
                startActivity(new Intent(getApplicationContext(), QnaActivity.class));
                break;
            case R.id.nav_skin:
                startActivity(new Intent(getApplicationContext(), ChangeSkinActivity.class));
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
        switch (v.getId()){
            case R.id.nav_btn :
                if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.shop_btn : startActivity(new Intent(getApplicationContext(), StoreActivity.class));
        }
    }
}
