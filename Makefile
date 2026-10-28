.PHONY: build run jar clean

build:
	gradle build

run:
	gradle run

jar:
	gradle shadowJar
	cp build/libs/weather-dashboard-all.jar .

clean:
	gradle clean
	rm -f weather-dashboard-all.jar