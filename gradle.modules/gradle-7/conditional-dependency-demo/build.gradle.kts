plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("java")
}

group = "cn.tuyucheng.taketoday"
version = "1.0.0"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter:2.6.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.1")
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}