package com.hanadal.dooson.hanadal.ui.my_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.ui.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.make_challenge.MakeChallengeActivity;
import com.hanadal.dooson.hanadal.ui.view.DoNotSwipeViewPager;

// ToDo("도전 만들기 기능 구현")
// ToDo("다이어리 작성 기능 구현")
public class MyChallengeFragment extends Fragment implements View.OnClickListener{

    TabLayout tabLayout;
    FragmentViewPagerAdapter fragmentViewPagerAdapter;
    DoNotSwipeViewPager viewPager;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_challenge, container, false);

        viewPager = view.getRootView().findViewById(R.id.my_challenge_list_view_pager);
        tabLayout = view.getRootView().findViewById(R.id.my_challenge_list_tab);
        floatingActionButton = view.getRootView().findViewById(R.id.my_challenge_floating);

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        fragmentViewPagerAdapter.addFragment(new ChallengeListFragment());
        fragmentViewPagerAdapter.addFragment(new BookListFragment());

        viewPager.setAdapter(fragmentViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("진행중인 도전");
        tabLayout.getTabAt(1).setText("만든 책");

        floatingActionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_challenge_floating:{
                startActivity(new Intent(getContext(), MakeChallengeActivity.class));
            }
        }
    }
}
