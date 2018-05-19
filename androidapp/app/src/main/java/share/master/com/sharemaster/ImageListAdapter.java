package share.master.com.sharemaster;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageListAdapter extends ArrayAdapter<String> {


    //region VARIABLES
    private final Activity context;
    private final String[] menuItems;
    private final Integer[] imageId;
    //endregion

    /***
     * Constructor for this class.
     *
     * @param context  getApplicationContext()
     * @param menuItems text for menu items (string array)
     * @param imageId ids for images in menu items (integer array)
     */
    public ImageListAdapter(Activity context, String[] menuItems, Integer[] imageId)
    {
        super(context, R.layout.list_item_with_image, menuItems);
        this.context = context;
        this.menuItems = menuItems;
        this.imageId = imageId;

    }

    @Override
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item_with_image, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        txtTitle.setTextColor(Color.parseColor("#000000"));
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(menuItems[position]);


        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

