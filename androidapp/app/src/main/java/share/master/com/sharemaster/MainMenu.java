package share.master.com.sharemaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenu extends AppCompatActivity {

    TextView tvAmount;
    ImageButton ibCard;


    Context context;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;


    List<String> listDataHeader;

    HashMap<String, List<String>> listDataChild;


    Integer[] imageIdAdditionalBenefits =
            {
                    R.drawable.money_transfer,
                    R.drawable.instant_cash,
                    R.drawable.services,
                    R.drawable.cupons
            };


    int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

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

        tvAmount = findViewById(R.id.tvAmount);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild, imageIdAdditionalBenefits, true);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        setListenersForAdditionalBenefits();


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
           status = intent.getIntExtra("STATUS", 1);

            switch (status)
           {
               case 1:
                   tvAmount.setText("1,000.00 EUR");
                   break;
               case 2:
                   tvAmount.setText("2,500.00 EUR");
                   break;
               case 3:
                   tvAmount.setText("3,500.00 EUR");
                   break;
               case 4:
                   tvAmount.setText("0 EUR");
                   break;
               default:
                   break;

           }
        }

        ibCard= findViewById(R.id.ibCard);
        ibCard.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.card_3));
        ibCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status)
                {
                    case 1:
                        Intent authorise = new Intent(MainMenu.this,AuthoriseYourCard.class);
                        authorise.putExtra("AUTHORISE", 2);
                        startActivity(authorise);
                        finish();
                        break;
                    case 2:
                        openAlertDialog();
                        break;
                    case 3:
                        openAlertDialog();
                        break;
                    case 4:
                        openAlertDialogNotEnoughMoney();
                    default:
                        break;

                }
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        List<String> emptyList = new ArrayList<>();


        listDataHeader.add("Money transfer");
        listDataHeader.add("Instant cash");
        listDataHeader.add("Services");
        listDataHeader.add("Cupons");

        List<String> header1List = new ArrayList<>();
        header1List.add("Taxi");
        header1List.add("Parking");

        listDataChild.put(listDataHeader.get(2), header1List); // Header, Child data
    }


    private void setListenersForAdditionalBenefits() {

        //region Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                switch (groupPosition) {

                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        Intent cupons = new Intent(MainMenu.this, Cupons.class);
                        startActivity(cupons);
                        finish();
                        break;
                    default:
                        break;
                }


            }
        });

        //endregion
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //region ON CHILD LISTENER
                switch (groupPosition) {
                    case 2:
                    {

                        switch (childPosition)
                        {
                            case 0: //First list item
                                Toast.makeText(context, "Taxi service", Toast.LENGTH_LONG).show();
                                break;
                            case 1: //Second list item
                                Toast.makeText(context, "Parking service", Toast.LENGTH_LONG).show();
                                break;

                        }


                        break;
                    }
                    default:
                        break;
                }
                return false;
                //endregion
            }
        });

    }

    private void openAlertDialog()
    {

            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainMenu.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_payment_successfull, null);

            dialogBuilder.setView(dialogView);

            final AlertDialog alertDialog = dialogBuilder.create();

            ImageButton ibSuccess = dialogView.findViewById(R.id.ibSuccess);
            ibSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.cancel();

                    if(status == 2) {
                        tvAmount.setText("0 EUR");
                        status = 4;
                    }
                    else {
                        tvAmount.setText("1,000.00 EUR");
                        status = 1;
                    }

                }
            });

            dialogBuilder.setCancelable(true);

            alertDialog.show();


//        final Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            public void run() {
//                if(alertDialog.isShowing())
//                {
//                    if(status == 2)
//                        tvAmount.setText("0 EUR");
//                    else
//                        tvAmount.setText("1,000.00 EUR");
//                }
//                alertDialog.dismiss(); // when the task active then close the dialog
//                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
//            }
//        }, 2000);

    }

    private void openAlertDialogNotEnoughMoney()
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainMenu.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_not_enough_money, null);

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();

        ImageButton ibSuccess = dialogView.findViewById(R.id.ibSuccess);
        ibSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });

        dialogBuilder.setCancelable(true);

        alertDialog.show();


//        final Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            public void run() {
//                alertDialog.dismiss(); // when the task active then close the dialog
//
//            }
//        }, 2000);
    }

}
