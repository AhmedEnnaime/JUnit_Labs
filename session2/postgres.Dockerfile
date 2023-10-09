FROM postgres:15

ENV POSTGRES_PASSWORD=3ea14367A4
ENV POSTGRES_DB=easybank_test

RUN mkdir -p /docker-entrypoint-initdb.d

COPY ./src/main/java/org/jUnit/db/migration/db.sql /docker-entrypoint-initdb.d/

EXPOSE 5433