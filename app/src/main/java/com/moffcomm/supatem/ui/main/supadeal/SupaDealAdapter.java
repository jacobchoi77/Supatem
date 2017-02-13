package com.moffcomm.supatem.ui.main.supadeal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moffcomm.supatem.R;
import com.moffcomm.supatem.data.remote.model.SupaDeal;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

final class SupaDealAdapter extends RecyclerView.Adapter<SupaDealAdapter.ViewHolder> {
    public interface RepositoryClickListener {
        void onRepositoryClick(SupaDeal supaDeal);
    }

    private final Picasso picasso;
    private final RepositoryClickListener repositoryClickListener;

    private List<SupaDeal> supaDeals = Collections.emptyList();

    public SupaDealAdapter(Picasso picasso, RepositoryClickListener repositoryClickListener) {
        this.picasso = picasso;
        this.repositoryClickListener = repositoryClickListener;
        setHasStableIds(true);
    }

    public void setSupaDeals(List<SupaDeal> newSupaDeals) {
        supaDeals = newSupaDeals;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SupaDealItemView view = (SupaDealItemView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_supadeal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindTo(supaDeals.get(i));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return supaDeals.size();
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {
        public final SupaDealItemView itemView;

        public ViewHolder(SupaDealItemView itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SupaDeal supaDeal = supaDeals.get(getAdapterPosition());
                    repositoryClickListener.onRepositoryClick(supaDeal);
                }
            });
        }

        public void bindTo(SupaDeal supaDeal) {
            itemView.bindTo(supaDeal, picasso);
        }
    }
}
