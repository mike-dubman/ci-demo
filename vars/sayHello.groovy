#!/usr/bin/env groovy
library(identifier: 'ngci@ci_version-3.1',
                   retriever: modernSCM([$class: 'GitSCMSource', 
                   remote: 'http://l-gerrit.mtl.labs.mlnx:8080/DevOps/Jenkins/ci_framework']))

def call(projectName, projectVersion, projectSrcPath, attachArtifact, reportName, scanMode) {
    echo "mmmmmm Hello ${projectName} -- ${projectVersion} -- $projectSrcPath -- last: $scanMode"
    echo "mooo=${env.SPRING_APPLICATION_JSON}"

//        env.SPRING_APPLICATION_JSON = '{"blackduck.url":"https://blackduck.mellanox.com/","blackduck.api.token":"ODMwOWYwMzEtODA2ZC00MzBjLWI1ZDEtNmFiMjBkYzQzMzkwOjNmNjExN2M1LWE2ZmEtNDZlYS1hZjRiLTZlNDgwNjAwOTVjNw=="}'

    NGCIBlackDuckScan (
        projectName: "ci-demo",
        projectVersion: "1.0",
        projectSrcPath: "${env.WORKSPACE}/src",
        attachArtifact: true ,
        reportName: "BlackDuck report",
        scanMode: "source"
    )
    return;
}
