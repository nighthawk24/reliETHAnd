package boston.eth.relieth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void connect() throws Exception {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        Web3j web3j = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/<your token>"));  // FIXME: Enter your Infura token here;
        String clientVersion = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        Log.i("Connected to ETH: ", clientVersion);

        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "/path/to/<walletfile>");
        Log.i(getClass().getSimpleName(), "Credentials loaded");

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
        Log.i(getClass().getSimpleName(), "Sending 1 Wei (" + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        Log.i(getClass().getSimpleName(), "Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
                + transferReceipt.getTransactionHash());

        // Now lets deploy a smart contract
        Log.i(getClass().getSimpleName(), "Deploying smart contract");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
//        Greeter contract = Greeter.deploy(
//                web3j,
//                credentials,
//                contractGasProvider,
//                "test"
//        ).send();
//
//        String contractAddress = contract.getContractAddress();
//        Log.i(getClass().getSimpleName(), "Smart contract deployed to address " + contractAddress);
//        Log.i(getClass().getSimpleName(), "View contract at https://rinkeby.etherscan.io/address/" + contractAddress);
//
//        Log.i("Value stored in remote smart contract: " + contract.greet().send());
//
//        // Lets modify the value in our smart contract
//        TransactionReceipt transactionReceipt = contract.newGreeting("Well hello again").send();
//
//        Log.i("New value stored in remote smart contract: " + contract.greet().send());
//
//        // Events enable us to log specific events happening during the execution of our smart
//        // contract to the blockchain. Index events cannot be logged in their entirety.
//        // For Strings and arrays, the hash of values is provided, not the original value.
//        // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events
//        for (Greeter.ModifiedEventResponse event : contract.getModifiedEvents(transactionReceipt)) {
//            Log.i("Modify event fired, previous value: " + event.oldGreeting
//                    + ", new value: " + event.newGreeting);
//            Log.i("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx)
//                    + ", new value: " + Numeric.toHexString(event.newGreetingIdx));
//        }
    }
}
