# ---------- Stage 1: Build ----------
    FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
    WORKDIR /app
    
    # Cache deps
    COPY pom.xml .
    RUN mvn -B -ntp dependency:go-offline
    
    # Copy sources and build
    COPY . .
    ARG REVISION=1.0.0
    RUN mvn -B -ntp clean package -Drevision=${REVISION} -DskipTests
    
    
    # ---------- Stage 2: Runtime ----------
    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    
    # Create non-root user
    RUN addgroup -S app && adduser -S app -G app
    
    # --- OpenTelemetry Java Agent ---
    ARG OTEL_AGENT_VERSION=2.19.0
    RUN mkdir -p /otel && \
        wget -q -O /otel/opentelemetry-javaagent.jar \
          "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar" && \
        chown -R app:app /otel
    
    # App artifact name provided by CI (e.g., springboot-city-images-<REVISION>.jar)
    ARG ARTIFACT_NAME
    COPY --from=builder /app/target/*jar /app/app.jar

    RUN chown app:app /app/app.jar
    
    # --- OpenTelemetry wiring (sane defaults; override in K8s) ---
    ENV JAVA_TOOL_OPTIONS="-javaagent:/otel/opentelemetry-javaagent.jar" \
        OTEL_SERVICE_NAME="springboot-city-images" \
        OTEL_RESOURCE_ATTRIBUTES="service.namespace=springboot-city-images,deployment.environment=prod" \
        OTEL_TRACES_EXPORTER="otlp" \
        OTEL_METRICS_EXPORTER="none" \
        OTEL_LOGS_EXPORTER="none" \
        OTEL_EXPORTER_OTLP_PROTOCOL="grpc" \
        OTEL_EXPORTER_OTLP_ENDPOINT="http://otel-collector.default.svc:4317"
    
    EXPOSE 8080
    USER app
    
    ENTRYPOINT ["java","-jar","/app/app.jar"]
    