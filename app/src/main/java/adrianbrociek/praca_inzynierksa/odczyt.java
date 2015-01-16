package adrianbrociek.praca_inzynierksa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class odczyt extends Activity {
    public class FeedData {
        public FeedData( String data1, String data2, String data3) {
            last_data1 = data1;
            last_data2 = data2;
            last_data3 = data3;
        }
        public String last_data1;
        public String last_data2;
        public String last_data3;
    }

    TextView responseView;
    Button update;
    EditText channel;
    ProgressDialog progress;

    final Handler mHandler = new Handler();
    FeedData mFD = new FeedData("0","0","0");
    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            updateResults();
        }
    };
    final Runnable mStartProgressBar = new Runnable() {
        public void run() {
            startProgressBar();
        }
    };
    final Runnable mStopProgressBar = new Runnable() {
        public void run() {
            stopProgressBar();
        }
    };

    private Button button1, button2,button3,button4, button5;

    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);



        setContentView(R.layout.odczyt);



        button1 = (Button) findViewById(R.id.button_odczyt);
        button2 = (Button) findViewById(R.id.button_wykresy);
        button3 = (Button) findViewById(R.id.button_sterowanie);
        button4 = (Button) findViewById(R.id.button_ustawienia);
        button5 = (Button) findViewById(R.id.button_wyjscie);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, odczyt.class);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, wykresy.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, sterowanie.class);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ustawienia.class);
                startActivity(intent);
                finish();
            }
        });

        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new AlertDialog.Builder(odczyt.this)
                        .setTitle( "Koniec" )
                        .setMessage( "Czy chcesz wyjść z aplikacji?" )
                        .setNegativeButton( "Nie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("AlertDialog", "Negative");
                            }
                        } )
                        .setPositiveButton( "Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Positive" );
                                finish();
                            }
                        })
                        .show();
            }
        });

        responseView = (TextView)findViewById(R.id.response);
        channel = (EditText)findViewById(R.id.channel);
        update = (Button)findViewById(R.id.update);
        progress = new ProgressDialog(this);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread() {
                    public void run() {
                        mHandler.post(mStartProgressBar);
                        mFD = sendRequest();
                        mHandler.post(mUpdateResults);
                        mHandler.post(mStopProgressBar);
                    }
                };
                t.start();
            }
        });

    }
    protected void updateResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("Temperatura: " + mFD.last_data1+ "*C\n");
        sb.append("Wilgotność: " + mFD.last_data2+ "%\n");
        sb.append("Jasność: " + mFD.last_data3+ "%\n");
        responseView.setText(sb);

    }

    private void startProgressBar() {
        progress = ProgressDialog.show(odczyt.this, "", "Pobieranie danych...", true);
    }
    private void stopProgressBar() {
        progress.cancel();
    }

    private odczyt.FeedData sendRequest() {
        FeedData fd = new FeedData("0","0","0");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://thingspeak.com/channels/"+channel.getText()+"/feeds/last.json");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            JSONObject jo = new JSONObject(br.readLine());
            String attr1 = jo.getString("field1");
            String attr2 = jo.getString("field2");
            String attr3 = jo.getString("field3");
            fd.last_data1 = attr1;
            fd.last_data2 = attr2;
            fd.last_data3 = attr3;
            return fd;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return fd;
        } catch (IOException e) {
            e.printStackTrace();
            return fd;
        } catch (JSONException e) {
            e.printStackTrace();
            return fd;
        }

    }
}