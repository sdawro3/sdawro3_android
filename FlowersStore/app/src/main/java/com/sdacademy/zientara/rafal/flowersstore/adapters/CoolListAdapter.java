package com.sdacademy.zientara.rafal.flowersstore.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sdacademy.zientara.rafal.flowersstore.R;
import com.sdacademy.zientara.rafal.flowersstore.models.Flower;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 12.07.2017.
 */

public class CoolListAdapter extends ArrayAdapter<Flower> {
    private final LayoutInflater inflater;
    private final int imageSizePixels;
    private List<Flower> flowerList;
    private OnItemClicked onItemClicked;

    public CoolListAdapter(@NonNull Context context, List<Flower> flowerList, OnItemClicked onItemClicked) {
        super(context, R.layout.flower_item);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.flowerList = flowerList;
        this.onItemClicked = onItemClicked;
        imageSizePixels = context.getResources().getDimensionPixelSize(R.dimen.image_size);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Flower flower = flowerList.get(position);

        View rowView = convertView;
        if (rowView == null)
            rowView = inflater.inflate(R.layout.flower_item, parent, false);

        ViewHolder holder = (ViewHolder) rowView.getTag();
        if (holder == null) {
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);
        }

        holder.nameText.setText(flower.getName());
        holder.priceText.setText(String.format("%.2f $", flower.getPrice()));
        Picasso.with(getContext())
                .load(flower.getPhotoUrl())
                .resize(imageSizePixels, imageSizePixels)
                .centerCrop()
                .into(holder.imageView);

        setOnRowClickedListener(flower, rowView);
        setOnPriceClickedListener(flower, holder.priceText);

        holder.imageView.setOnTouchListener(new DoubleTap() {
            @Override
            protected void onFingerUp() {
                Toast.makeText(getContext(), "Podniosles palec!", Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }

    private void setOnPriceClickedListener(final Flower flower, TextView priceText) {
        priceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClicked != null)
                    onItemClicked.onPriceClicked(flower);
            }
        });
    }

    private void setOnRowClickedListener(final Flower flower, View rowView) {
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClicked != null)
                    onItemClicked.onRowClicked(flower);
            }
        });
    }

    @Override
    public int getCount() {
        return flowerList.size();
    }

    @Override
    public boolean isEmpty() {
        return flowerList.isEmpty();
    }

    public interface OnItemClicked {
        void onRowClicked(Flower flower);

        void onPriceClicked(Flower flower);
    }

    class ViewHolder {
        @BindView(R.id.flowerItem_nameText)
        TextView nameText;

        @BindView(R.id.flowerItem_priceText)
        TextView priceText;

        @BindView(R.id.flowerItem_imageView)
        AppCompatImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
