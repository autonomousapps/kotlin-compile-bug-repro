pluginManagement {
  plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention")
  id("org.jetbrains.kotlin.jvm") apply false
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

include(":app")
include(":utils")

rootProject.name = "kotlin-compile-bug-repro"
