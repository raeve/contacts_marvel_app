# Contacts Marvel App
An app to list contacts from phone with Marvel characters and split an amount of euros between them.

## Usage

To retrieve data from [MarvelAPI](https://developer.marvel.com/docs), you need to create an account and replace the public key and private key in *NetworkConfig.kt* with your credentials
```kotlin
    const val MARVEL_PUBLIC_KEY = "your_public_key"
    const val MARVEL_PRIVATE_KEY = "your_private_key"
```


## Summary

* Kotlin app based on Clean Architecture (MVP pattern)
* Interactors connected to the different layers using Repository pattern.
* Dependency injection handled with Dagger2
* Asynchronous events with RxKotlin
* Used [Marvel API](https://developer.marvel.com/docs) as network provider. Endpoint used **characters**


## Libraries

* [Dagger](https://google.github.io/dagger/)
* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [Retrofit](https://square.github.io/retrofit/)
* [Picasso](https://square.github.io/picasso/)
* [Lottie](https://airbnb.design/lottie)
* [Mockito](https://github.com/nhaarman/mockito-kotlin/)
