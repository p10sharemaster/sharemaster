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

public class MainMenu extends AppCompatActivity {


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

            ImageView iv = mCustomView.findViewById(R.id.ivLogo);
            iv.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher));

            ImageButton btnBack = mCustomView.findViewById(R.id.btnBack);
            btnBack.setVisibility(View.GONE);


            expListView = (ExpandableListView) findViewById(R.id.lvExp);
            prepareListData();
            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, imageIdAdditionalBenefits, true);
            // setting list adapter
            expListView.setAdapter(listAdapter);

            setListenersForAdditionalBenefits();

        }
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

//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                switch (groupPosition) {
//
//                    case 0:
//                        Toast.makeText(context, "Money transfer", Toast.LENGTH_LONG).show();
//                        break;
//                    case 1:
//                        Toast.makeText(context, "Instant cash", Toast.LENGTH_LONG).show();
//                        break;
//                    case 2:
//
//                        break;
//                    case 3:
//                        Intent cupons = new Intent(MainMenu.this, Cupons.class);
//                        startActivity(cupons);
//                        break;
//                    default:
//                        break;
//                }
//
//                return false;
//            }
//        });

        //region Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                switch (groupPosition) {

                    case 0:
                        Toast.makeText(context, "Money transfer", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Instant cash", Toast.LENGTH_LONG).show();
                        break;
                    case 2:

                        break;
                    case 3:
                        Intent cupons = new Intent(MainMenu.this, Cupons.class);
                        startActivity(cupons);
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

}
