package com.hanadal.dooson.hanadal.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.Search;
import com.hanadal.dooson.hanadal.ui.adapter.FragmentViewPagerAdapter;
import com.hanadal.dooson.hanadal.ui.main.MainActivity;
import com.hanadal.dooson.hanadal.ui.view.DoNotSwipeViewPager;
import com.hanadal.dooson.hanadal.util.UtilClass;

//ToDo("검색 입력시 페이지에 맞게 리스트 값 계속 가져오기")
public class SearchFragment extends Fragment implements TextView.OnEditorActionListener {

    private TabLayout tabLayout;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;
    private DoNotSwipeViewPager viewPager;
    private SearchResultFragment challengeResultFragment;
    private SearchResultFragment qnaResultFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        viewPager = view.getRootView().findViewById(R.id.search_view_pager);
        tabLayout = view.getRootView().findViewById(R.id.search_tab);

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager());

        challengeResultFragment = new SearchResultFragment();
        qnaResultFragment = new SearchResultFragment();

        fragmentViewPagerAdapter.addFragment(challengeResultFragment);
        fragmentViewPagerAdapter.addFragment(qnaResultFragment);

        viewPager.setAdapter(fragmentViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("도전");
        tabLayout.getTabAt(1).setText("질문");

        ((MainActivity)getActivity()).editText.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if(v.length() > 0) {
                UtilClass.loadProgress(getActivity());
                Connector.api.searching(v.getText().toString()).enqueue(new Res<Search>(getContext()) {
                    @Override
                    public void callback(int code, Search body) {
                        if (code == 200) {
                            UtilClass.Toast(getContext(),
                                    "도전 " + body.challenges.size() + "개,\n" +
                                            "질문 " + body.qnas.size() + "개를 찾았습니다.");
                            challengeResultFragment.putResult(body.challenges);
                            qnaResultFragment.putResult(body.qnas);
                        }
                    }
                });
                return true;
            }
        }
        return false;
    }
}
