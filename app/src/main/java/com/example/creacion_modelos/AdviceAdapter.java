package com.example.creacion_modelos;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creacion_modelos.models.Advice;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.MyViewHolder> {

    ArrayList<Advice> adviceList;
    Context context;

    public AdviceAdapter(Context context, ArrayList<Advice> adviceList) {
        this.context = context;
        this.adviceList = adviceList;
    }

    @NonNull
    @Override
    public AdviceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.advice_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceAdapter.MyViewHolder holder, int position) {

        Advice advice = adviceList.get(position);

        holder.title.setText(advice.title);
        holder.description.setText(advice.description);

        if(!advice.url.isEmpty()) {
            holder.videoLocal.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.GONE);

            if(advice.isLink){
                showVideoOnline(holder, advice.url);
            }
            else {
                int idVideo = context.getResources().getIdentifier(advice.url, "raw", context.getPackageName());
                String carpetaRecursos = "android.resource://" + context.getPackageName() + "/";
                Uri uri = Uri.parse(carpetaRecursos + idVideo);

                holder.videoLocal.setVideoURI(uri);

                MediaController mediaController = new MediaController(context);
                mediaController.setAnchorView(holder.videoLocal);
                holder.videoLocal.setMediaController(mediaController);
            }
        }
        else {
            int idImage = context.getResources().getIdentifier(advice.image, "mipmap", context.getPackageName());
            holder.image.setImageResource(idImage);
        }

    }

    public void showVideoOnline(AdviceAdapter.MyViewHolder holder, String linkVideo){

        holder.videoOnline.setVisibility(View.VISIBLE);
        holder.videoLocal.setVisibility(View.GONE);
        holder.image.setVisibility(View.GONE);

        String iframeHtml = "<iframe width=\"100%\" height=\"100%\" src=\"" + linkVideo + "\" title=\"YouTube video player\" " +
                "frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" " +
                "referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

        holder.videoOnline.loadData(iframeHtml, "text/html", "utf-8");
        holder.videoOnline.getSettings().setJavaScriptEnabled(true);
        holder.videoOnline.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public int getItemCount() { return adviceList.size();}

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView   image;
        TextView    title;
        TextView    description;
        VideoView   videoLocal;
        WebView videoOnline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            videoLocal = itemView.findViewById(R.id.videoLocal);
            videoOnline = itemView.findViewById(R.id.videoOnline);
        }
    }
}
