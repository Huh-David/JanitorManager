import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.5.31"
	id("org.jetbrains.compose") version "1.0.0"
	kotlin("plugin.serialization") version "1.6.10"
}

group = "me.davidhuh"
version = "1.0"

repositories {
	google()
	mavenCentral()
	maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
	testImplementation(kotlin("test"))
	implementation(compose.desktop.currentOs)
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
	implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
}

tasks.test {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "11"
}

compose.desktop {
	application {
		mainClass = "AppKt"
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "JanitorManager"
			packageVersion = "1.0.0"
		}
	}
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
