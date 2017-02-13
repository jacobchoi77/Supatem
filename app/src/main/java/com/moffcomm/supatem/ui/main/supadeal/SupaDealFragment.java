package com.moffcomm.supatem.ui.main.supadeal;

/**
 * Created by jacobsFactory on 2017-02-06.
 */

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moffcomm.supatem.R;
import com.moffcomm.supatem.data.callbacks.CallbackSupaDeals;
import com.moffcomm.supatem.data.remote.WebServiceManager;
import com.moffcomm.supatem.data.remote.model.SupaDeal;
import com.moffcomm.supatem.ui.misc.BetterViewAnimator;
import com.moffcomm.supatem.ui.misc.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moffcomm.supatem.ui.misc.DividerItemDecoration.VERTICAL_LIST;


public class SupaDealFragment extends Fragment implements SupaDealAdapter.RepositoryClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View mContentView;

    @BindView(R.id.supadeal_animator)
    BetterViewAnimator animatorView;
    @BindView(R.id.supadeal_swipe_refresh)
    SwipeRefreshLayout swipeRefreshView;
    @BindView(R.id.supadeal_list)
    RecyclerView recyclerView;
    @BindView(R.id.supadeal_loading_message)
    TextView loadingMessageView;
    private SupaDealAdapter supaDealAdapter;
    private WebServiceManager webServiceManager;

    public SupaDealFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webServiceManager = WebServiceManager.getInstance(getActivity().getApplication());
        supaDealAdapter = new SupaDealAdapter(webServiceManager.getPicasso(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_supa_deal, container, false);
        ButterKnife.bind(this, mContentView);

        AnimationDrawable ellipsis =
                (AnimationDrawable) ContextCompat.getDrawable(getContext(), R.drawable.dancing_ellipsis);
        loadingMessageView.setCompoundDrawablesWithIntrinsicBounds(null, null, ellipsis, null);
        ellipsis.start();

        swipeRefreshView.setColorSchemeResources(R.color.accent);
        swipeRefreshView.setOnRefreshListener(this);

        supaDealAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                animatorView.setDisplayedChildId(supaDealAdapter.getItemCount() == 0 //
                        ? R.id.supadeal_empty //
                        : R.id.supadeal_swipe_refresh);
                swipeRefreshView.setRefreshing(false);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), VERTICAL_LIST, 0, true));
        recyclerView.setAdapter(supaDealAdapter);
        return mContentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        onRefresh();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRepositoryClick(SupaDeal supaDeal) {

    }

    @Override
    public void onRefresh() {
        if (animatorView.getDisplayedChildId() != R.id.supadeal_swipe_refresh) {
            animatorView.setDisplayedChildId(R.id.supadeal_loading);
        }
        swipeRefreshView.setRefreshing(true);
        webServiceManager.getSupaDeals("", 0, new CallbackSupaDeals() {
            @Override
            public void onSupaDealsLoaded(List<SupaDeal> supaDeals) {
                supaDealAdapter.setSupaDeals(supaDeals);
            }

            @Override
            public void onDataNotAvailable(String errorMsg) {

            }

            @Override
            public void onFailure() {

            }
        });
    }

}
