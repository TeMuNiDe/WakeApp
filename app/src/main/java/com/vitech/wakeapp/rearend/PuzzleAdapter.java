package com.vitech.wakeapp.rearend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vitech.wakeapp.R;

/**
 * Created by Home on 02-07-2016.
 */
public class PuzzleAdapter extends BaseAdapter {
    Context context;
    String[] puzzle;
    public PuzzleAdapter(String[] puzzle,Context context){
        this.puzzle = puzzle;
        this.context = context;
    }
    @Override
    public int getCount() {
        return puzzle.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.puzzle_item,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.puzzleItem);
        textView.setText(puzzle[position]);
        return convertView;
    }
}
