package share.master.com.sharemaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.QuickContactBadge;

public class Cupons extends AppCompatActivity {

    Context context;

    ListView list;
    String[] menuItems = {
            "50% Zara - Male t-shirts",
            "20% NIS gas station",
            "5% Gigatron - Dell Computers"

    } ;


    Integer[] imageId = {
            R.drawable.tshirt,
            R.drawable.gas,
            R.drawable.it

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupons);

        ActionBar mActionBar = getSupportActionBar();

        context = getApplicationContext();

        if (mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater mInflater = LayoutInflater.from(this);
            View mCustomView = mInflater.inflate(R.layout.custom_action_bar_layout, null);
            mActionBar.setCustomView(mCustomView);
            mActionBar.setDisplayShowCustomEnabled(true);

            ImageView iv = mCustomView.findViewById(R.id.ivLogo);
            iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher));

            ImageButton btnBack = mCustomView.findViewById(R.id.btnBack);
            btnBack.setVisibility(View.VISIBLE);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent goBack = new Intent(Cupons.this, MainMenu.class);
                    goBack.putExtra("STATUS", 1);
                    startActivity(goBack);
                    finish();
                }
            });

        }

        ImageListAdapter adapter = new
                ImageListAdapter(Cupons.this, menuItems, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        context = getApplicationContext();
    }
}
