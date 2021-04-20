FROM maven:3-jdk-8
WORKDIR /app
COPY . .
RUN mkdir target
CMD ["mvn", "test", "site", "surefire-report:report-only"]
