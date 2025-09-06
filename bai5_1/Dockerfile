# ==== 1) Build WAR với Maven ====
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom trước để cache dependency
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Copy src và build
COPY src ./src
RUN mvn -B -DskipTests package

# ==== 2) Chạy trên Tomcat 10 (Jakarta) ====
FROM tomcat:10.1-jdk21-temurin

# Xóa app mặc định và copy WAR của bạn làm ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
