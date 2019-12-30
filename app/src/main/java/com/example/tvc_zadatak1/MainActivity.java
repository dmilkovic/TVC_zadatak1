package com.example.tvc_zadatak1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.tvc_zadatak1.UpdateLocale.getLocale;
import static com.example.tvc_zadatak1.UpdateLocale.isLocaleSet;
import static com.example.tvc_zadatak1.UpdateLocale.loadLocale;
import static com.example.tvc_zadatak1.UpdateLocale.changeLang;

public class MainActivity extends AppCompatActivity{
    String currentLanguage;
    private PopupMenu languageMenu;
    private Button btn, messageBtn, textBtn;
    private View customLayout;
    private AlertDialog builder;
    private EditText inputText;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale(MainActivity.this);
        currentLanguage = getLocale(MainActivity.this);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.language_button);
        messageBtn = (Button)findViewById(R.id.message_button);
        textBtn = (Button)findViewById(R.id.text_button);

        if(!isLocaleSet(getApplicationContext()))
        {
            btn.setText("-");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create popup menu
                languageMenu = new PopupMenu(MainActivity.this, btn);
                //Inflating the Popup using xml file
                languageMenu.getMenuInflater().inflate(R.menu.popup_menu, languageMenu.getMenu());

                //registering popup with OnMenuItemClickListener
                languageMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);;
                        switch (id)
                        {
                            case R.id.croatian:
                                //check if the selected language equals current language
                                if(!getLocale(getApplicationContext()).equals("cro"))
                                {
                                    //change language and restart activity
                                    changeLang("cro", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }
                                break;
                            case R.id.english:
                                if(!getLocale(getApplicationContext()).equals("en"))
                                {
                                    //change language and restart activity
                                    changeLang("en", MainActivity.this);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }
                                break;
                            default: break;
                        }
                        return true;
                    }
                });
                //showing popup menu
                languageMenu.show();
            }
        });

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if language isn't set show Toast
                //else create and show an alertDialog
                if(!isLocaleSet(getApplicationContext()))
                {

                    Toast.makeText(MainActivity.this, R.string.no_language, Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(R.string.hello_world);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set the custom layout
                customLayout = getLayoutInflater().inflate(R.layout.edit_text_dialog, null);
                builder = new AlertDialog.Builder(MainActivity.this).setPositiveButton(R.string.validate, null).setView(customLayout).show();
                inputText = customLayout.findViewById(R.id.editText);
                errorText = customLayout.findViewById(R.id.invalidText);
                Button positiveButton = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ValidateText.validateText(inputText.getText().toString(), getApplicationContext())) {
                            builder.cancel();
                        }else{
                            errorText.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

    }
}
