package shakar.ashrine;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        final EditText et = (EditText) findViewById(R.id.mainText);
        final Button encrypt = (Button) findViewById(R.id.encryptbutton);
        Button decrypt = (Button) findViewById(R.id.decrpyttbutton);
        final TextView anstext = (TextView) findViewById(R.id.answertext);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {

                }
                try {
                    anstext.setText((MainActivity.this.encrypt(et.getText().toString(), true)), 0, et.getText().length());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {

                }
                try {
                    String sentence=et.getText().toString();
                    sentence = sentence.replaceAll("[!?,]", "");
                    anstext.setText((MainActivity.this.encrypt(sentence, false)), 0, sentence.length());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                String smsBody = anstext.getText().toString();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", smsBody);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    //    client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    
        public char[] encrypt(String sentence, boolean encrypt) throws UnsupportedEncodingException {
        int key;

        String[] words = sentence.split("\\s+");
        ArrayList<ArrayList<Character>> newwords = new ArrayList<>();
        for(String word : words) {
            ArrayList<Character> letters = new ArrayList<>();
            for(int j = 0; j < word.length(); j = j + 1) {
                int letter = word.charAt(j);
                if((word.length() % 2) == 0) {
                    key = word.length() / 2;
                } else {
                    key = (word.length() + 1) / 2;
                }
                if(encrypt) {
                    if(letter > 64) {
                        letter += key;
                    }
                } else if(letter > 64) {
                    letter -= key;
                }
                letters.add((char) letter);
            }
            newwords.add(letters);

        }
            String s = "";
            int length_sen=newwords.size();
            for (int j=0;j<length_sen;j++) {
                for (Character i : newwords.get(j))
                    s += i;
                s +=' ';
            }
            return s.toCharArray();



        }
     
        

    



    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://shakar.ashrine/http/host/path")
        );
       // AppIndex.AppIndexApi.end(client, viewAction);
        //client.disconnect();
    }
}
