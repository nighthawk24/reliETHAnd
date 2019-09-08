package boston.eth.relieth.wallet;

import boston.eth.relieth.BasePresenter;
import boston.eth.relieth.BaseView;

public class WalletContract {

    interface View extends BaseView<Presenter> {

        void showBalance();

        void showWalletAddress();
    }

    interface Presenter extends BasePresenter {

        void getWalletBalance();

        void getWalletAddress();
    }
}
