package project01.csc214.project01;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 * Reference: https://developer.android.com/guide/topics/ui/layout/gridview.html
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public int[] savedMoves = new int[25];

    public ImageAdapter(Context c, int[] moves) {
        mContext = c;
        savedMoves = moves;
    }

    public int getCount() {
        return savedMoves.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        gridView = inflater.inflate(R.layout.gridview_item, null);

        ImageView gridImage = (ImageView) gridView.findViewById(R.id.grid_image);

        int event = savedMoves[position];
        if(event == 0)
            gridImage.setImageResource(R.drawable.grey_circle);
        if(event == 1)
            gridImage.setImageResource(R.drawable.red_circle);
        if(event == 2)
            gridImage.setImageResource(R.drawable.blue_circle);

        return gridImage;
    }
}

