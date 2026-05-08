# =========================
# 1. Build stage
# =========================
FROM crpi-v2fmzydhnzmlpzjc.cn-shanghai.personal.cr.aliyuncs.com/machenkai/maven:3.8.8-eclipse-temurin-8 AS builder

WORKDIR /app

# -------------------------------
# 0. Write Maven settings.xml
# -------------------------------
RUN set -eux; \
    mkdir -p /root/.m2; \
    printf '%s\n' \
    '<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"' \
    '          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"' \
    '          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">' \
    '  <mirrors>' \
    '    <mirror>' \
    '      <id>aliyun-central</id>' \
    '      <mirrorOf>central</mirrorOf>' \
    '      <name>Aliyun Central Mirror</name>' \
    '      <url>https://maven.aliyun.com/repository/central</url>' \
    '    </mirror>' \
    '  </mirrors>' \
    '  <profiles>' \
    '    <profile>' \
    '      <id>fallback-central</id>' \
    '      <repositories>' \
    '        <repository>' \
    '          <id>central</id>' \
    '          <url>https://repo1.maven.org/maven2/</url>' \
    '          <releases><enabled>true</enabled></releases>' \
    '          <snapshots><enabled>false</enabled></snapshots>' \
    '        </repository>' \
    '      </repositories>' \
    '      <pluginRepositories>' \
    '        <pluginRepository>' \
    '          <id>central</id>' \
    '          <url>https://repo1.maven.org/maven2/</url>' \
    '          <releases><enabled>true</enabled></releases>' \
    '          <snapshots><enabled>false</enabled></snapshots>' \
    '        </pluginRepository>' \
    '      </pluginRepositories>' \
    '    </profile>' \
    '  </profiles>' \
    '  <activeProfiles>' \
    '    <activeProfile>fallback-central</activeProfile>' \
    '  </activeProfiles>' \
    '</settings>' \
    > /root/.m2/settings.xml

# -------------------------------
# 1. Copy Maven descriptor first for better layer cache
# -------------------------------
COPY pom.xml /app/pom.xml

# -------------------------------
# 2. Copy source
# -------------------------------
COPY . /app

# -------------------------------
# 3. Build aliyun
# -------------------------------
RUN mvn -s /root/.m2/settings.xml -U -B \
    -Dmaven.wagon.http.retryHandler.count=5 \
    -Dmaven.wagon.httpconnectionManager.ttlSeconds=60 \
    clean package \
    -DskipTests

# =========================
# 2. Runtime stage
# =========================
FROM crpi-v2fmzydhnzmlpzjc.cn-shanghai.personal.cr.aliyuncs.com/machenkai/eclipse-temurin:8-jre

WORKDIR /opt/aliyun

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS=""
ENV LOG_PATH=logs

COPY --from=builder /app/target/aliyun-1.0-SNAPSHOT.jar /opt/aliyun/app.jar

LABEL org.opencontainers.image.title="aliyun" \
      org.opencontainers.image.description="Aliyun DataWorks Spring Boot application image" \
      org.opencontainers.image.version="1.0.0" \
      org.opencontainers.image.authors="mack"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /opt/aliyun/app.jar"]
