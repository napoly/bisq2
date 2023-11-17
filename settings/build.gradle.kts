plugins {
    id("bisq.java-library")
    id("bisq.protobuf")
}

dependencies {
    implementation(project(":persistence"))
    implementation(project(":i18n"))

    implementation("network:network")

    implementation(libs.google.guava)
}
