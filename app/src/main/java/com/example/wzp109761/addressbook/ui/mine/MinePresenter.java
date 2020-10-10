package com.example.wzp109761.addressbook.ui.mine;
import com.example.wzp109761.addressbook.ui.core.model.impl.CommonModel;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;

public class MinePresenter extends BasePresenter<MineContract.IMineView> implements MineContract.IMinePresenter  {
    private MineContract.IMineView iMineViewView;
    private CommonModel iMineModel;
    public MinePresenter() {
        this.iMineModel = new CommonModel();
    }

    @Override
    public void getTestData() {
        iMineViewView=getView();
    }
}
