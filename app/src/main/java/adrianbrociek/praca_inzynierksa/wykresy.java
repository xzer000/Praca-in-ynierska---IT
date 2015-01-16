package adrianbrociek.praca_inzynierksa;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class wykresy extends Activity {

    private WebView webViewa;
    private WebView webViewb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wykresy);


        webViewa = (WebView) findViewById(R.id.webView1);
        webViewa.getSettings().setJavaScriptEnabled(true);
        webViewa.loadUrl("http://api.thingspeak.com/channels/20412/charts/1?width=380&height=260&results=10&dynamic=true&type=spline&title=Pomiar%20temperatury");

        webViewb = (WebView) findViewById(R.id.webView2);
        webViewb.getSettings().setJavaScriptEnabled(true);
        webViewb.loadUrl("http://api.thingspeak.com/channels/20412/charts/2?width=380&height=260&results=10&dynamic=true&type=spline&title=Pomiar%20wilgotno%C5%9Bci");
    }

}