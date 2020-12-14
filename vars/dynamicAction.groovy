#!/usr/bin/env groovy

def call(List args) {

    println("==>DynamicAction(" + args + ")")

    if (args.size() < 1) {
        println("fatal: DynamicAction() expects at least 1 parameter")
        sh("false")
    }
    def actionName = args[0]
    def actionScript = libraryResource "actions/${actionName}"
    def toFile = env.WORKSPACE + "/cidemo_${actionName}"

    writeFile(file: toFile, text: actionScript)
    sh("chmod +x " + toFile)

    def cmd = toFile
    if (args.size() > 1) {
        cmd += " " + args.subList(1,args.size()).collect{ "'" + it + "'"}.join(" ")
    }
    sh(cmd)
    return;
}
