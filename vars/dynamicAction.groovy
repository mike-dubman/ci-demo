#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd)")
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = new File ("cidemo_" + actionName)

    println("action file: " + toFile.getAbsolutePath())
    toFile.write("${actionScript}")
    sh("chmod +x " + toFile.getAbsolutePath())

    sh(toFile.getAbsolutePath() + " $buildCmd")
    return;
}
