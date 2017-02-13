package com.moffcomm.supatem.data.remote;

import android.support.annotation.NonNull;

import com.moffcomm.supatem.data.remote.model.SupaDeal;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.moffcomm.supatem.data.remote.Order.ASC;
import static com.moffcomm.supatem.data.remote.Order.DESC;

final class SortUtil {
    private static final StarsComparator STARS_ASC = new StarsComparator(ASC);
    private static final StarsComparator STARS_DESC = new StarsComparator(DESC);
    private static final ForksComparator FORKS_ASC = new ForksComparator(ASC);
    private static final ForksComparator FORKS_DESC = new ForksComparator(DESC);

    static void sort(List<SupaDeal> repositories, Sort sort, Order order) {
        if (repositories == null) return;

        switch (sort) {
            case STARS:
                Collections.sort(repositories, order == ASC ? STARS_ASC : STARS_DESC);
                break;
            case FORKS:
                Collections.sort(repositories, order == ASC ? FORKS_ASC : FORKS_DESC);
                break;
            default:
                throw new IllegalArgumentException("Unknown sort: " + sort);
        }
    }

    private static abstract class OrderComparator<T> implements Comparator<T> {
        private final Order order;

        protected OrderComparator(Order order) {
            this.order = order;
        }

        @Override
        public final int compare(@NonNull T lhs, @NonNull T rhs) {
            return order == ASC ? compareAsc(lhs, rhs) : -compareAsc(lhs, rhs);
        }

        protected abstract int compareAsc(@NonNull T lhs, @NonNull T rhs);
    }

    private static final class StarsComparator extends OrderComparator<SupaDeal> {
        protected StarsComparator(Order order) {
            super(order);
        }

        @Override
        public int compareAsc(@NonNull SupaDeal lhs, @NonNull SupaDeal rhs) {
            long left = lhs.watchers;
            long right = rhs.watchers;
            return left < right ? -1 : (left == right ? 0 : 1);
        }
    }

    private static final class ForksComparator extends OrderComparator<SupaDeal> {
        protected ForksComparator(Order order) {
            super(order);
        }

        @Override
        public int compareAsc(@NonNull SupaDeal lhs, @NonNull SupaDeal rhs) {
            long left = lhs.forks;
            long right = rhs.forks;
            return left < right ? -1 : (left == right ? 0 : 1);
        }
    }

    private SortUtil() {
    }
}
