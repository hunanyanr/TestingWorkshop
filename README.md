# Introduction
Welcome to the testing workshop for Android. In this workshop, we will learn and practice the frameworks we will use to unit test our code in Picsart.

This workshop is mainly set for regular domain business and presentation logic. We can say it's focused on the **social** part of Picsart.

>:warning: Please read this README file carefully, as it includes all the information and resources you'll need to start working on it.
# Frameworks
In this section, we will introduce all the important frameworks and technologies we are going to use in the workshop to prevent frustration and confusion.

## Kotest
**Kotest**, formerly **Kotlin Test**, is a well-known testing framework fully implemented in Kotlin. It relies on Junit, so it's compatible with the current testing stack we are used to working within Android.

We created a [document in confluence](https://picsart.atlassian.net/wiki/spaces/EN/pages/2963866769/Guidelines) to learn how to start using it with Guidelines.

> Before starting the workshop, please take your time to read the documentation.
## Arrow
[Arrow](https://arrow-kt.io/) functional companion library for Kotlin. Don't worry, and before getting scared, read calmly: We used this library only to use the `Either` class. Just read the following few lines carefully to understand why:

### Either
[`Either<A,B>`](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/) is a class that can be either `Left<A>` or `Right<B>`. The Behavior is that we can assume we are always in the `Right` path (our domain), and if there is a failure, it shortcircuits to `Left`.

In other words, it's a wrapper for `Failure` (`Left`) or `Success` (`Right`).

As an example, we can show this:
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

To Unwrap the Either, we just need to do:
```
fun readValue(a: A) {
    a.execute()
    .fold(
        ifLeft = { /* here "it" is a string */ },
        ifRight = { /* here "it" is a Int */}
    )
}
```

Why do we use it?
- It provides functional operators (like Flows, RxJava observables, etc) like map, flatMap, foldRight, etc.
- It's error-prone, we can use the `Left` to emit errors.
- It's built on top of coroutines and provides amazing concurrency management.
- It's easy to test results without unfolding the object.
- It's a new toy you can play with and have fun 😃

# How is this workshop structured?
This workshop is about implementing a new feature with specific requirements described in this [Issue](https://github.com/Atternatt/TestingWorkshop/issues/1)
## Scope of the workshop

The workshop provides the default definition of the interfaces, classes, and dependencies specified in the [Class Diagram](https://github.com/Atternatt/TestingWorkshop/issues/1).
Some already implemented stuff (like the architecture package) also provides tests for its features. The [architecture](https://github.com/Atternatt/TestingWorkshop/tree/master/business/src/main/java/com/picsart/business/arch) package can be considered a __legacy__ package that has been provided to you as a library. It's there for you, and their tests are also for you to look into if you feel curious, but we won't need to go deeper into them in the workshop.

The workshop's scope is everything that has been tagged with the `TODO()` function. You can take a big picture of them by clicking on the `TODO` section of your `Android Studio`.

We have five todos therefore, we have five steps in the workshop divided into 2 phases:

1. Implementation of the business layer.
2. Implementation of the presentation layer.

### Phase 1: The business layer
This is the most active one, and we will implement a feature in live. While performing the meeting meant for the workshop, we will work step by step into implementing the feature and its unit tests.

#### Step 1: Implement the Repositoy
This is the first step of the workshop. We need to implement the logic for the repository interface, whose sequence diagram is documented in the [Issue](https://github.com/Atternatt/TestingWorkshop/issues/1).

In this case, the test for it is already built-in. Take a look into [DefaultGetPostsRepositoryTest](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/test/kotlin/com/picsart/business/feature/posts/data/repository/DefaultGetPostsRepositoryTest.kt). This section aims to understand the structure of a test and how to make the implementation to satisfy it.

#### Step 2: Implement the Network Data Source
At this point, we'll have implemented and tested our Repository. We tested it in isolation, and we know it works as expected, but then we need to implement the network layer to provide the expected data.

In this scenario, we have [NetworkGetPostsDataSourceTest](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/test/kotlin/com/picsart/business/feature/posts/data/datasource/NetworkGetPostsDataSourceTest.kt) also implemented. In this case, we have a special corner case for testing. We depend on a 3rd party dependency (`HttpClient`). In this step, we will learn to bypass this situation by creating test fakes that will help us test out implementation.

In this step, we are going to implement the logic for [NetworkGetPostsDataSource](https://github.com/Atternatt/TestingWorkshop/blob/master/business/src/main/java/com/picsart/business/feature/posts/data/datasource/NetworkGetPostsDataSource.kt)

#### Step 3 and 4: Implement the Cache mechanism and the use case

With the examples implemented in the first two steps, we will implement the cache data source and use case tests and logic following the same dynamics as the previous steps: First, we will define the tests, and then implement the feature.

### Pase 2: The presentation layer

At this point, we'll have implemented the whole business layer, from data sources to use cases, and we'll have to do the same thing we did in [Step 3](#step-3-implement-the-cache-mechanism-and-the-use-case), but this time we'll have to implement the ViewModel test and logic.

This Phase is out of the scope of the live coding session and is a **Homewok** that attendees will need to implement following the examples and guidelines provided in the [confluence documentation](https://picsart.atlassian.net/wiki/spaces/EN/pages/2964717777/Test+Samples+Guides#ViewModels-MVVM).

# Solutions
Each step on [Phase 1](#phase-1-the-business-layer) has a solution branch following this nomenclature:
`solution/step-#` where `#` has to be replaced by the step number `1`, `2`, `3` o `4`

> Phase 2 doesn't have solutions provided, and it is up to the developer to make a fork of this repo, implement the feature and create a PR with the solution. Although the solution is not provided, examples in the [confluence documentation](https://picsart.atlassian.net/wiki/spaces/EN/pages/2964717777/Test+Samples+Guides#ViewModels-MVVM) can be seen to execute the remaining code.
