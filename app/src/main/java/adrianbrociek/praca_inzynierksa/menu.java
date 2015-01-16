package adrianbrociek.praca_inzynierksa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.DialogInterface;
import android.util.Log;

public class menu extends Activity {

    private Button button1, button2,button3,button4, button5;

    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Witaj w aplikacji Inteligentne Terrarium!");
        builder.setMessage("Dane zostały wczytane pomyślnie. W celu przejścia do MENU wciśnij START.")
                .setCancelable(false)
                .setPositiveButton("START", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        setContentView(R.layout.menu);

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
                new AlertDialog.Builder(menu.this)
                        .setTitle( "Koniec" )
                        .setMessage( "Czy chcesz wyjść z aplikacji?" )
                        .setNegativeButton( "Nie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Negative" );
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