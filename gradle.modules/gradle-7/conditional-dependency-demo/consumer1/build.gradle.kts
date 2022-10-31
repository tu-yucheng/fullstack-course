plugins {
	id("java")
}

group = "cn.tuyucheng.taketoday"
version = "1.0.0"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

	if (project.hasProperty("isLocal")) {
		implementation("cn.tuyucheng.taketoday:provider1")
	} else {
		implementation("cn.tuyucheng.taketoday:provider2")
	}
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}