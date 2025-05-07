# kotlin-compile-bug-repro

The bugs described below fail with both Kotlin `2.0.21` and `2.1.20`.

Reported at https://youtrack.jetbrains.com/issue/KT-77416/kotlinCompile-succeeds-when-it-should-fail.

## First bug: just `:app:compileKotlin`

Run

```shell
./gradlew app:compileKotlin
```

the build should pass.

Now, in `app/build.gradle.kts`, comment out the dependency on the `:utils` project. Run the task 
again. Observe that the build _passes_ -- it shouldn't! The `Example.kt` file in the `:app` module
imports a constant from the `:utils` module. Compilation cannot succeed without that dependency. 
Now, run this:

```shell
./gradlew app:compileKotlin --rerun
```

the build fails, as expected, along with the expected error message:
```
> Task :app:compileKotlin FAILED
e: file:///<...>/app/src/main/kotlin/Example.kt:1:8 Unresolved reference 'Something'.
e: file:///<...>/app/src/main/kotlin/Example.kt:5:13 Unresolved reference 'CONSTANT'.
```

## Second bug: `:app:compileKotlin --build-cache`

There is a related bug that might be even more pernicious, which involves an interaction with the
Gradle build cache.

First, re-add the dependency that was removed earlier. Now, run

```shell
./gradlew app:compileKotlin --build-cache
```

The build succeeds, as expected. Remove the dependency and run the command again. The build will 
again succeed, when it should fail. Now run this:

```shell
./gradlew app:compileKotlin --rerun --build-cache
```

and the build will fail, as expected, along with the expected error message (same as above). 
Finally, run this again:

```shell
./gradlew app:compileKotlin --build-cache
```

once again the build succeeds when it shouldn't!
