GRADLE = ./gradlew
JAR_FILE = build/libs/$(shell basename "$(CURDIR)")-0.0.1-SNAPSHOT.jar

JAVA_VERSION = 23

.PHONY: all build run test clean

all: build

build:
	$(GRADLE) build

run: build
	$(GRADLE) bootRun


jar:
	$(GRADLE) bootJar

run-jar: jar
	java -jar $(JAR_FILE)

setup-java:
	$(GRADLE) -Porg.gradle.java.home=$(JAVA_VERSION)
