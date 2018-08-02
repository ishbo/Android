package com.example.abhishek03.myapplication2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         compositeDisposable = new CompositeDisposable();

        functionRx();
    }

    @SuppressLint("CheckResult")
    private void functionRx() {
        Observable<String> animalsObservable = Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
        Observer<String> animalObserver = getanimalObserver();
        animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(animalObserver);

        compositeDisposable.add(
                animalsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getanimalObserver()));

        /**
         * filter() is used to filter out the animal names starting with 'c'
         * map() is used to transform all the characters to UPPER case
         * */

    }



    private DisposableObserver<String> getanimalObserver() {
    return new DisposableObserver<String>() {

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
    }


}
