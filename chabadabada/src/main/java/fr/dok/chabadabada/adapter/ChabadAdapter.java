package fr.dok.chabadabada.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.dok.chabadabada.fragments.ChabadaFragment;

/**
 * Created by Doky on 03/09/2014.
 */
public class ChabadAdapter extends FragmentStatePagerAdapter {

    private List<ChabadaFragment> deck;

    public ChabadAdapter(FragmentManager fm) {
        super(fm);
        deck = new ArrayList<ChabadaFragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return deck.get(position);
    }

    @Override
    public int getCount() {
        return deck.size();
    }

    public void addItem(ChabadaFragment fragment,int position) {
        deck.add(position,fragment);
        notifyDataSetChanged();
    }

    public void addItem(ChabadaFragment fragment) {
        deck.add(fragment);
        notifyDataSetChanged();
    }

    public void remove(int position){
        deck.remove(position);
        notifyDataSetChanged();
    }

    public void remove(){
        deck.remove(0);
        notifyDataSetChanged();
    }
}
