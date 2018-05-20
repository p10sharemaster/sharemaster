package share.master.com.sharemaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class UnsuccessfulPayment extends AppCompatActivity {

    Button btnFirstChoice, btnSecondChoice, btnCancel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsuccessful_payment);


        ActionBar mActionBar = getSupportActionBar();

        context = getApplicationContext();

        if (mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.custom_action_bar_layout, null);
            mActionBar.setCustomView(mCustomView);
            mActionBar.setDisplayShowCustomEnabled(true);

//            ImageView iv = mCustomView.findViewById(R.id.ivLogo);
//            iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher));

            ImageButton btnBack = mCustomView.findViewById(R.id.btnBack);
            btnBack.setVisibility(View.INVISIBLE);

        }

        btnFirstChoice = findViewById(R.id.btnFirstChoice);
        btnSecondChoice = findViewById(R.id.btnSecondChoice);
        btnCancel= findViewById(R.id.btnCancel);

        btnFirstChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialog(2);
            }
        });

        btnSecondChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialog(3);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent successful = new Intent(UnsuccessfulPayment.this, MainMenu.class);
                successful.putExtra("STATUS",1);
                startActivity(successful);
            }
        });



    }

    private void openAlertDialog(final int i)
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UnsuccessfulPayment.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_credit_approved, null);

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();

        ImageButton ibSuccess = dialogView.findViewById(R.id.ibSuccess);
        ibSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
                Intent successful = new Intent(UnsuccessfulPayment.this, MainMenu.class);
                successful.putExtra("STATUS", i);
                startActivity(successful);
                finish();
            }
        });

        dialogBuilder.setCancelable(true);

        alertDialog.show();
    }

}
