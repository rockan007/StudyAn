package com.study.an.CriminalIntent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.an.BaseUnits.SingleFragmentActivity;
import com.study.an.all.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss EE MM-dd-yy", Locale.getDefault());


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.crimes);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
        setListAdapter(new CrimeAdapter(mCrimes));

    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);
        Toast.makeText(this.getActivity(), c.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), CrimeViewPagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(intent);
    }

    public class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.crime_list_item, null);
            }
            Crime c = getItem(position);
            TextView mTitle = (TextView) convertView.findViewById(R.id.crime_list_item_title);
            mTitle.setText(c.getTitle());
            TextView mDate = (TextView) convertView.findViewById(R.id.crime_list_item_date);
            mDate.setText(simpleDateFormat.format(c.getDate()));
            CheckBox mSolved = (CheckBox) convertView.findViewById(R.id.crime_list_item_solved);
            mSolved.setChecked(c.isSolved());
            return convertView;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_title_list, menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_item_delete_crime, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = new Intent(getActivity(), CrimeViewPagerActivity.class);
                intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
                startActivityForResult(intent, 0);
                return true;
            case R.id.menu_item_show_subtitle:
                if (((SingleFragmentActivity) getActivity()).getToolbar().getSubtitle() == null) {
                    ((SingleFragmentActivity) getActivity()).getToolbar().setSubtitle(R.string.subtitle);
                    item.setTitle(R.string.hide_subtitle);
                } else {
                    ((SingleFragmentActivity) getActivity()).getToolbar().setSubtitle(null);
                    item.setTitle(R.string.show_subtitle);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        CrimeAdapter adapter = (CrimeAdapter) getListAdapter();
        Crime crime = adapter.getItem(position);
        switch (item.getItemId()) {
            case R.id.delete_crime:
                CrimeLab.get(getActivity()).deleteCrime(crime);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getListView();
        registerForContextMenu(listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_item_delete_crime, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_crime:
                        CrimeAdapter crimeAdapter = (CrimeAdapter) getListAdapter();
                        CrimeLab crimeLab = CrimeLab.get(getActivity());
                        for (int i = crimeAdapter.getCount() - 1; i >= 0; i--) {
                            if (getListView().isItemChecked(i)) {
                                crimeLab.deleteCrime(crimeAdapter.getItem(i));
                                crimeAdapter.notifyDataSetChanged();
                            }
                        }

                        mode.finish();
                        ((SingleFragmentActivity) getActivity()).getToolbar().setVisibility(View.VISIBLE);
                        return true;
                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }
}
