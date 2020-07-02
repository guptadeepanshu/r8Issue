# Demonstrates an issue with R8 enum inlining

## Steps to reproduce:
1. Build `mylib`:
  ```bash
  cd mylib
  ./gradlew publishToMavenLocal
  ```
2. Build and install the release variant of the app:
  ```bash
  cd myapp
  ./gradlew installRelease
  ```
3. On the device, enter any text that contains "FIRST", "SECOND" or "THIRD".
4. Press "Submit" button

## Expectation:
If the text contains one of the three keywords, it should display a message like `this is the first enum value`.

## Observation:
`No match found` is shown for any text that contains only words in caps, like `FIRST`. If the text contains `b`, the message corresponding to `first` is shown. Similarly, `c` shows `second` and `d` shows `third`.

## More details:
The issue started due to https://r8-review.googlesource.com/c/r8/+/40221. The first AGP to include it was 3.6.0. The issue exists in all versions since then.

The `mylib` is compiled with 3.5.3. It is also obfuscated before distribution. Since the above change was not present in 3.5, the enum `MyEnum` is not inlined in `Api` class. However, the enum itself is obfuscated. This result is a strange call where the decompiler shows the enum to be of the below form:

```java
package com.example.mylib;

public enum a {
    b("this is the first enum value"),
    c("this is the second enum value"),
    d("this is the third enum value");

    public final String a;

    public a(String var3) {
        this.a = var3;
    }
}
```

However, at runtime, calling `a.b.name()` returns `FIRST` and not `b`. I'm not an expert in the bytecode specifications. So, I don't know exactly how it's happening.

When such an enum is used in the compilation of the app, where enum name inlining is enabled, it ends up inlining a call to `name()` with the obfuscated name. So, `a.b.name()` becomes `b` instead of `FIRST`.

So, there are two workarounds that are available for the app right now:
1. Use AGP 3.5.3 or older
2. Disable R8 and use proguard (set `android.enableR8=false` in `gradle.properties`)