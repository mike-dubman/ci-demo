#!/usr/bin/env groovy

def call(projectName, projectVersion, projectSrcPath, attachArtifact, reportName, scanMode) {
    echo "mmmmmm Hello ${projectName} -- ${projectVersion} -- last: $scanMode"
}
