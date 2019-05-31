package com.ajayraj.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ajayraj.rxjavademo.data.DataSource;
import com.ajayraj.rxjavademo.model.Task;


import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
private static  final String TAG=MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inIt();
        flowable();
    }

    private  void inIt(){

        Observable<Task> taskObservable = Observable // create a new Observable object
                .fromIterable(DataSource.createTasksList()) // apply 'fromIterable' operator
                .subscribeOn(Schedulers.io()) // designate worker thread (background)
                .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)


        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: : " + d.toString());

            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: : " + task.getDescription());

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: : " + e.toString());

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: : " );

            }
        });
    }

    private void flowable(){
//flowable with backpressure
     Flowable<Integer> flowableLarge=  Flowable.range(0, 1000000)
             .subscribeOn(Schedulers.io()) // designate worker thread (background)
              .onBackpressureBuffer()
             .observeOn(AndroidSchedulers.mainThread());
        Observable<Integer> backToObservable = flowableLarge.toObservable();
        backToObservable .subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: first " + integer);
                    }
                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: first ", t);
                    }
                    @Override
                    public void onComplete() {

                    }
                });


        // flowable to observer

       /* Observable<Integer> observable = Observable
                .range(1, 100000);

        Flowable<Integer> flowable = observable.toFlowable(BackpressureStrategy.BUFFER);

        Observable<Integer> backToObservable = flowable.toObservable();
        backToObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + d.toString());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });*/
    }

}
