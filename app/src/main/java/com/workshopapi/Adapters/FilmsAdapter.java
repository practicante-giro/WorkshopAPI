package com.workshopapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.workshopapi.Models.Film;
import com.workshopapi.R;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {
    Context context ;
    List<Film> filmList;

    public FilmsAdapter(Context context, List<Film> filmList){
        this.context = context;
        this.filmList = filmList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(filmList.get(position).getTitle());
        holder.episode_id.setText(String.valueOf(filmList.get(position).getEpisode()));
        holder.opening.setText(filmList.get(position).getOpening_crawl());
        holder.director.setText(filmList.get(position).getDirector());
        holder.producer.setText(filmList.get(position).getProducer());
        holder.url.setText(filmList.get(position).getUrl());
        holder.created.setText(filmList.get(position).getCreated());
        holder.edited.setText(filmList.get(position).getEdited());


    }

    @Override
    public int getItemCount() {

        return filmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Se comportan como textview
        TextView title,episode_id,opening,
        director,producer,url,created,edited;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //traer del xml
            title = itemView.findViewById(R.id.title);
            episode_id = itemView.findViewById(R.id.episode_id);
            opening = itemView.findViewById(R.id.opening_crawl);
            director = itemView.findViewById(R.id.director);
            producer = itemView.findViewById(R.id.producer);
            url = itemView.findViewById(R.id.url);
            created = itemView.findViewById(R.id.created);
            edited = itemView.findViewById(R.id.edited);


        }
    }

}
