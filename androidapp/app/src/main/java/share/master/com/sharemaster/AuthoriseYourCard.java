package share.master.com.sharemaster;

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
import android.widget.TextView;
import android.widget.Toast;

public class AuthoriseYourCard extends AppCompatActivity {

    Button btnConfirmPIN;
    EditText etPIN;
    Context context;


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
                    Intent mainMenu = new Intent(AuthoriseYourCard.this, MainMenu.class);
                    mainMenu.putExtra("STATUS", 1);
                    startActivity(mainMenu);
                    finish();
                }
                else
                {
                    Toast.makeText(context, "Enter PIN number or use a fingerprint.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
