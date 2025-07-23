install gradle https://gradle.org/install/#manually

navigate to project root dir

in sr/main/resources/application.yml, replace keys %WEATHERSTACK_KEY% and %OPEN_WEATHERMAP_KEY% with keys generated from signing up at https://weatherstack.com/ and https://home.openweathermap.org/, respectively.
to test failover, comment out or use incorrect %WEATHERSTACK_KEY%

run command line "gradle build" and then "gradle bootRun" to start up application

only for local testing, in a browser tab navigate to http://localhost:8080/ping

if i had more time, i would:
#1 add unit tests with mockito for cases where weather services are down
#2 add proper caching instead of in-memory cache
#3 improve design of config, service, and client, and reduce duplicate code
#4 better failover handling instead of simple null check
#5 better error handling
