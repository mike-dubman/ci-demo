#!/usr/bin/env groovy

def call(List args) {

    println("==>DynamicAction(" + args + ")")

    if (args.size() < 1) {
        println("fatal: DynamicAction() expects at least 1 parameter")
        sh("false")
    }
    def actionName = args[0]
    def actionScript = libraryResource "actions/${actionName}"
    def uuid = UUID.randomUUID().toString()
    def toFile = env.WORKSPACE + "/cidemo_${uuid}_${actionName}"

    println("xxxxx action file " + toFile)
    writeFile(file: toFile, text: actionScript)
    sh("chmod +x " + toFile)
    println("xxxxx passed write ")

    def cmd = toFile
    if (args.size() > 1) {
        cmd += " " + args.subList(1,args.size()).collect{ "'" + it + "'"}.join(" ")
    }
    println("xxxxx Running " + cmd)
    sh(cmd)
    println("xxxxx ${actionName} -- call done")
    return;
}
