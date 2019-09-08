package boston.eth.relieth.wallet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import boston.eth.relieth.R;

public class WalletActivity extends AppCompatActivity {

    private TextView mWalletAddress;

    private TextView mBalance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        Web3j web3j = Web3j.build(new HttpService());

        mWalletAddress = findViewById(R.id.account_address);
        mBalance = findViewById(R.id.wallet_balance);

        Bundle extras = getIntent().getExtras();
        mWalletAddress.setText(extras.getString("WalletAddress"));
    }
}
