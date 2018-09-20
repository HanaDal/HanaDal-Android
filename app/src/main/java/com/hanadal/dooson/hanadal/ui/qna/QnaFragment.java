package com.hanadal.dooson.hanadal.ui.qna;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.make_challenge.MakeChallengeActivity;
import com.hanadal.dooson.hanadal.ui.make_qna.MakeQnaActivity;
import com.hanadal.dooson.hanadal.view.DoNotSwipeViewPager;

// Todo("QnA 만들기 기능 구현")
public class QnaFragment extends Fragment
        implements View.OnClickListener, TabLayout.OnTabSelectedListener{

    TabLayout tabLayout;
    FragmentViewPagerAdapter fragmentViewPagerAdapter;
    DoNotSwipeViewPager viewPager;
    FloatingActionButton floatingActionButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qna, container, false);

        viewPager = view.getRootView().findViewById(R.id.qna_view_pager);
        tabLayout = view.getRootView().findViewById(R.id.qna_tab);
        floatingActionButton = view.getRootView().findViewById(R.id.qna_floating);

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        fragmentViewPagerAdapter.addFragment(new QnaListFragment());
        fragmentViewPagerAdapter.addFragment(new MyQnaCommentFragment());

        viewPager.setAdapter(fragmentViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("질문");
        tabLayout.getTabAt(1).setText("내 질문/답변");

        tabLayout.addOnTabSelectedListener(this);
        floatingActionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qna_floating:{
                if(tabLayout.getSelectedTabPosition() == 0){
                    startActivity(new Intent(getContext(), MakeQnaActivity.class));
                }else{
                    if(MyQnaCommentFragment.getMyQnaCommentViewPager().getCurrentItem() == 0){
                        MyQnaCommentFragment.getMyQnaCommentViewPager().setCurrentItem(1);
                        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.qna_comment, getContext().getTheme()));
                    }else{
                        MyQnaCommentFragment.getMyQnaCommentViewPager().setCurrentItem(0);
                        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.qna_question, getContext().getTheme()));
                    }
                }
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0){
            floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.add, getContext().getTheme()));
        }else{
            if(MyQnaCommentFragment.getMyQnaCommentViewPager().getCurrentItem() == 0){
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.qna_question, getContext().getTheme()));
            }else{
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.qna_comment, getContext().getTheme()));
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }
}
