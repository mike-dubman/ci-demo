#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd)")
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = File.createTempFile(actionName, null) 
    writeFile(file: toFile.getAbsolutePath(), text: "${actionScript}")
    sh("chmod 755 " + toFile.getAbsolutePath())

    sh(toFile.getAbsolutePath() + " $buildCmd")
    return;
}
