package com.hanadal.dooson.hanadal.ui.my_challenge_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

public class MyChallengeListFragment extends Fragment {

    TabLayout tabLayout;
    FragmentViewPagerAdapter fragmentViewPagerAdapter;
    DoNotSwipeViewPager viewPager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.getRootView().findViewById(R.id.my_challenge_list_view_pager);
        tabLayout = view.getRootView().findViewById(R.id.my_challenge_list_tab);
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        fragmentViewPagerAdapter.addFragment(new ChallengeListFragment());
        fragmentViewPagerAdapter.addFragment(new BookListFragment());

        viewPager.setAdapter(fragmentViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("진행중인 도전");
        tabLayout.getTabAt(1).setText("만든 책");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_challenge, container, false);
    }
}
