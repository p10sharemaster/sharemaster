package share.master.com.sharemaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AuthoriseYourCard extends AppCompatActivity {

    Button btnConfirmPIN;
    EditText etPIN;
    Context context;
    ImageButton ibFinger;

    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise_your_card);


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

        context = getApplicationContext();
        etPIN = findViewById(R.id.etPIN);
        btnConfirmPIN = findViewById(R.id.btnConfirmPIN);
        btnConfirmPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPIN.getText().length() >0)
                {
                    openAlertDialog();
                }
                else
                {
                    Toast.makeText(context, "Enter PIN number or use a fingerprint.", Toast.LENGTH_LONG).show();
                }
            }
        });

        ibFinger = findViewById(R.id.ibFinger);
        ibFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialog();
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            status = intent.getIntExtra("AUTHORISE", 1);

        }
    }


    private void openAlertDialog()
    {
        if(status == 1)
        {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AuthoriseYourCard.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_authorisation_successfull, null);

            dialogBuilder.setView(dialogView);

            final AlertDialog alertDialog = dialogBuilder.create();

            ImageButton ibSuccess = dialogView.findViewById(R.id.ibSuccess);
            ibSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.cancel();
                    if(status == 1)
                    {
                        Intent mainMenu = new Intent(AuthoriseYourCard.this, MainMenu.class);
                        mainMenu.putExtra("STATUS", 1);
                        startActivity(mainMenu);
                        finish();
                    }
                    else
                    {
                       Intent processing = new Intent(AuthoriseYourCard.this, Processing.class);
                       startActivity(processing);
                       finish();

                        //status = 2;
                    }


                }
            });

            dialogBuilder.setCancelable(true);

            alertDialog.show();

//            final Timer t = new Timer();
//            t.schedule(new TimerTask() {
//                public void run() {
//                    if(alertDialog.isShowing())
//                    {
//                        if(status == 1)
//                        {
//                            Intent mainMenu = new Intent(AuthoriseYourCard.this, MainMenu.class);
//                            mainMenu.putExtra("STATUS", 1);
//                            startActivity(mainMenu);
//                            finish();
//                        }
//                        else
//                        {
//                            Intent unsuccessful = new Intent(AuthoriseYourCard.this, UnsuccessfulPayment.class);
//                            startActivity(unsuccessful);
//                            finish();
//
//                            //status = 2;
//                        }
//                    }
//                    alertDialog.dismiss(); // when the task active then close the dialog
//
//
//                    t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
//                }
//            }, 2000);

        }
        else
        {
            Intent processing = new Intent(AuthoriseYourCard.this, Processing.class);
            startActivity(processing);
            finish();

//            Intent unsuccessful = new Intent(AuthoriseYourCard.this, UnsuccessfulPayment.class);
//            startActivity(unsuccessful);
//            finish();
        }

    }

}
