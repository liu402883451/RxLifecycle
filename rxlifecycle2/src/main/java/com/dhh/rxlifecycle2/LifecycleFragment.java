package com.dhh.rxlifecycle2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


/**
 * Created by dhh on 2017/9/25.
 */

public class LifecycleFragment extends Fragment implements LifecycleManager {
    private final BehaviorSubject<ActivityEvent> lifecycleSubject;

    LifecycleFragment() {
        lifecycleSubject = BehaviorSubject.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityEvent.onCreate);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        lifecycleSubject.onNext(ActivityEvent.onStart);
        super.onStart();
    }

    @Override
    public void onResume() {
        lifecycleSubject.onNext(ActivityEvent.onResume);
        super.onResume();
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(ActivityEvent.onPause);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(ActivityEvent.onStop);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.onDestory);
        super.onDestroy();
    }

    @Override
    public Observable<ActivityEvent> getLifecycle() {
        return lifecycleSubject;
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(final ActivityEvent activityEvent) {
        return new LifecycleTransformer<>(lifecycleSubject, activityEvent);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return new LifecycleTransformer<>(lifecycleSubject);
    }

    @Override
    public <T> LifecycleTransformer<T> bindOnDestroy() {
        return bindUntilEvent(ActivityEvent.onDestory);
    }
}
