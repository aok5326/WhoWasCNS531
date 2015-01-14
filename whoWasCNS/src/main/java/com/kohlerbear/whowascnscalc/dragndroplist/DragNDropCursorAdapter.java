/*
 * Copyright 2012 Terlici Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kohlerbear.whowascnscalc.dragndroplist;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kohlerbear.whowascnscalc.R;
import com.kohlerbear.whowascnscalc.SwipeDismissListViewTouchListener;

public class DragNDropCursorAdapter extends SimpleCursorAdapter implements DragNDropAdapter {
	int mPosition[];
	int mHandler;
    LayoutInflater mLayoutInflater;
    SwipeDismissListViewTouchListener mDismisser;

	public DragNDropCursorAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to, int handler) {
		super(context, layout, cursor, from, to, 0);
		
		mHandler = handler;
        mLayoutInflater = LayoutInflater.from(context);
        setup();

	}

	@Override
	public Cursor swapCursor(Cursor c) {
		Cursor cursor = super.swapCursor(c);
		
		mPosition = null;
		setup();
		
		return cursor;
	}
	
	private void setup() {
		Cursor c = getCursor();
		
		if (c == null || !c.moveToFirst()) return;
		
		mPosition = new int[c.getCount()];
		
		for (int i = 0; i < mPosition.length; ++i) mPosition[i] = i;
	}

    public void setDismisser(SwipeDismissListViewTouchListener dismisser)
    {
        mDismisser = dismisser;
    }
	
	@Override
	public View getDropDownView(int position, View view, ViewGroup group) {
		return super.getDropDownView(mPosition[position], view, group);
	}
	
	@Override
	public Object getItem(int position) {
		return super.getItem(mPosition[position]);
	}
	
	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(mPosition[position]);
	}
	
	@Override
	public long getItemId(int position) {
		return super.getItemId(mPosition[position]);
	}

	/*
	In the newView() method, you simply inflate the view and return it. In the bindView() method, you set the elements of your view.
	 */
	@Override
	public View getView(int position, View view, ViewGroup group) {
		return super.getView(mPosition[position], view, group);
	}



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {

//        TextView rowView = (TextView) view.findViewById(R.id.);
        ImageView deleteButton = (ImageView) view.findViewById(R.id.deleteButton);
        deleteButton.setVisibility(View.VISIBLE);
        final Context myContext = context;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(myContext, "motherofgod.jpeg", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println();


    }
	
	@Override
	public boolean isEnabled(int position) {
		return super.isEnabled(mPosition[position]);
	}

	@Override
	public void onItemDrag(DragNDropListView parent, View view, int position, long id) {
		
	}

	@Override
	public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id) {
		int position = mPosition[startPosition];
		
		if (startPosition < endPosition)
			for(int i = startPosition; i < endPosition; ++i)
				mPosition[i] = mPosition[i + 1];
		else if (endPosition < startPosition)
			for(int i = startPosition; i > endPosition; --i)
				mPosition[i] = mPosition[i - 1];
		
		mPosition[endPosition] = position;

	}


	@Override
	public int getDragHandler() {
		return mHandler;
	}
	
}
