package adrianbrociek.praca_inzynierksa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class ustawienia extends Activity {

    private Button button1, button2,button3,button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ustawienia);

        button1 = (Button) findViewById(R.id.button_odczyt);
        button2 = (Button) findViewById(R.id.button_wykresy);
        button3 = (Button) findViewById(R.id.button_sterowanie);
        button4 = (Button) findViewById(R.id.button_ustawienia);
        button5 = (Button) findViewById(R.id.button_wyjscie);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, odczyt.class);
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, wykresy.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, sterowanie.class);
                startActivity(intent);
                finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ustawienia.class);
                startActivity(intent);
                finish();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new AlertDialog.Builder(ustawienia.this)
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
    }

}
