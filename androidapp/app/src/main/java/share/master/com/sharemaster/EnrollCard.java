package share.master.com.sharemaster;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcA;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;

public class EnrollCard extends AppCompatActivity {


    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private AlertDialog mDialog;
    private Button btnMainMenu;

    private static final String TAG = "Verification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_card);


        ActionBar mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.custom_action_bar_layout_with_name, null);
            mActionBar.setCustomView(mCustomView);
            mActionBar.setDisplayShowCustomEnabled(true);

            TextView tv = mCustomView.findViewById(R.id.tvShareMaster);
            tv.setGravity(Gravity.CENTER);
            //Here do whatever you need to do with the view (set text if it's a textview or whatever)
//            ImageView iv = mCustomView.findViewById(R.id.ivLogo);
//            iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_share_master_square));
//            iv.setVisibility(View.GONE);

        }

        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(EnrollCard.this, MainMenu.class);
                mainMenu.putExtra("STATUS", 1);
                startActivity(mainMenu);
                finish();
            }
        });



        resolveIntent(getIntent());

        mDialog = new AlertDialog.Builder(this).setNeutralButton("Ok", null).create();

        //da li telefon podrzava NFC
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null)
        {
           // showMessage("GRESKA","NEMA NFC");
            finish();
            return;
        }

        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


    }


    private void showMessage(int title, int message)
    {
        mDialog.setTitle(title);
        mDialog.setMessage(getText(message));
        mDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mAdapter != null) {
            if (!mAdapter.isEnabled())
            {
                Log.d("NFC", "NFC is disabled. Settings on." );
                showWirelessSettingsDialog();
            }
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mAdapter != null)
        {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    /**Ukoliko je NFC iskljucen pokrecu se Podecavanja za NFC i od korisnika se zahteva da ga ukljuci.
     *
     */
    private void showWirelessSettingsDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("NFC");
        builder.setMessage(R.string.nfc_disabled);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
            }
        });
        builder.create().show();
    }

    /**NFC je otkrio karticu
     *
     * @param intent
     */
    private void resolveIntent(Intent intent)
    {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action))
        {
            Log.d(TAG, "Card has been discovered." );
          //  Toast.makeText(this, "Card has been discovered", Toast.LENGTH_LONG).show();

            Intent authorise = new Intent(EnrollCard.this, AuthoriseYourCard.class);
            authorise.putExtra("AUTORISE", 1);
            startActivity(authorise);
            finish();
//
//            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//            NdefMessage[] msgs;
//            if (rawMsgs != null)
//            {
//                msgs = new NdefMessage[rawMsgs.length];
//                for (int i = 0; i < rawMsgs.length; i++)
//                {
//                    msgs[i] = (NdefMessage) rawMsgs[i];
//                }
//            }
//            else
//            {
//                Parcelable tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

//                //Citaju se podaci sa kartice i postavljaju se u tekstualno po
//                //               // dumpTagData(tag);
//            }
        }
    }

    /**Citanje podataka sa taga tj. kartice.
     *
     * @param p
     */
    private void dumpTagData(Parcelable p)
    {
        Tag tag = (Tag) p;
        Log.d(TAG, "Reading data from a card." );
        boolean auth = false;

        //Cita se ID taga
        byte[] id = tag.getId();

        int typeOfCard = 0;
        for (String tech : tag.getTechList())
        {
            if(tech.equals(NfcA.class.getName()))
            {
                NfcA nfcATag = NfcA.get(tag);
                byte[] data = null;

                try
                { // Konektuje se sa karticom
                   nfcATag.connect();


                }
                catch (IOException e)
                {
                    Log.e(TAG, e.getLocalizedMessage());
                    Toast.makeText(this, "Verifikacija neuspešna.\nPokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
                finally
                {
                }

            }
        }
    }



    /**Kad se cita nov tag tj kartica poziva se metoda koja otkriva karticu.
     *
     */
    @Override
    public void onNewIntent(Intent intent)
    {
        setIntent(intent);
        resolveIntent(intent);
    }
}
