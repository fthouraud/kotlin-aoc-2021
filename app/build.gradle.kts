import java.time.LocalDate

plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest()
        }
    }
}

tasks.create("initDay").doFirst {
    val dayOfMonth = LocalDate.now().dayOfMonth
    val projectDirectory = layout.projectDirectory.asFile

    with(File(projectDirectory, """src/main/kotlin/me/fth/aoc/day$dayOfMonth""")) {
        mkdir()
        File(this, """Day$dayOfMonth.kt""").createNewFile()
    }

    File(projectDirectory, """src/main/resources/day$dayOfMonth.txt""").createNewFile()

    with(File(projectDirectory, """src/test/kotlin/me/fth/aoc/day$dayOfMonth""")) {
        mkdir()
        File(this, """Day${dayOfMonth}Test.kt""").createNewFile()
    }
}