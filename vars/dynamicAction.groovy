#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd)")
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = "cidemo_" + actionName
    writeFile(file: toFile, text: "${actionScript}")
    sh("chmod 755 " + toFile)

    sh(toFile + " $buildCmd")
    return;
}
