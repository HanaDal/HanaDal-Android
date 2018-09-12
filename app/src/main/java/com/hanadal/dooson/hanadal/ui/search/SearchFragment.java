package com.hanadal.dooson.hanadal.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.TestFragment;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

public class SearchFragment extends Fragment implements TextWatcher {

    TabLayout tabLayout;
    FragmentViewPagerAdapter fragmentViewPagerAdapter;
    DoNotSwipeViewPager viewPager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.getRootView().findViewById(R.id.search_view_pager);
        tabLayout = view.getRootView().findViewById(R.id.search_tab);
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        fragmentViewPagerAdapter.addFragment(new TestFragment());
        fragmentViewPagerAdapter.addFragment(new TestFragment());

        viewPager.setAdapter(fragmentViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("도전");
        tabLayout.getTabAt(1).setText("질문");

        ((MainActivity)getActivity()).editText.addTextChangedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
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
