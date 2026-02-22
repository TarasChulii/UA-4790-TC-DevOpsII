import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  	id("org.springframework.boot") version "3.1.0"
  	id("io.spring.dependency-management") version "1.1.0"
  	kotlin("jvm") version "1.8.21"
  	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.serialization") version "1.8.21"
}

group = "academy.softserve"
version = "0.0.1-SNAPSHOT"
java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}


// TODO: your code starts here

tasks.register<Exec>("npmInstall") {
	val dir = project.projectDir
	workingDir = File("${dir}/ui")
	commandLine("npm install".split(" "))
}

tasks.register<Exec>("compileUi") {
	val dir = project.projectDir
	workingDir = File("${dir}/ui")
	commandLine("npm run build".split(" "))
	dependsOn("npmInstall")
}

tasks.register<Copy>("copyUi") {
	val dir = project.projectDir
	from("${dir}/ui/dist")
	into("${dir}/src/main/resources")
	dependsOn("compileUi")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
	val dir = project.projectDir
	dependsOn("copyUi")
}
