@file:OptIn(ExperimentalSpmForKmpFeature::class)

import com.android.build.api.dsl.androidLibrary
import io.github.frankois944.spmForKmp.swiftPackageConfig
import io.github.frankois944.spmForKmp.utils.ExperimentalSpmForKmpFeature
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.spmForKmp)
}

group = "io.github.itsivag"
version = "1.0.2"

kotlin {
    androidLibrary {
        namespace = "io.github.itsivag.mixpanel_kmp"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_11
                )
            }
        }
    }

    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { target ->
        target.swiftPackageConfig("ios") {
            dependency {
                remotePackageVersion(
                    url = uri("https://github.com/mixpanel/mixpanel-swift.git"),
                    products = {
                        add("Mixpanel")
                    },
                    version = "5.1.3",
                )
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.mixpanel.android)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "mixpanel-kmp", version.toString())

    pom {
        name = "Mixpanel-KMP"
        description =
            "An unofficial Mixpanel client SDK for Kotlin Multiplatform that enables shared analytics across Android and iOS."
        inceptionYear = "2026"
        url = "https://github.com/itsivag/mixpanel-kmp"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "itsivag"
                name = "Siva G"
                url = "https://github.com/itsivag"
            }
        }
        scm {
            url = "https://github.com/itsivag/mixpanel-kmp"
            connection = "scm:git:git://github.com/itsivag/mixpanel-kmp"
            developerConnection = "scm:git:ssh://git@github.com/itsivag/mixpanel-kmp"
        }
    }
}
