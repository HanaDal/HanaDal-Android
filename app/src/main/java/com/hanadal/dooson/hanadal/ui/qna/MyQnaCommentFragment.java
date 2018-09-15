package com.hanadal.dooson.hanadal.ui.qna;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.TestFragment;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

public class MyQnaCommentFragment extends Fragment {

    private static DoNotSwipeViewPager viewPager;
    FragmentViewPagerAdapter myQnaCommentViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_qna_comment_fragment, container, false);
        viewPager = view.getRootView().findViewById(R.id.my_qna_comment_pager);
        viewPager.setPagingEnabled(false);

        myQnaCommentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());
        myQnaCommentViewPagerAdapter.addFragment(new MyQnaListFragment());
        myQnaCommentViewPagerAdapter.addFragment(new MyQnaCommentListFragment());

        viewPager.setAdapter(myQnaCommentViewPagerAdapter);

        return view;
    }

    public static DoNotSwipeViewPager getMyQnaCommentViewPager() {
        return viewPager;
    }
}