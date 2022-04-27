# Introduction
Wellcome to the testing workshop for android. In this workshop we are going to learn and practice the frameworks we are going to use in order to unit test our code in Picsart.

This Workshop is mainly set for regular domain business and presentation logic. We can say It's focused on the **social** part of picsart.

>:warning: Please read this README file carefuly as it includes all the information, and resources you'll need in order to start worrking on it.
# Frameworks
In this section we are going to introduce all the important frameworks and technologies we are going to use in the workshop to prevent frustration and confusion.

## Kotest
**Kotest**, formerly **Kotlin Test** is a well know testing framework fully implemented in Kotlin. It relies in Junit so it's totally compatible with the current tetsing stack we are used to work with in Android.

We created a [document in confluence](https://picsart.atlassian.net/wiki/spaces/EN/pages/2963866769/Guidelines) in order to learn how to start using it with Guidelines.

> Please, before starting the wokrshop take your time to read the documentation.
## Arrow
[Arrow](https://arrow-kt.io/) is a functional companion library for Kotlin. Don't worry and before getting scared read with calm: We used this library only in orderr to use the `Either` class. Just read carefully the following few lines to understand why:

### Either
[`Either<A,B>`](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/) is a class that can be either `Left<A>` or `Right<B>`. The Behavior is that we can asume we are always in the `Right` path (our domain) and if there is a failure then it shortcircuits to `Left`.

In other words it's a wrapper for `Failure` (`Left`) or for `Success` (`Right`).

As an example we can show this:
```
interface A {
    fun execute(): Either<String, Int>
}
object Success: A {
    fun execute() = Right(0)
}
object Fail: A {
    fun execute() = Left("Fail")
}
```

To Unwrapp the Either we just need to do:
```
fun readValue(a: A) {
    a.execute()
    .fold(
        ifLeft = { /* here "it" is a string */ },
        ifRight = { /* here "it" is a Int */}
    )
}
```

why we use it?
- It provides functional operators (like Flows, RxJava observables, etc) like, map, flatMap, foldRight, etc...
- It's error prone, we can use the `Left` to emit errors.
- It's built on top of coroutines and provides amazing concurrency management.
- It's easy to test results without unfolding the object
- It's a new toy you can play with and have fun 😃

# How this workshop is structured?
This workshop is about implementing a new feature with specific requirements described in this [Issue](https://github.com/Atternatt/TestingWorkshop/issues/1)
## Scope of the workshop

The workshop provides the default definition of the interfaces, classes and it's dependencies as specified in the [Class Diagram](https://github.com/Atternatt/TestingWorkshop/issues/1).
There is also some already implemented stuff (like the architecture package) that provides tests for it's features. The [architecture](https://github.com/Atternatt/TestingWorkshop/tree/master/business/src/main/java/com/picsart/business/arch) package can be considered a __legacy__ package that has been provided to you as a library. It's there for you and their tests are also for you to take a look in to if you feel curious but we won't need to go deeper on them in the workshop.

The scope of the workshop is everything that has been tagged with the `TODO()` function. You can take a big picture of all of them just clicking in the `TODO` section of your `Android Studio`.

We have 5 todos, therefore we have 5 steps in the workshop divided in 2 phases

1. Implementation of the business layer
2. Implementation of the presentation layer


### Phase 1: The business layer
This is the most active one, we are going to implement a feature in live. While performing the meeting meant for the workshop we are going to work step by step into the implementation of the feature and it's unit tests.

#### Step 1: Implement the Repositoy
This is the very first step of the workshop we need to implement the logic for the repository interface whose sequence diagram is documented in the [Issue](https://github.com/Atternatt/TestingWorkshop/issues/1).

In this case the test for it is already built in. Take a look into [DefaultGetPostsRepositoryTest](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/test/kotlin/com/picsart/business/feature/posts/data/repository/DefaultGetPostsRepositoryTest.kt). The purpose of this section is to understand how is the structure of a test and how to make the implementation to satisfy it.

#### Step 2: Implement the Network Data Source
At this point we'll have implemented and tested our Repository, we tested it in isolation, we know it works as it's expected to work but then we need to implement the network leyer in order to provide the expected data.

In this scenario we have [NetworkGetPostsDataSourceTest](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/test/kotlin/com/picsart/business/feature/posts/data/datasource/NetworkGetPostsDataSourceTest.kt) also implemented. In this case we have a special corner case for testing, we depend on a 3rd party deppendency (`HttpClient`). In this step we are going to lear how to bypass this situations by creating test fakes that will help us to test out implementation.

In this step we are going to implement the logic for [NetworkGetPostsDataSource](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/main/java/com/picsart/business/feature/posts/data/datasource/NetworkGetPostsDataSource.kt)

#### Step 3 and 4: Implement the Cache mechanism and the use case

With the examples implemented in the first 2 steps we are going to implement the cache data source and use case tests and logic following the exact same dynamics as the previous steps: First we are going to define the tests and then we are going to implement the feature.

### Pase 2: The presentation layer

At this point we'll have implemented the whole busines layer, from data sources to use cases and we'll have to do the exact same thing we did in [Step 3](#step-3-implement-the-cache-mechanism-and-the-use-case) but this time we'll have to implement the ViewModel test and logic.

This Pahse is out of the scope of the live coding session and is a **Homewok** that attendees will need to implement following the examples and guidelines provided in the [confluence documentation](https://picsart.atlassian.net/wiki/spaces/EN/pages/2964717777/Test+Samples+Guides#ViewModels-MVVM).

# Solutions
Each step on [Phase 1](#phase-1-the-business-layer) has a solution branch following this nomenclature:
`solution/step-#` where `#` has to be replaced by the step number `1`, `2`, `3` o `4`

> Phase 2 doesn't have solutions provided, is up to the developer to make a fork of this repo implement the feature and create a PR with the solution. Despite the solution is not provided there are examples in the [confluence documentation](https://picsart.atlassian.net/wiki/spaces/EN/pages/2964717777/Test+Samples+Guides#ViewModels-MVVM) that can be seen to implement the remaining code.