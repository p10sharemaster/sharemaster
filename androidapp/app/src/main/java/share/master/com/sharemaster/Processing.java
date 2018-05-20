package share.master.com.sharemaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Processing extends AppCompatActivity {

    ImageButton ibProcessing;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);


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

        }

        context = getApplicationContext();

        ibProcessing = findViewById(R.id.ibProcessing);
        ibProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unsuccessful = new Intent(Processing.this, UnsuccessfulPayment.class);
                startActivity(unsuccessful);
                finish();
            }
        });

    }

    //endregion
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            callParentPage();
        }

        return super.onKeyDown(keyCode, event);
    }
}
