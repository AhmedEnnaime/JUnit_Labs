db-build:
	@docker build -t postgres-db -f postgres.Dockerfile .
db-run:
	@docker run -d --name junit-db -p 5433:5432 -v junit_data:/var/lib/postgresql/data postgres-db
db-start:
	@docker start junit-db
db-stop:
	@docker stop junit-db
test:
	mvn clean verify
clean:
	mvn clean install