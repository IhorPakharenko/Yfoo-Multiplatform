plugins {
  alias(libs.plugins.spacecards.kotlinMultiplatform)
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(projects.core.model)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.serialization.kotlinx.json)
    }
    androidMain.dependencies {
      implementation(libs.ktor.client.android)
    }
    jvmMain.dependencies {
      implementation(libs.ktor.client.java)
    }
    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
    }
  }
}
