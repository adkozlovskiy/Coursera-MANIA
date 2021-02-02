package com.example.practicethree.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicethree.objects.Image;
import com.example.practicethree.R;
import com.example.practicethree.objects.RecyclerObject;
import com.example.practicethree.objects.Text;

import java.util.ArrayList;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int IMAGE_VIEW_TYPE = 0;
    public static final int TEXT_VIEW_TYPE = 1;
    public ArrayList<RecyclerObject> recyclerObjects;

    public MainAdapter(ArrayList<RecyclerObject> recyclerObjects) {
        this.recyclerObjects = recyclerObjects;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);

        if (viewType == IMAGE_VIEW_TYPE) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view, parent, false);
            return new ImageViewHolder(v);
        }

        return new TextViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case IMAGE_VIEW_TYPE:
                Image image = (Image) recyclerObjects.get(position);
                ((ImageViewHolder) holder).image.setImageResource(image.getResId());

                ((ImageViewHolder) holder).linearLayout.setOnClickListener(view -> {
                    notifyItemRemoved(holder.getAdapterPosition());
                    recyclerObjects.remove(image);
                });
                break;

            case TEXT_VIEW_TYPE:
                Text text = (Text) recyclerObjects.get(position);
                ((TextViewHolder) holder).text.setText(text.getText());

                ((TextViewHolder) holder).linearLayout.setOnClickListener(view -> {
                    notifyItemRemoved(holder.getAdapterPosition());
                    recyclerObjects.remove(text);
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (recyclerObjects.get(position) instanceof Image)
            return IMAGE_VIEW_TYPE;
        else if (recyclerObjects.get(position) instanceof Text)
            return TEXT_VIEW_TYPE;

        return -1;
    }

    @Override
    public int getItemCount() {
        return recyclerObjects.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout linearLayout;

        public TextViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.linear);
            text = view.findViewById(R.id.text);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        LinearLayout linearLayout;

        public ImageViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.linear);
            image = view.findViewById(R.id.image);
        }
    }

    public void addText(Text text) {
        recyclerObjects.add(text);
        notifyItemChanged(getItemCount() - 1);
    }

    public void addImage(Image image) {
        recyclerObjects.add(image);
        notifyItemChanged(getItemCount() - 1);
    }

}
