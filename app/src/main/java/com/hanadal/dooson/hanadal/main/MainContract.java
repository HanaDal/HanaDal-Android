package com.hanadal.dooson.hanadal.main;

import com.hanadal.dooson.hanadal.BasePresenter;
import com.hanadal.dooson.hanadal.BaseView;

public interface MainContract extends BaseView<MainContract.Presenter>{

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter {

    }
}
