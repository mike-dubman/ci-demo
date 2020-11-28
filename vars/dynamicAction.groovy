#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd)")
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = new File ("cidemo_" + actionName)
    toFile.write("${actionScript}")
    sh("chmod 755 " + toFile)

    sh(toFile.getAbsolutePath() + " $buildCmd")
    return;
}
