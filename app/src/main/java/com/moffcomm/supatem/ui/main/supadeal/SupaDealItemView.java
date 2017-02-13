package com.moffcomm.supatem.ui.main.supadeal;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moffcomm.supatem.R;
import com.moffcomm.supatem.data.remote.model.SupaDeal;
import com.moffcomm.supatem.ui.misc.Truss;
import com.moffcomm.supatem.ui.transform.CircleStrokeTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class SupaDealItemView extends RelativeLayout {
    @BindView(R.id.supadeal_repository_avatar)
    ImageView avatarView;
    @BindView(R.id.supadeal_repository_name)
    TextView nameView;
    @BindView(R.id.supadeal_repository_description)
    TextView descriptionView;
    @BindView(R.id.supadeal_repository_stars)
    TextView starsView;
    @BindView(R.id.supadeal_repository_forks)
    TextView forksView;

    private final CircleStrokeTransformation avatarTransformation;
    private final int descriptionColor;

    public SupaDealItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.textColorSecondary, outValue, true);
        descriptionColor = outValue.data;

        // TODO: Make this a singleton.
        avatarTransformation =
                new CircleStrokeTransformation(context, ContextCompat.getColor(context, R.color.avatar_stroke), 1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(SupaDeal supaDeal, Picasso picasso) {
        picasso.load("https://avatars.githubusercontent.com/u/25229317?v=3")
                .placeholder(R.drawable.avatar)
                .fit()
                .transform(avatarTransformation)
                .into(avatarView);
        nameView.setText(supaDeal.name);
        starsView.setText(String.valueOf(supaDeal.watchers));
        forksView.setText(String.valueOf(supaDeal.forks));

        Truss description = new Truss();

        if (!TextUtils.isEmpty(supaDeal.description)) {
            description.pushSpan(new ForegroundColorSpan(descriptionColor));
            description.append(" — ");
            description.append(supaDeal.description);
            description.popSpan();
        }

        descriptionView.setText(description.build());
    }
}
