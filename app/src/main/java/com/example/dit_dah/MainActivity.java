package com.example.dit_dah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView data;
    private TextView res;
    private Button convert;
    private ImageButton swap;
    private boolean plain = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer dit_sound = MediaPlayer.create(this, R.raw.dit);

        data = findViewById(R.id.input_text);
        res = findViewById(R.id.output_text);
        convert = findViewById(R.id.convert_bt);
        swap = findViewById(R.id.swap_bt);


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dit_sound.start();
                hideKeyboard(v);
                String dataToConv = data.getText().toString();
                if (dataToConv.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Type something first!", Toast.LENGTH_SHORT).show();
                } else {
                    String convData = null;
                    if (plain) {
                        convData = MorseCode.plainToMorse(dataToConv);
                    } else {
                        convData = MorseCode.morseToPlain(dataToConv);
                    }
                    res.setText(convData);
                }
            }
            private void hideKeyboard(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
            }
        });

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dit_sound.start();
                boolean pl_chk = plain;
                if (pl_chk) {
                    TextView tv1 = findViewById(R.id.enterInput);
                    tv1.setText("Enter Morse code:");
                    TextView tv2 = findViewById(R.id.output);
                    tv2.setText("Plain text:");
                    plain = false;
                }
                else {
                    plain = true;
                    TextView tv1 = findViewById(R.id.enterInput);
                    tv1.setText("Enter plain text:");
                    TextView tv2 = findViewById(R.id.output);
                    tv2.setText("Morse code:");
                }
            }
        });

        final TextView output = findViewById(R.id.output_text);
        final EditText input = findViewById(R.id.input_text);
        Button copy = findViewById(R.id.copy_bt);
        Button clear = findViewById(R.id.clear_bt);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dit_sound.start();
                String value = output.getText().toString();
                String val = input.getText().toString();
                if (val.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nothing to copy!", Toast.LENGTH_SHORT).show();
                } else {
                    ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Data", value);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getApplicationContext(), "Copied!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dit_sound.start();
                String text = input.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Already empty!", Toast.LENGTH_SHORT).show();
                } else {
                    input.setText("");
                    output.setText("");
                }
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionAbout) {
            showInfo();
        }
        return true;
    }

    public void showInfo() {
        AboutDialog abt = new AboutDialog();
        abt.show(getSupportFragmentManager(), "about dialog");
    }
}