# RxJavaTutorial

Introduction
The job of an operator that falls into the transformation category is as follows:

Below are some of the most common transformation operators that I think you'll find yourself using.

Map:-
Applies to a function each emitted item.It transforms each emitted item by applying a function to it.

I'm going to teach you about four operators who's job is to create Observables.

1. create()

2. just()

3. range()

4. repeat()

The 'just()' and 'create()' operators should be used if you want to create a single Observable.

The Just() operator has the ability to accept a list of up to 10 entries. However, I don't see a point in passing it a list because there's other operators that also accepts lists, and they aren't limited to 10 entries. So I recommend only using it to create a single observable.


range() and repeat() are great for replacing loops or any iterative processes / methods. You can do the work on a background thread and observe the results on the main thread.

create()  :----
----------------------------------------------------
Input:T
Output:Observable<T>
  
  
  
  Advantage of LiveData:
  
 1. Ensures your UI matches your data state.
 2. No memory leaks.
 3. No crashes due to stopped activities.
              :---- If the observer's lifecycle is inactive, such as in the case of an activity in the back stack, then it doesn’t receive any   LiveData events
 4. No more manual lifecycle handling.
 5. Always up to date data.
 6. Proper configuration changes.
 7. Sharing resources.
 







