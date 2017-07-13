package com.example.han.boostcamp2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Han on 2017-07-12.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder>{

    // 가게를 담을 ArrayList, sorting을 위해
     private List<Shop> shopList = new ArrayList<>();

    // Sorting을 위한 인터페이스들 선언
    private Comparator distanceComparator, popularityComparator, recentComparator;

    // 생성자 안에서 인터페이스의 메소드들을 구현하고 생성합니다.
    public ShopAdapter(){

        /* Compares its two arguments for order.  Returns a negative integer,
         zero, or a positive integer as the first argument is less than, equal
        to, or greater than the second */
        distanceComparator = new Comparator<Shop>() {
            @Override
            public int compare(Shop s1, Shop s2) {

                if(s1.getDistance()>s2.getDistance()){
                    return 1;
                }

                else if(s1.getDistance()<s2.getDistance()){
                    return -1;
                }

                else return 0;

            }
        };

        popularityComparator = new Comparator<Shop>() {
            @Override
            public int compare(Shop s1, Shop s2) {

                if(s1.getPopularity()>s2.getPopularity()){
                    return 1;
                }

                else if(s1.getPopularity()<s2.getPopularity()){
                    return -1;
                }

                else return 0;

            }
        };

        recentComparator = new Comparator<Shop>() {
            @Override
            public int compare(Shop s1, Shop s2) {
                if(s1.getRecent()>s2.getRecent()){
                    return 1;
                }

                else if(s1.getRecent()<s2.getRecent()){
                    return -1;
                }

                else return 0;

            }


        };
    }


    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recyclerview,parent,false);

        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {

        Shop shop = shopList.get(position);
        String name = shop.getShopName();
        int imageID = shop.getShopImageID();
        String article = shop.getShopArticle();


        holder.shopNameTextView.setText(name);
        holder.shopImageView.setImageResource(imageID);
        holder.showArticleTextView.setText(article);

        // 상태에 따라 다르게 그려줍니다.
        if(shop.isChecked()){
            holder.chkboxButton.setImageResource(R.drawable.checked);
        }
        else{
            holder.chkboxButton.setImageResource(R.drawable.unchecked);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    // 뷰홀더에서 리스너를 implements하여 체크박스 버튼을 눌렀을때 가게 정보를 바꾸어 줍니다.
    public class ShopViewHolder extends RecyclerView.ViewHolder implements Button.OnClickListener{

        TextView shopNameTextView;
        ImageView shopImageView;
        TextView showArticleTextView;
        ImageButton chkboxButton;



        public ShopViewHolder(View itemView) {
            super(itemView);
            shopNameTextView = (TextView)itemView.findViewById(R.id.shop_name_textview);
            shopImageView = (ImageView)itemView.findViewById(R.id.shop_imageview);
            showArticleTextView = (TextView)itemView.findViewById(R.id.shop_article_textview);
            chkboxButton = (ImageButton)itemView.findViewById(R.id.shop_check_button);
            chkboxButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Shop currentShop = shopList.get(getAdapterPosition());
            if(currentShop.isChecked()){

                currentShop.setChecked(false);
                // 이미지를 반영시켜주기 위함
                notifyDataSetChanged();
            }
            else{
                currentShop.setChecked(true);
                notifyDataSetChanged();

            }

        }
    }


    public void addShop(Shop shop){
        shopList.add(shop);

    }

    public void sortShopListDistance(){

        Collections.sort(this.shopList,distanceComparator);
        // coordinatorlayout으로 인해 자동으로 애니메이션 효과를 얻기위함
        for (int i =0; i<shopList.size(); i++){

            notifyItemChanged(i);
        }

    }

    public void sortShopListPopularity(){

        Collections.sort(this.shopList,popularityComparator);
        for (int i =0; i<shopList.size(); i++){

            notifyItemChanged(i);
        }

    }

    public void sortShopListRecent(){

        Collections.sort(this.shopList,recentComparator);
        for (int i =0; i<shopList.size(); i++){

            notifyItemChanged(i);
        }

    }


}
