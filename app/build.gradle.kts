plugins {
  id("org.jetbrains.kotlin.jvm")
  application
}

dependencies {
  // implementation(project(":utils"))
}

application {
  mainClass = "org.example.app.AppKt"
}
