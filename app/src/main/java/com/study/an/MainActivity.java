package com.study.an;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.study.an.CriminalIntent.CrimeListActivity;
import com.study.an.Draw.DragAndDrawActivity;
import com.study.an.MediaPlayer.HelloMoonActivity;
import com.study.an.RecyclerView.RecyclerActivity;
import com.study.an.RecyclerView.StaggeredGridLayoutActivity;
import com.study.an.TrueFalse.TrueFalseActivity;
import com.study.an.Volley.SimpleHttpRequestActivity;
import com.study.an.all.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 2016/1/20.
 */
public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private int[] mImage = {R.mipmap.bar_back, R.mipmap.bar_creatgroup, R.mipmap.bar_menu, R.mipmap.bar_menu_changejiaobao,
            R.mipmap.bar_menu_changeunit, R.mipmap.bar_menu_chat, R.mipmap.bar_menu_dailywork, R.mipmap.bar_menu_dt, R.mipmap.bar_menu_exit,R.mipmap.bar_menu_exit,R.drawable.ic_favorite_red_400_18dp,R.drawable.ic_pets_cyan_a200_18dp};
    private int[] mName = {R.string.true_false, R.string.expandable, R.string.crime_title, R.string.media_player, R.string.web_list, R.string.picture, R.string.doit, R.string.location,R.string.volley,R.string.draw,R.string.recycler,R.string.staggered};
    private GridView mGridView;
    private String[] from = {"image", "name"};
    private Toolbar mToolbar;
    private int[] to = {R.id.gridView_image, R.id.gridView_name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        mGridView = (GridView) findViewById(R.id.grid_view);
        ArrayList<HashMap<String, Object>> gridListItem = new ArrayList<>();
//        ArrayList<String> list=new ArrayList();
        for (int i = 0; i < mName.length; i++) {
            HashMap<String, Object> gridItem = new HashMap<>();
//            list.add(mName[i]);
            gridItem.put("image", mImage[i]);
            gridItem.put("name", getResources().getString(mName[i]));
            gridListItem.add(gridItem);
        }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, gridListItem, R.layout.gridview_item, from, to);
        mGridView.setAdapter(simpleAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, TrueFalseActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, com.study.an.Expandable.MainActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, CrimeListActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, HelloMoonActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, com.study.an.WebList.MainActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, com.study.an.ceshi.picture.class);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(MainActivity.this, com.study.an.ceshi.picture.class);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(MainActivity.this, com.study.an.location.locationActivity.class);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8=new Intent(MainActivity.this, SimpleHttpRequestActivity.class);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9=new Intent(MainActivity.this, DragAndDrawActivity.class);
                        startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(MainActivity.this, RecyclerActivity.class);
                        startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(MainActivity.this, StaggeredGridLayoutActivity.class);
                        startActivity(intent11);
                        break;
                    default:
                        break;
                }
            }
        });
        mToolbar.setNavigationIcon(R.drawable.ic_favorite_red_400_18dp);
        mToolbar.setLogo(R.drawable.ic_pets_cyan_a200_18dp);
        mToolbar.setTitle("StudyAn");
        mToolbar.setSubtitle("2016.06.08");
    }
    //Toolbar增加按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    // menu的点击事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"人才是我",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
