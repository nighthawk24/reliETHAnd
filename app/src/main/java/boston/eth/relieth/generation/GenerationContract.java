package boston.eth.relieth.generation;

import boston.eth.relieth.BasePresenter;
import boston.eth.relieth.BaseView;

public interface GenerationContract {

    interface View extends BaseView<Presenter> {

        void showGeneratedWallet(String walletAddress);
    }

    interface Presenter extends BasePresenter {

        void generateWallet(String password);
    }
}
