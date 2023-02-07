package com.hyunsungkr.simpleimgapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hyunsungkr.simpleimgapp.PhotoActivity;
import com.hyunsungkr.simpleimgapp.R;
import com.hyunsungkr.simpleimgapp.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<Post> postList;

    public PostAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml 파일을 연결하는 작업
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);
        return new PostAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);

        // 데이터 셋팅 !!! => 가공해서 셋팅
        holder.txtTitle.setText(post.title);
        holder.txtId.setText(post.id+"");
        holder.txtAlbumId.setText(post.albumId+"");

        // 이미지뷰에 사진 셋팅
        Glide.with(context).load(post.thumbnailUrl).placeholder(R.drawable.outline_insert_photo_24).into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtId;
        TextView txtAlbumId;
        ImageView imgPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtId =itemView.findViewById(R.id.txtId);
            txtAlbumId = itemView.findViewById(R.id.txtAlbumId);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);

            imgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();

                    Post post = postList.get(index);

                    Intent intent = new Intent(context, PhotoActivity.class);
                    intent.putExtra("url",post.url);

                    context.startActivity(intent);
                }
            });

        }
    }
}
