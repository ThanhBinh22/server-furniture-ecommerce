APP_NAME = server-furniture-ecommerce
JAR_FILE = build/libs/$(APP_NAME).jar
GRADLE = ./gradlew
PORT = 8085

.PHONY: help
help:
	@echo "Usage: make [target]"
	@echo "Targets:"
	@echo "  build         Build the Spring Boot application"
	@echo "  run           Run the application"
	@echo "  clean         Clean the build files"

.PHONY: build
build:
	$(GRADLE) bootJar

.PHONY: run
run: build
	@echo "Running application on port $(PORT)..."
	java -jar $(JAR_FILE)

.PHONY: clean
clean:
	$(GRADLE) clean
