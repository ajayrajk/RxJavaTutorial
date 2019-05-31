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




