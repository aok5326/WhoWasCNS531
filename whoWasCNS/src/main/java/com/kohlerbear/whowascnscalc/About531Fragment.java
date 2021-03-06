package com.kohlerbear.whowascnscalc;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

public class About531Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentActivity faActivity  = (FragmentActivity)    super.getActivity();
        // Replace LinearLayout by the type of the root element of the layout you're trying to load
        DrawerLayout drawerLayout = (DrawerLayout) inflater.inflate(R.layout.fragment_about531, container, false);
        // Of course you will want to faActivity and llLayout in the class and not this method to access them in the rest of
        // the class, just initialize them here
        // Content of previous onCreate() here
        // ...
        drawerLayout.setBackgroundColor(Color.BLACK);
        // Don't use this method, it's handled by inflater.inflate() above :
        // setContentView(R.layout.activity_layout);
        //Set colors for our Q and A
        TextView questionOne = (TextView) drawerLayout.findViewById(R.id.whatis531Q);
        TextView questionTwo = (TextView) drawerLayout.findViewById(R.id.whatisTM);
        TextView questionThree = (TextView) drawerLayout.findViewById(R.id.whatisCycle);
        TextView questionFour = (TextView) drawerLayout.findViewById(R.id.whatifFailedLift);
        questionOne.setTextColor(ColorManager.getInstance(getActivity()).getPrimaryColor());
        questionTwo.setTextColor(ColorManager.getInstance(getActivity()).getPrimaryColor());
        questionThree.setTextColor(ColorManager.getInstance(getActivity()).getPrimaryColor());
        questionFour.setTextColor(ColorManager.getInstance(getActivity()).getPrimaryColor());
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);


        // The FragmentActivity doesn't contain the layout directly so we must use our instance of     LinearLayout :
        // Instead of :
        // findViewById(R.id.someGuiElement);
        return drawerLayout; // We must return the loaded Layout
    }



}
