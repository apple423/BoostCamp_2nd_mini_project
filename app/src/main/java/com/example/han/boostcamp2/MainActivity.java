package com.example.han.boostcamp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int DISTANCE = 0;
    private static final int POPULARITY = 1;
    private static final int RECENT = 2;


    /*데이터 모음*/
    private String[] shopNames = {"형","고황24시","온달파전","마루기"};
    private int[] shopImageIDs = {R.drawable.brother,R.drawable.born,R.drawable.ondal,R.drawable.marugi};
    private String[] shopContents = {"초벌구이 삼겹살과 더불어 우렁이를 넣은 된장찌개가 일품인 고기집 형입니다.",
            "저렴한 가격과 푸짐한 양, 그리고 어디서도 맛 볼 수 없는 뼈찜이 있는 고황24시입니다.",
            "엄청난 크기와 부드러움을 자랑하는 파전과 함께 막걸리를 마실 수 있는 온달파전입니다.",
            "생활의 달인에 출연한 최고의 일본라멘을 먹을 수 있는 마루기입니다."};
    private int[] distanceArray = {0,2,1,3};
    private int[] popularityArray = {4,3,2,1};
    private int[] recentArray = {2,3,1,4};

    // 필요한 것들 선언
    @BindView(R.id.content_recycler) RecyclerView recyclerView;

    @BindView(R.id.layout_imageButton) ImageButton layoutImageButton;

    @BindView(R.id.tabLayout) TabLayout tabLayout;

    private ShopAdapter shopAdapter;

    private boolean isLinear = true;


    @OnClick(R.id.layout_imageButton) public void onClick(View v) {

        if(isLinear){
            isLinear =false;
            layoutImageButton.setImageResource(R.drawable.staggered_button);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            for(int i  = 0; i<shopAdapter.getItemCount(); i++)
                shopAdapter.notifyItemChanged(i);

        }
        else{
            isLinear = true;
            layoutImageButton.setImageResource(R.drawable.linear_button);
            recyclerView.setLayoutManager(linearLayoutManager);
            for(int i  = 0; i<shopAdapter.getItemCount(); i++)
                shopAdapter.notifyItemChanged(i);


        }

    }

    // 레이아웃 매니저들 생성 및 선언
    // 값이 바뀌지 않고 똑같은걸 계속 쓸테니 상수로 썼습니다.
    final LinearLayoutManager linearLayoutManager =
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    final StaggeredGridLayoutManager staggeredGridLayoutManager
            = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //네비게이션 드로워를 위한 작업들
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recyclerView = (RecyclerView)findViewById(R.id.content_recycler);

        /*final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        final StaggeredGridLayoutManager staggeredGridLayoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);*/


        recyclerView.setLayoutManager(linearLayoutManager);

        //recyclerView에 넣을 어뎁터 생성
        shopAdapter = new ShopAdapter();

        recyclerView.setAdapter(shopAdapter);



        // 가게 정보를 위한 ArrayList 초기화
        initList();


        //layoutImageButton = (ImageButton)findViewById(R.id.layout_imageButton);
/*        @OnClick(R.id.layout_imageButton)
        // 우측 상단 버튼을 눌렀을때 레이아웃이 바뀌는 이벤트
        Button.OnClickListener layoutButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLinear){
                    isLinear =false;
                    layoutImageButton.setImageResource(R.drawable.staggered_button);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                    for(int i  = 0; i<shopAdapter.getItemCount(); i++)
                    shopAdapter.notifyItemChanged(i);

                }
                else{
                    isLinear = true;
                    layoutImageButton.setImageResource(R.drawable.linear_button);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    for(int i  = 0; i<shopAdapter.getItemCount(); i++)
                        shopAdapter.notifyItemChanged(i);


                }


            }
        };*/

       // layoutImageButton.setOnClickListener(layoutButtonListener);

        //tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        // 각 탭을 눌렀을때 리스트 재배치

        TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                switch (position){
                    case DISTANCE :shopAdapter.sortShopListDistance();

                            break;

                    case POPULARITY: shopAdapter.sortShopListPopularity();
                            break;

                    case RECENT: shopAdapter.sortShopListRecent();
                            break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };

        tabLayout.addOnTabSelectedListener(onTabSelectedListener);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 가게 정보를 위한 ArrayList 초기화
    public void initList(){

        for(int i = 0 ; i<4; i++){
            Shop s = new Shop(shopNames[i],shopImageIDs[i],shopContents[i], false,
                    distanceArray[i],popularityArray[i],recentArray[i]);
            shopAdapter.addShop(s);

        }

    }
}
