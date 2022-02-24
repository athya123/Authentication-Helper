# Authentication-Helper
auth helper for pidu

***

## Setup

In your project build.gradle add this 

```
allprojects {
    repositories {
    ...
    maven { url 'https://jitpack.io' }
   }
}
```

In your settings.gradle change this

```
repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
```

In your app.gradle file add this

```
implementation 'com.github.athya123:Authentication-Helper:1.2'
```

Wherever you want to use this library just declare its object and pass a context into it ( ``` Authentication (context) ```)

***

## Features

### Functions ->

1. initUserState() -> must be called from activity's onCrete method or Fragment's OnCreteView.

2. signUpWithPhone(number: String) -> Can be called whenever you want to sign in with Phone Number, takes number of type string as a parameter.

3. verifyOTP(Otp : String) -> Must be called after the signUp function, takes OTP of type String as a parameter.

### Auth States -> 

Observe the changes in the auth States by observing the state variable in the library. Different States are given below :

#### AuthState.Loading -> You can show a progress bar if the state variable points to this.

#### AuthState.SendOTP -> This state means the OTP has been sent to the number.

#### AuthState.OTPVerified -> This state means the OTP entered has been successfully Verified.

#### AuthState.UserLoggedIn -> This state means the user is already logged in the app (has user which contains UserId and ApiKey)

#### AuthState.UserLoginFirstTime -> This means the user is logged in the app for first time.

#### AuthState.Error -> This shows any error that occured during the procedure (has message of type String)

#### AuthState.Empty -> Empty State
