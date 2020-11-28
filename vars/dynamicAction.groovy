#!/usr/bin/env groovy

def call(List args) {

    println("==>DynamicAction(" + args + ")")
    def actionName = args[0]
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = new File (env.WORKSPACE + "/cidemo_" + actionName)

    println("action file: " + toFile.getAbsolutePath())
    writeFile(file: toFile.getAbsolutePath(), text: actionScript)
    sh("chmod +x " + toFile.getAbsolutePath())

    sh(toFile.getAbsolutePath() + args)
    return;
}
