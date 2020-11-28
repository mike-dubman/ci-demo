#!/usr/bin/env groovy

@NonCPS
def call(actionName, preCmd, buildCmd) {

    println("==>DynamicAction ($actionName, $preCmd, $buildCmd)")
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = new File (env.WORKSPACE + "/cidemo_" + actionName)

    println("action file: " + toFile.getAbsolutePath())
    writeFile(file: toFile.getAbsolutePath(), text: actionScript)
    sh("chmod +x " + toFile.getAbsolutePath())

    sh(toFile.getAbsolutePath() + " $buildCmd")
    return;
}
