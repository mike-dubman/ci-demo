#!/usr/bin/env groovy

def call(projectName, projectVersion, projectSrcPath, attachArtifact, reportName, scanMode) {
    echo "mmmmmm Hello ${projectName} -- ${projectVersion} -- $projectSrcPath -- last: $scanMode"
    echo "mooo=${env.SPRING_APPLICATION_JSON}"
}
