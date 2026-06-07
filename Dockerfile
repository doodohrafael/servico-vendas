FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

RUN ./mvnw dependency:go-offline -B

COPY src/ src/
RUN export MAVEN_OPTS="--enable-preview" && ./mvnw package -DskipTests -B

FROM eclipse-temurin:25-jre-alpine AS runtime

LABEL maintainer="servico-vendas-maintainer"
LABEL version="1.0.0"

RUN addgroup -S servicovendasgroup && adduser -S servicovendasuser -G servicovendasgroup

WORKDIR /app

RUN #chown -R servicovendasuser:servicovendasgroup /app

USER servicovendasuser

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget -qO- http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=70.0", \
    "-XX:+OptimizeStringConcat", \
    "--enable-preview", \
    "-jar", "app.jar"]