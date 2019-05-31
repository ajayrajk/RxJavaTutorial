package com.ajayraj.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ajayraj.rxjavademo.data.DataSource;
import com.ajayraj.rxjavademo.model.Task;



import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable=new CompositeDisposable();
private static  final String TAG=MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inIt();
        flowable();
        createOprator();
    }

    private void createOprator(){

        // Instantiate the object to become an Observable
        final Task task = new Task("Walk the dog", false, 4);

        // Create the Observable
        Observable<Task> singleTaskObservable = Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
                        if(!emitter.isDisposed()){
                            emitter.onNext(task);
                            emitter.onComplete();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

       // Subscribe to the Observable and get the emitted object
        singleTaskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "create onNext: single task: create" + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private  void inIt(){

        Observable<Task> taskObservable = Observable // create a new Observable object
                .fromIterable(DataSource.createTasksList()) // apply 'fromIterable' operator
                .subscribeOn(Schedulers.io()) // designate worker thread (background)
                .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)


        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                //disposable added to main activity
                compositeDisposable.add(d);
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
        //it is use for large range up to 1 to 10000000..
     Flowable<Integer> flowableLarge=  Flowable.range(0, 100)
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
