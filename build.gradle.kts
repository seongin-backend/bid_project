plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.oracle.database.jdbc:ojdbc8:21.1.0.0")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.6")
    implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")
    compileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.8")
}
